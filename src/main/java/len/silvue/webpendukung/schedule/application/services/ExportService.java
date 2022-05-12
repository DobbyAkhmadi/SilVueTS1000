package len.silvue.webpendukung.schedule.application.services;

import com.opencsv.CSVWriter;
import len.silvue.webpendukung.schedule.adapter.out.web.ExportMasterPlanDto;
import len.silvue.webpendukung.schedule.adapter.out.web.MasterScheduleDto;
import len.silvue.webpendukung.schedule.adapter.out.web.StationDto;
import len.silvue.webpendukung.schedule.adapter.out.web.TrainScheduleDto;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.StationMapper;
import len.silvue.webpendukung.schedule.application.port.in.ExportUseCase;
import len.silvue.webpendukung.schedule.application.port.out.LoadMasterPlanPort;
import len.silvue.webpendukung.schedule.application.port.out.LoadStationPort;
import len.silvue.webpendukung.domains.MasterPlan;
import len.silvue.webpendukung.domains.Station;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExportService implements ExportUseCase {
    private final LoadMasterPlanPort loadMasterPlanPort;
    private final LoadStationPort loadStationPort;
    private final StationMapper stationMapper;

    private List<MasterPlan> masterPlanList;
    private List<Station> stationList;
    private String typeMasterPlan;
    private List<MasterScheduleDto> masterScheduleList;
    private ExportMasterPlanDto exportMasterPlanDto;
    private CSVWriter csvWriter;

    @Override
    public void generateMasterSchedule(int idTypeMasterPlan) throws Exception {
        try {
            masterPlanList = getMasterPlanByTypeMasterPlan(idTypeMasterPlan);
            stationList = getAllStation();
            typeMasterPlan = (masterPlanList.size() > 0 ? masterPlanList.get(0).getTypeMasterPlan().getNameTypeMasterPlan() : "");
            generateMasterScheduleFromMasterPlan();
            createExportMasterPlan();
        } catch(Exception e) {
            throw new Exception("Gagal menghasilkan data export", e);
        }
    }

    @Override
    public void writeCsv(Writer writer) throws Exception {
        try {
            csvWriter = new CSVWriter(writer);

            writeTypeMasterPlan();

            writeStationHeader();

            writeStations();

            writeStationHeaderTitle();

            writeSchedules();

        } catch(Exception e) {
            throw new Exception("Gagal mengirimkan csv", e);
        }
    }

    private void writeTypeMasterPlan() throws Exception {
        String[] firstLine = {"Type", exportMasterPlanDto.getTypeMasterPlan()};
        csvWriter.writeNext(firstLine);
    }

    private void writeStations() throws Exception {
        List<StationDto> stationList = exportMasterPlanDto.getStationList();
        String[] stationsLine = new String[2 + stationList.size() * 4];

        int counterStationLine = 0;
        stationsLine[counterStationLine++] = " ";
        stationsLine[counterStationLine++] = " ";
        for(int i = 0; i < stationList.size(); i++){
            stationsLine[counterStationLine++] = stationList.get(i).getNameStation();
            stationsLine[counterStationLine++] = " ";
            stationsLine[counterStationLine++] = " ";
            stationsLine[counterStationLine++] = " ";
        }
        csvWriter.writeNext(stationsLine);
    }

    private void writeStationHeaderTitle() throws Exception {
        List<StationDto> stationList = exportMasterPlanDto.getStationList();
        String[] stationsLine = new String[2 + stationList.size() * 4];

        int counterStationLine = 0;
        stationsLine[counterStationLine++] = "Train";
        stationsLine[counterStationLine++] = "Route";
        for(int i = 0; i < stationList.size(); i++){
            stationsLine[counterStationLine++] = "Arrival";
            stationsLine[counterStationLine++] = "Depart";
            stationsLine[counterStationLine++] = "Platform";
            stationsLine[counterStationLine++] = "Flag Next Day";
        }
        csvWriter.writeNext(stationsLine);
    }

    private void writeStationHeader() throws Exception {
        csvWriter.writeNext(new String[]{" ", " ", "Stations"});
    }

    private void writeSchedules() throws Exception {
        List<MasterScheduleDto> masterSchedules = exportMasterPlanDto.getMasterScheduleList();
        String[] dataNextLine = new String[2 + exportMasterPlanDto.getStationList().size() * 4];

        for(MasterScheduleDto masterSchedule : masterSchedules) {
            int counterDataNextLine = 0;
            dataNextLine[counterDataNextLine++] = masterSchedule.getNoka();
            dataNextLine[counterDataNextLine++] = masterSchedule.getNameRoute();
            List<TrainScheduleDto> trainScheduleDtoList = masterSchedule.getTrainScheduleDtoList();
            for(TrainScheduleDto trainScheduleDto : trainScheduleDtoList) {
                dataNextLine[counterDataNextLine++] = trainScheduleDto.getArrivalTime();
                dataNextLine[counterDataNextLine++] = trainScheduleDto.getDepartureTime();
                dataNextLine[counterDataNextLine++] = String.valueOf(trainScheduleDto.getPlatform());
                dataNextLine[counterDataNextLine++] = String.valueOf(trainScheduleDto.getFlagMasterPlan());
            }
            csvWriter.writeNext(dataNextLine);
        }

        csvWriter.close();
    }

    private List<MasterPlan> getMasterPlanByTypeMasterPlan(int idTypeMasterPlan) throws Exception {
        Optional<List<MasterPlan>> optionalMasterPlanList = loadMasterPlanPort.loadAllMasterPlanByTypeMasterPlan(idTypeMasterPlan);
        return optionalMasterPlanList.orElse(new ArrayList<>());
    }

    private List<Station> getAllStation() throws Exception {
        Optional<List<Station>> optionalStationList = loadStationPort.loadAllStation();
        return optionalStationList.orElse(new ArrayList<>());
    }

    private void generateMasterScheduleFromMasterPlan() throws Exception {
        int currIdTrain = 0;
        int currIdRute = 0;
        MasterScheduleDto masterScheduleDto = null;
        masterScheduleList = new ArrayList<>();
        for(MasterPlan masterPlan : masterPlanList) {
            if(currIdTrain != masterPlan.getTrain().getIdTrain() || currIdRute != masterPlan.getRuteRole().getIdRuteRole()) {
                masterScheduleDto = new MasterScheduleDto();
                masterScheduleList.add(masterScheduleDto);
                masterScheduleDto.setTrainScheduleDtoList(generateTrainSchedule());
                masterScheduleDto.setNameTypeMasterPlan(masterPlan.getTypeMasterPlan().getNameTypeMasterPlan());
                masterScheduleDto.setNameRoute(masterPlan.getRuteRole().getNameRoute());
                masterScheduleDto.setNoka(masterPlan.getTrain().getNoka());
                currIdTrain = masterPlan.getTrain().getIdTrain();
                currIdRute = masterPlan.getRuteRole().getIdRuteRole();
            }

            if(masterScheduleDto != null) {
                masterScheduleDto.getTrainScheduleDtoList().forEach(trainScheduleDto -> {
                    if (trainScheduleDto.getIdStation() == masterPlan.getIdMasterPlan()) {
                        trainScheduleDto.setFlagMasterPlan(masterPlan.getFlagMasterPlan());
                        trainScheduleDto.setArrivalTime(masterPlan.getArrival().toString());
                        trainScheduleDto.setDepartureTime(masterPlan.getArrival().toString());
                    }
                });
            }
        }
    }

    private List<TrainScheduleDto> generateTrainSchedule() throws Exception {
        List<TrainScheduleDto> trainScheduleList = new ArrayList<>();
        for(Station station : stationList) {
            TrainScheduleDto trainScheduleDto = new TrainScheduleDto();
            trainScheduleDto.setIdStation(station.getIdStation());
            trainScheduleDto.setStationName(station.getNameStation());
            trainScheduleDto.setDepartureTime("-");
            trainScheduleDto.setArrivalTime("-");
            trainScheduleDto.setFlagMasterPlan(0);
            trainScheduleList.add(trainScheduleDto);
        }
        return trainScheduleList;
    }

    private void createExportMasterPlan() throws Exception {
        exportMasterPlanDto = new ExportMasterPlanDto();
        exportMasterPlanDto.setTypeMasterPlan(typeMasterPlan);
        exportMasterPlanDto.setMasterScheduleList(masterScheduleList);
        exportMasterPlanDto.setStationList(stationMapper.toStationDtoList(stationList));
    }
}
