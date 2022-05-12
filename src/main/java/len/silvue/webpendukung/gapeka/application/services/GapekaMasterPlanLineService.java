package len.silvue.webpendukung.gapeka.application.services;

import len.silvue.webpendukung.gapeka.adapter.out.web.GapekaDataDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.GapekaDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.GapekaStationDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.GapekaTrainDto;
import len.silvue.webpendukung.gapeka.application.port.in.GapekaUseCase;
import len.silvue.webpendukung.gapeka.application.port.out.LoadListLinePort;
import len.silvue.webpendukung.domains.ListLine;
import len.silvue.webpendukung.schedule.application.port.out.LoadMasterPlanPort;
import len.silvue.webpendukung.domains.MasterPlan;
import len.silvue.webpendukung.domains.Train;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class GapekaMasterPlanLineService implements GapekaUseCase {
    private final LoadMasterPlanPort loadMasterPlanPort;
    private final LoadListLinePort loadListLinePort;

    private List<MasterPlan> masterPlanList;
    private List<ListLine> listLines;
    private List<MasterPlan> masterPlansBasedOnListLines;
    private int typeMasterPlanId;
    private int lineId;
    private Date minimumDate;
    private Date maximumDate;
    private List<Train> trainList;
    private Optional<GapekaDto> optionalGapekaDto = Optional.empty();
    private List<GapekaStationDto> gapekaStationDtoList;
    private List<GapekaTrainDto> gapekaTrainDtoList;
    private final Map<Integer, Integer> stationMap = new HashMap<>();
    private String lineName;

    public void setLineId(int id) {
        lineId = id;
    }

    public void setTypeMasterPlanId(int id) {
        typeMasterPlanId = id;
    }

    @Override
    public void generateGapekaDto() throws Exception {
        try {
            fetchData();
            createGapekaStationDto();
            createGapekaTrainDto();
            createGapekaDto();
        } catch(Exception e){
            throw new Exception("Gagal men generate Gapeka", e);
        }
    }

    private void createGapekaTrainDto() throws Exception {
        gapekaTrainDtoList = new ArrayList<>();
        for(Train train : trainList) {
            GapekaTrainDto gapekaTrainDto = new GapekaTrainDto();
            gapekaTrainDto.setTrainName(train.getNoka());
            List<GapekaDataDto> gapekaDataDtoList = new ArrayList<>();
            gapekaTrainDto.setGapekaDataDtos(gapekaDataDtoList);
            for(MasterPlan masterPlan : masterPlansBasedOnListLines) {
                if(train.getIdTrain() == masterPlan.getTrain().getIdTrain()) {
                    GapekaDataDto gapekaDataDtoArrival = new GapekaDataDto();
                    gapekaDataDtoArrival.setStationPoint(stationMap.get(masterPlan.getPeronFrom().getStation().getIdStation()));
                    gapekaDataDtoArrival.setDatePoint(masterPlan.getArrival().getTime());
                    gapekaDataDtoList.add(gapekaDataDtoArrival);

                    GapekaDataDto gapekaDataDtoDepart = new GapekaDataDto();
                    gapekaDataDtoDepart.setStationPoint(stationMap.get(masterPlan.getPeronFrom().getStation().getIdStation()));
                    gapekaDataDtoDepart.setDatePoint(masterPlan.getDepart().getTime());
                    gapekaDataDtoList.add(gapekaDataDtoDepart);
                }
            }
            gapekaTrainDtoList.add(gapekaTrainDto);
        }
    }

    private void createGapekaDto() throws Exception {
        if(!listLines.isEmpty()) {
            lineName = listLines.get(0).getLine().getNameLine();
        }
        GapekaDto gapekaDto = GapekaDto.builder()
                .gapekaStationDtos(gapekaStationDtoList)
                .gapekaTrainDtos(gapekaTrainDtoList)
                .maxDate(maximumDate.getTime())
                .minDate(minimumDate.getTime())
                .title("MASTERPLAN - LINE - " + lineName)
                .build();
        optionalGapekaDto = Optional.of(gapekaDto);
    }

    private void createGapekaStationDto() throws Exception {
        gapekaStationDtoList = new ArrayList<>();
        for(ListLine listLine : listLines) {
            GapekaStationDto gapekaStationDto = new GapekaStationDto();
            gapekaStationDto.setStationId(listLine.getStation().getIdStation());
            gapekaStationDto.setStationName(listLine.getStation().getNameStation());
            gapekaStationDto.setStationPoint(listLine.getListLineNumber());
            gapekaStationDtoList.add(gapekaStationDto);
            stationMap.put(listLine.getStation().getIdStation(), listLine.getListLineNumber());
        }
    }

    private void fetchData() throws Exception {
        fetchListLine();
        fetchMasterPlanByListLine();
        fetchMinimumDate();
        fetchMaximumDate();
        fetchTrains();
    }

    private void fetchListLine() throws Exception {
        Optional<List<ListLine>> optionalListLines = loadListLinePort.loadListLineByLineId(lineId);
        listLines = optionalListLines.orElseThrow(DataNotFoundException::new);
    }

    private void fetchMasterPlanByListLine() throws Exception {
        masterPlansBasedOnListLines = new ArrayList<>();
        Optional<List<MasterPlan>> optionalMasterPlans = loadMasterPlanPort.loadAllMasterPlanByTypeMasterPlan(typeMasterPlanId);
        masterPlanList = optionalMasterPlans.orElseThrow(DataNotFoundException::new);
        for(MasterPlan masterPlan : masterPlanList) {
            for(ListLine listLine : listLines) {
                if(masterPlan.getPeronFrom().getStation().getIdStation() == listLine.getStation().getIdStation()) {
                    masterPlansBasedOnListLines.add(masterPlan);
                }
            }
        }
    }

    private void fetchMinimumDate() throws Exception {
        minimumDate = new Date();
        for(MasterPlan masterPlan : masterPlansBasedOnListLines) {
            if(masterPlan.getArrival().getTime() < minimumDate.getTime()) {
                minimumDate = masterPlan.getArrival();
            }
        }
    }

    private void fetchMaximumDate() throws Exception {
        maximumDate = new GregorianCalendar(1960, Calendar.JANUARY, 1).getTime();
        for(MasterPlan masterPlan : masterPlansBasedOnListLines) {
            if(masterPlan.getDepart().getTime() > maximumDate.getTime()) {
                maximumDate = masterPlan.getDepart();
            }
        }
    }

    private void fetchTrains() throws Exception {
        trainList = new ArrayList<>();
        int idTrain = 0;
        for(MasterPlan masterPlan : masterPlansBasedOnListLines) {
            if(!trainList.contains(masterPlan.getTrain())) {
                trainList.add(masterPlan.getTrain());
            }
        }
    }

    @Override
    public GapekaDto getGapekaDto() throws Exception {
        return optionalGapekaDto.orElseThrow(DataNotFoundException::new);
    }
}
