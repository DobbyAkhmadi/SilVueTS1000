package len.silvue.webpendukung.setting.application.services;

import len.silvue.webpendukung.domains.*;
import len.silvue.webpendukung.gapeka.application.port.out.*;
import len.silvue.webpendukung.schedule.application.port.out.*;
import len.silvue.webpendukung.setting.application.port.in.ImportSettingUseCase;
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
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImportSettingService implements ImportSettingUseCase {
    private MultipartFile file;
    private String fileStrPath;
    private List<Station> stationList;
    private List<ListLineDetail> listLineDetailList;
    private List<ListLineDetail> listLineDetailListToAdd;
    private List<ListRuteDetail> listRuteDetailList;
    private List<ListRuteDetail> listRuteDetailListToAdd;
    private List<Line> lines;
    private List<RuteRole> ruteRoleList;
    private Station station;
    private RuteRole ruteRole;
    private Line line;
    private String metric;
    private String location;
    // load all port
    private final LoadStationPort loadStationPort;
    private final LoadListLineDetailPort loadListLineDetailPort;
    private final LoadListRuteDetailPort loadListRuteDetailPort;
    private final LoadLinePort loadLinePort;
    private final LoadRuteRolePort loadRuteRolePort;
    private final SaveRuteRolePort saveRuteRolePort;
    private final SaveLinePort saveLinePort;
    private final SaveListLineDetailPort saveListLineDetailPort;
    private final SaveListRuteDetailPort saveListRuteDetailPort;

    @Override
    public void setMultipartFile(MultipartFile file) throws Exception {
        this.file = file;
    }
    @Override
    public void importData() throws Exception {
        try {
            saveToTemporaryFile();
            fetchData();
            fetchAllRelation();
            saveLine();
            saveRute();
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
        fileStrPath = uuid + "-" + fileName;
    }
    private void saveFile() throws Exception {
        Path path = Paths.get(fileStrPath);
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
    }
    public void deleteTemporaryFile() throws Exception {
        Path path = Paths.get(fileStrPath);
        log.info(path.toString());
        Files.delete(path);
    }

    private void fetchData() throws Exception {
        fetchAllStation();
        fetchAllListRuteDetail();
        fetchAllListLineDetail();
        fetchAllLineDetail();
        fetchAllRuteRole();
    }

    private void fetchAllListRuteDetail() throws Exception {
        Optional<List<ListRuteDetail>> optionalListRuteDetails = loadListRuteDetailPort.loadAllListRuteDetail();
        listRuteDetailList = optionalListRuteDetails.orElse(new ArrayList<>());
    }

    private void fetchAllStation() throws Exception {
        Optional<List<Station>> optionalStationList = loadStationPort.loadAllStation();
        stationList = optionalStationList.orElse(new ArrayList<>());
    }

    private void fetchAllListLineDetail() throws Exception {
        Optional<List<ListLineDetail>> optionalListLineDetails = loadListLineDetailPort.loadAllListLineDetail();
        listLineDetailList = optionalListLineDetails.orElse(new ArrayList<>());
    }

    private void fetchAllLineDetail() throws Exception {
        Optional<List<Line>> optionalLines = loadLinePort.loadAllLine();
        lines = optionalLines.orElse(new ArrayList<>());
    }

    private void fetchAllRuteRole() throws Exception {
        Optional<List<RuteRole>> optionalRuteRoles = loadRuteRolePort.loadAllRuteRole();
        ruteRoleList = optionalRuteRoles.orElse(new ArrayList<>());
    }

    private void fetchAllRelation() throws Exception {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileStrPath))) {
            int count = 0 ;
            String lineBuffReader = null;
            listRuteDetailListToAdd = new ArrayList<>();
            listLineDetailListToAdd = new ArrayList<>();
            while ((lineBuffReader = bufferedReader.readLine()) != null) {
                if(count != 0) {
                    // split all csv
                    String[] arrData = lineBuffReader.split(",");
                    station = findStationByStationText(arrData[0]);
                    String type = arrData[1].replaceAll("^\"+|\"+$", "");
                    String typeName = arrData[2].replaceAll("^\"+|\"+$", "");
                    metric = arrData[3].replaceAll("^\"+|\"+$", "");
                    location = arrData[4].replaceAll("^\"+|\"+$", "");
                    if(type.compareToIgnoreCase("line") == 0) {
                        line = findLineByLineText(typeName);
                        if(line == null) {
                            line = saveNewLine(typeName);
                        }
                        addLineToListLine();
                    } else {
                        ruteRole = findRuteRoleByRuteRoleText(typeName);
                        if(ruteRole == null) {
                            log.info("RuteRole null");
                            ruteRole = saveNewRuteRole(typeName);
                        }
                        addRuteRoleToListRuteDetail();
                    }
                } else {
                    count++;
                }
            }
        } catch(Exception e) {
            throw new Exception("failed to get stations", e);
        }
    }

    private Station findStationByStationText(String stationText) throws Exception {
        if(stationText == null)
            return null;
        String stationTextCleanTrim = stationText.replaceAll("^\"+|\"+$", "");
        Station stationInListRute = null;
        for(Station stationInDb : stationList) {
            if(stationInDb.getNameStation().compareToIgnoreCase(stationTextCleanTrim) == 0) {
                stationInListRute = stationInDb;
                break;
            }
        }
        return stationInListRute;
    }

    private Line findLineByLineText(String lineText) throws Exception {
        if(lineText == null)
            return null;
        Line line = null;
        String lineTextCleanTrim = lineText.replaceAll("^\"+|\"+$", "");
        for(Line lineInDb : lines) {
            if(lineInDb.getNameLine().compareToIgnoreCase(lineTextCleanTrim) == 0) {
                line = lineInDb;
                break;
            }
        }
        return line;
    }

    private RuteRole findRuteRoleByRuteRoleText(String ruteRoleText) throws Exception {
        RuteRole ruteRole = null;
        for(RuteRole ruteRoleInDb : ruteRoleList) {
            if(ruteRoleInDb.getNameRoute().compareToIgnoreCase(ruteRoleText) == 0) {
                ruteRole = ruteRoleInDb;
                break;
            }
        }
        return ruteRole;
    }

    private void saveLine() throws Exception {
        saveListLineDetailPort.storeAllListLine(listLineDetailListToAdd);
    }

    private void saveRute() throws Exception {
        saveListRuteDetailPort.storeAllListRoute(listRuteDetailListToAdd);
    }

    private Line saveNewLine(String lineText) throws Exception {
        Line line = Line.builder()
                .nameLine(lineText)
                .build();
        return saveLinePort.save(line);
    }

    private RuteRole saveNewRuteRole(String rute) throws Exception {
        RuteRole ruteRole = RuteRole.builder()
                .nameRoute(rute)
                .build();

        return saveRuteRolePort.saveRuteRole(ruteRole);
    }

    private void addLineToListLine() throws Exception {
        boolean exist = isLineExist();

        if(!exist) {
            ListLineDetail listLineDetail = ListLineDetail.builder()
                    .line(line)
                    .indexListLineDetail(Integer.parseInt(location))
                    .locUnitLine(metric)
                    .station(station)
                    .build();
            listLineDetailListToAdd.add(listLineDetail);
        }
    }

    private boolean isLineExist() throws Exception {
       boolean exist = false;
        for(ListLineDetail listLineDetail : listLineDetailList) {
            if(listLineDetail.getLine().getIdLine() == line.getIdLine()
                    && listLineDetail.getStation().getIdStation() == station.getIdStation()) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    private void addRuteRoleToListRuteDetail() throws Exception {
        boolean exist = isRuteExists();
        if(!exist) {
            ListRuteDetail listRuteDetail = ListRuteDetail.builder()
                    .ruteRole(ruteRole)
                    .indexListRuteDetail(Integer.parseInt(location))
                    .locUnitRute(metric)
                    .station(station)
                    .build();
            listRuteDetailListToAdd.add(listRuteDetail);
        }
    }

    private boolean isRuteExists() throws Exception {
        boolean exist = false;
        for(ListRuteDetail listRuteDetail : listRuteDetailList) {
            if(listRuteDetail.getRuteRole().getIdRuteRole() == ruteRole.getIdRuteRole()
                && (listRuteDetail.getStation() != null && station != null && listRuteDetail.getStation().getIdStation() == station.getIdStation())) {
                exist = true;
                break;
            }
        }
        return exist;
    }
}
