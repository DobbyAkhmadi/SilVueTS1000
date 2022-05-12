package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.domains.*;
import len.silvue.webpendukung.schedule.application.port.in.ImportUseCase;
import len.silvue.webpendukung.schedule.application.port.out.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImportService implements ImportUseCase {
    private MultipartFile file;
    private String fileStrPath;
    private String typeMasterPlanImport;
    private TypeMasterPlan typeMasterPlan;
    private List<String> stationImportList;
    private List<String> lineImportList;
    private List<String> trainImportList;
    private static final int STATION_LINE_POSITION_IN_FILE = 2;
    private static final int MASTERPLAN_LINE_POSITION_IN_FILE = 4;
    private List<MasterPlanImport> masterPlanImportList;
    private List<Train> trainList;
    private List<Train> trainListFromDb;
    private List<RuteRole> ruteRoleList;
    private List<Station> stationList;
    private List<MasterPlan> masterPlanList;
    private List<Peron> peronList;
    private List<RuteRole> ruteRolesFromDb;
    private List<Station> stationListFromDb;

    private final SaveTrainPort saveTrainPort;
    private final SaveRuteRolePort saveRuteRolePort;
    private final SaveStationPort saveStationPort;
    private final LoadTrainPort loadTrainPort;
    private final SavePeronPort savePeronPort;
    private final SaveScheduleTypePort saveTypeMasterPlan;
    private final SaveMasterPlanPort saveMasterPlanPort;
    private final LoadTypeMasterPlanPort loadTypeMasterPlanPort;
    private final LoadRuteRolePort loadRuteRolePort;
    private final LoadStationPort loadStationPort;
    private final DeleteMasterPlanPort deleteMasterPlanPort;
    private final LoadPeronPort loadPeronPort;

    private final  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
    private final SimpleDateFormat simpleDateFormatToSecond = new SimpleDateFormat("HH:mm:ss");

    @Override
    public void setMultipartFile(MultipartFile file) throws Exception {
        this.file = file;
    }

    @Override
    public void importDataPrep() throws Exception {
        try {
            saveToTemporaryFile();
            fetchTypeMasterPlan();
            fetchStations();
            fetchMasterPlanTrainAndLine();
            deleteTemporaryFile();
        } catch(Exception e) {
            throw new Exception("Gagal import data prep", e);
        }
    }

    private void saveToTemporaryFile() throws Exception {
        try {
            createFileNamePath();
            saveFile();
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan file ke temporary", e);
        }
    }

    private void createFileNamePath() {
        UUID uuid = UUID.randomUUID();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        fileStrPath = uuid.toString() + "-" + fileName;
    }

    private void saveFile() throws Exception {
        Path path = Paths.get(fileStrPath);
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
    }

    private void fetchTypeMasterPlan() throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(fileStrPath))) {
            String firstLine = br.readLine();
            String[] dataArr = firstLine.split(",");
            typeMasterPlanImport = trimText(dataArr[1]) ;
        } catch(Exception e) {
            throw new Exception("Gagal mengambil type master plan", e);
        }
    }

    private void fetchStations() throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(fileStrPath))) {
            int cnt = 0;
            String line;
            stationImportList = new ArrayList<>();
            while((line = br.readLine()) != null) {
                if(cnt == STATION_LINE_POSITION_IN_FILE) {
                    int cntLine = 2;
                    String[] arrData = line.split(",");
                    for(; cntLine < arrData.length; cntLine += 4) {
                        String station = trimText(arrData[cntLine]);
                        stationImportList.add(station);
                    }
                    break;
                } else {
                    cnt++;
                }
            }
        } catch(Exception e) {
            throw new Exception("Gagal mengambil stations", e);
        }
    }

    private void fetchMasterPlanTrainAndLine() throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(fileStrPath))) {
            trainImportList = new ArrayList<>();
            lineImportList = new ArrayList<>();
            masterPlanImportList = new ArrayList<>();
            int cnt = 0;
            String line;
            while((line = br.readLine()) != null) {
                if(cnt >= MASTERPLAN_LINE_POSITION_IN_FILE) {
                    String[] arrStr = line.split(",");
                    String train = trimText(arrStr[0].trim());
                    String lineRute = trimText(arrStr[1].trim());
                    if(!trainImportList.contains(train)) {
                        trainImportList.add(train);
                    }

                    if(!lineImportList.contains(lineRute)) {
                        lineImportList.add(lineRute);
                    }
                    int cntStation = 0;
                    for(int cntColumn = 2; cntColumn < arrStr.length; cntColumn += 4) {
                        String arrival = trimText(arrStr[cntColumn]);
                        if(!arrival.isEmpty() || (cntColumn + 1 < arrStr.length && !arrStr[cntColumn + 1].isEmpty())) {
                            String depart = "";
                            if(cntColumn + 1 < arrStr.length) {
                                depart = trimText(arrStr[cntColumn + 1]);
                            }
                            String platform = "0";
                            if(cntColumn + 2 < arrStr.length) {
                                platform = trimText(arrStr[cntColumn + 2]);
                            }
                            String flagNextDay = "0";
                            if(cntColumn + 3 < arrStr.length) {
                                flagNextDay = trimText(arrStr[cntColumn + 3]);
                            }

                            PeronImport peron = PeronImport.builder()
                                    .platform(platform.isEmpty() ? "0" : platform)
                                    .station(stationImportList.get(cntStation))
                                    .build();

                            MasterPlanImport masterPlan = MasterPlanImport.builder()
                                    .arrival(arrival)
                                    .depart(depart)
                                    .peron(peron)
                                    .flagNextDay(flagNextDay.compareTo("0") != 0)
                                    .train(train)
                                    .rute(lineRute)
                                    .build();

                            masterPlanImportList.add(masterPlan);
                        }
                        cntStation++;
                    }
                }
                cnt++;
            }
        } catch(Exception e) {
            throw new Exception("Gagal mengambil masterplan, train, dan line", e);
        }
    }

    public void deleteTemporaryFile() throws Exception {
        Path path = Paths.get(fileStrPath);
        Files.delete(path);
    }

    @Override
    public void saveToDatabase() throws Exception {
        try {
            saveNewData();
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan ke database", e);
        }
    }

    private void saveNewData() throws Exception {
        try {
            saveTypeMasterPlan();
            saveAllTrains();
            saveAllRuteRole();
            saveAllStation();
            saveAllMasterPlan();
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan semua data", e);
        }
    }

    private void saveTypeMasterPlan() throws Exception {
        Optional<TypeMasterPlan> optionalTypeMasterPlanFromDb = loadTypeMasterPlanPort.loadTypeMasterPlanByName(typeMasterPlanImport);
        if(optionalTypeMasterPlanFromDb.isEmpty()) {
            typeMasterPlan = TypeMasterPlan.builder()
                    .nameTypeMasterPlan(typeMasterPlanImport)
                    .build();
            Optional<TypeMasterPlan> optionalTypeMasterPlan = saveTypeMasterPlan.storeScheduleType(typeMasterPlan);
            typeMasterPlan = optionalTypeMasterPlan.orElse(null);
        } else {
            typeMasterPlan = optionalTypeMasterPlanFromDb.get();
        }
    }

    private void saveAllTrains() throws Exception {
        List<Train> trainListToAdd = new ArrayList<>();
        trainList = new ArrayList<>();
        Optional<List<Train>> optionalTrainList = loadTrainPort.loadAllTrain();
        trainListFromDb = optionalTrainList.orElse(new ArrayList<>());

        for (String str :trainImportList)
        {
            Optional<Train> optionalTrain = findTrainFromDb(str);
            if (optionalTrain.isEmpty())
            {
                Train train = Train.builder()
                        .noka(str)
                        .build();
                trainListToAdd.add(train);
            } else {
                trainList.add(optionalTrain.get());
            }
        }
        trainListToAdd = saveTrainPort.saveAllTrain(trainListToAdd);
        trainList.addAll(trainListToAdd);
    }

    private Optional<Train> findTrainFromDb(String trainStr) throws Exception {
        Train trainDb = null;
        for(Train train : trainListFromDb) {
            String noka = train.getNoka();
            if(noka != null && noka.compareToIgnoreCase(trainStr) == 0) {
                trainDb = train;
                break;
            }
        }
        return Optional.ofNullable(trainDb);
    }

    private void saveAllRuteRole() throws Exception {
        List<RuteRole> ruteRoleToAdd = new ArrayList();
        Optional<List<RuteRole>> optionalRuteRoles = loadRuteRolePort.loadAllRuteRole();
        ruteRolesFromDb = optionalRuteRoles.orElse(new ArrayList<>());

        ruteRoleList = new ArrayList<>();
        for(String strRuteRole : lineImportList) {
            Optional<RuteRole> optionalRuteRole = findRuteRoleFromDb(strRuteRole);
            if(optionalRuteRole.isEmpty()) {
                RuteRole ruteRole = RuteRole.builder()
                        .nameRoute(strRuteRole)
                        .build();
                ruteRoleToAdd.add(ruteRole);
            } else {
                ruteRoleList.add(optionalRuteRole.get());
            }
        }
        ruteRoleToAdd = saveRuteRolePort.saveAllRuteRole(ruteRoleToAdd);
        ruteRoleList.addAll(ruteRoleToAdd);
    }

    private Optional<RuteRole> findRuteRoleFromDb(String ruteRoleStr) throws Exception {
        RuteRole ruteRoleDb = null;
        for(RuteRole ruteRole : ruteRolesFromDb) {
            String ruteRoleName = ruteRole.getNameRoute();
            if(ruteRoleName != null && ruteRoleName.compareToIgnoreCase(ruteRoleStr) == 0) {
                ruteRoleDb = ruteRole;
                break;
            }
        }
        return Optional.ofNullable(ruteRoleDb);
    }

    private void saveAllStation() throws Exception {
        List<Station> stationListToAdd = new ArrayList<>();
        Optional<List<Station>> optionalStations = loadStationPort.loadAllStation();
        stationListFromDb = optionalStations.orElse(new ArrayList<>());
        stationList = new ArrayList<>();
        for(String strStation : stationImportList) {
            Optional<Station> optionalStation = findStationFromDb(strStation);
            if(optionalStation.isEmpty()) {
                Station station = Station.builder()
                        .nameStation(strStation)
                        .build();
                stationListToAdd.add(station);
            } else {
                stationList.add(optionalStation.get());
            }
        }
        stationListToAdd = saveStationPort.saveAllStation(stationListToAdd);
        stationList.addAll(stationListToAdd);
    }

    private Optional<Station> findStationFromDb(String stationStr) throws Exception {
        Station stationDb = null;
        for(Station station : stationListFromDb) {
            String name = station.getNameStation();
            if(name != null && name.compareToIgnoreCase(stationStr) == 0) {
                stationDb = station;
                break;
            }
        }
        return Optional.ofNullable(stationDb);
    }

    private void saveAllMasterPlan() throws Exception {
        deleteMasterPlansByTypeMasterPlan(typeMasterPlan);
        fetchAllPeronFromDb();
        masterPlanList = new ArrayList<>();
        for(MasterPlanImport masterPlanImport : masterPlanImportList) {
            String arrival = masterPlanImport.getArrival();
            String depart = masterPlanImport.getDepart();
            boolean nextDay = masterPlanImport.getFlagNextDay();
            PeronImport peronImport = masterPlanImport.getPeron();
            Date arrivalDate = parseStringToDate(arrival);
            Date departDate = parseStringToDate(depart);
            if(arrivalDate == null && departDate != null) {
                arrivalDate = departDate;
            }

            if(arrivalDate != null && departDate != null) {
                Optional<Peron> optionalPeron = findPeronFromDb(peronImport);
                Peron peron = null;
                if(optionalPeron.isEmpty()) {
                    Station station = findStation(peronImport.getStation());
                    int noPeron = 0;
                    if(peronImport.getPlatform() != null && !peronImport.getPlatform().isEmpty()) {
                        noPeron = Integer.parseInt(peronImport.getPlatform());
                    }
                    peron = Peron.builder()
                            .noPeron(noPeron)
                            .station(station)
                            .build();
                    peron = savePeronPort.savePeron(peron);
                } else {
                    peron = optionalPeron.get();
                }
                if(!peronList.contains(peron)) {
                    peronList.add(peron);
                }

                RuteRole ruteRole = findRuteRole(masterPlanImport.getRute());
                Train train = findTrain(masterPlanImport.getTrain());
                long timeDifference = Math.abs(arrivalDate.getTime() - departDate.getTime());

                MasterPlan masterPlan = MasterPlan.builder()
                        .arrival(arrivalDate)
                        .depart(departDate)
                        .flagMasterPlan(nextDay ? 1 : 0)
                        .typeMasterPlan(typeMasterPlan)
                        .peronFrom(peron)
                        .peronTo(peron)
                        .ruteRole(ruteRole)
                        .train(train)
                        .dwellingTime(timeDifference/1000)
                        .build();
                masterPlanList.add(masterPlan);
            }
        }

        saveMasterPlanPort.storeMasterPlanList(masterPlanList);
    }

    private Date parseStringToDate(String dateStr) throws Exception {
        if(dateStr == null)
            return null;

        Date parsedDate = null;
        if(!dateStr.isBlank() && dateStr.length() == 8) {
            parsedDate = simpleDateFormatToSecond.parse(dateStr);
        } else if(!dateStr.isBlank() && dateStr.length() == 5) {
            parsedDate = simpleDateFormat.parse(dateStr);
        }

        return parsedDate;
    }

    private void fetchAllPeronFromDb() throws Exception {
        Optional<List<Peron>> optionalPerons = loadPeronPort.loadAllPeron();
        peronList = optionalPerons.orElse(new ArrayList<>());
    }

    private void deleteMasterPlansByTypeMasterPlan(TypeMasterPlan typeMasterPlan) throws Exception {
        deleteMasterPlanPort.eraseAllMasterPlanByTypeMasterPlan(typeMasterPlan);
    }

    private Optional<Peron> findPeronFromDb(PeronImport peronImport) throws Exception {
        Optional<Peron> optionalPeron = peronList.stream()
                .filter(peron -> {
                    if(peronImport.getPlatform() != null && peronImport.getPlatform().isEmpty())
                        peronImport.setPlatform("0");
                    return (peron.getNoPeron() == Integer.parseInt(peronImport.getPlatform())
                            && peron.getStation().getNameStation().compareToIgnoreCase(peronImport.getStation()) == 0);
                })
                .findAny();
        return optionalPeron;
    }

    private Station findStation(String strStation) throws Exception {
        Station realStation = null;
        for(Station station : stationList) {
            if(station.getNameStation().compareToIgnoreCase(strStation) == 0) {
                realStation = station;
                break;
            }
        }
        return realStation;
    }

    private RuteRole findRuteRole(String strRuteRole) throws Exception {
        RuteRole realRuteRole = null;
        for(RuteRole ruteRole : ruteRoleList) {
            if(ruteRole.getNameRoute().compareToIgnoreCase(strRuteRole) == 0) {
                realRuteRole = ruteRole;
                break;
            }
        }
        return realRuteRole;
    }

    private Train findTrain(String strTrain) throws Exception {
        Train realTrain = null;
        for(Train train : trainList) {
            if(train.getNoka().compareToIgnoreCase(strTrain) == 0) {
                realTrain = train;
                break;
            }
        }
        return realTrain;
    }

    private String trimText(String text) {
        return (text != null ? text.replaceAll("^\"+|\"+$", "").toUpperCase() : "");
    }
}
