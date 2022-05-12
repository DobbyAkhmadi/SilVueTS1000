package len.silvue.webpendukung.gapeka.application.services;

import len.silvue.webpendukung.domains.ActualPlan;
import len.silvue.webpendukung.gapeka.adapter.out.web.GapekaDataDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.GapekaDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.GapekaStationDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.GapekaTrainDto;
import len.silvue.webpendukung.gapeka.application.port.in.GapekaUseCase;
import len.silvue.webpendukung.gapeka.application.port.out.LoadActualPlanPort;
import len.silvue.webpendukung.gapeka.application.port.out.LoadListLineDetailPort;
import len.silvue.webpendukung.gapeka.application.port.out.LoadListRuteDetailPort;
import len.silvue.webpendukung.domains.ListLineDetail;
import len.silvue.webpendukung.domains.ListRuteDetail;
import len.silvue.webpendukung.domains.StationDetail;
import len.silvue.webpendukung.domains.Train;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GapekaActualService implements GapekaUseCase {
    private final LoadActualPlanPort loadActualPlanPort;
    private final LoadListRuteDetailPort loadListRuteDetailPort;
    private final LoadListLineDetailPort loadListLineDetailPort;

    private Date minimumDate;
    private Date maximumDate;
    private List<Train> trainList;
    private Optional<GapekaDto> optionalGapekaDto = Optional.empty();
    private List<GapekaStationDto> gapekaStationDtoList;
    private List<GapekaTrainDto> gapekaTrainDtoList;
    private final Map<String, Integer> stationMap = new HashMap<>();
    private String lineName;
    private String actualCode;
    private Date actualCodeDate;
    private String typePlan;
    private String ruteRole;
    private List<ListRuteDetail> ruteDetailList;
    private List<ListLineDetail> lineDetailList;
    private List<String> stationStringList;
    private List<ActualPlan> actualPlans;
    private List<ActualPlan> matchStationActualPlans;
    private List<StationDetail> stationDetailList;

    public void setActualCode(String actualCode) throws Exception {
        this.actualCode = actualCode;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        actualCodeDate = sdf.parse(actualCode);
    }

    public void setTypePlan(String typePlan) {
        this.typePlan = typePlan;
    }

    public void setRuteRole(String ruteRole) {
        this.ruteRole = ruteRole;
    }

    @Override
    public void generateGapekaDto() throws Exception {
        try {
            fetchData();
            createGapekaStationDto();
            createGapekaTrainDto();
            createGapekaDto();
        } catch(Exception e) {
            throw new Exception("Gagal membuat gapeka Dto untuk route type", e);
        }
    }

    private void fetchData() throws Exception {
        fetchListRuteDetail();
        fetchListLineDetail();
        fetchStations();
        fetchTrains();
        fetchMinimumDate();
        fetchMaximumDate();
        fetchActualPlanByActualCodeAndTypePlanAndRuteRole();
    }

    private void fetchListRuteDetail() throws Exception {
        Optional<List<ListRuteDetail>> optionalListRuteDetails = loadListRuteDetailPort.loadListRuteDetailByRuteRoleName(ruteRole);
        ruteDetailList = optionalListRuteDetails.orElse(new ArrayList<>());
    }

    private void fetchListLineDetail() throws Exception {
        Optional<List<ListLineDetail>> optionalListLineDetails = loadListLineDetailPort.loadAllListByNameLine(ruteRole);
        lineDetailList = optionalListLineDetails.orElse(new ArrayList<>());
    }

    private void fetchStations() throws Exception {
        stationDetailList = new ArrayList<>();
        if(!ruteDetailList.isEmpty()) {
            ruteDetailList.forEach(ruteDetail -> {
                StationDetail stationDetail = StationDetail.builder()
                        .station(ruteDetail.getStation())
                        .indexLoc(ruteDetail.getIndexListRuteDetail())
                        .build();
                stationDetailList.add(stationDetail);
            });
        } else {
            lineDetailList.forEach((lineDetail -> {
                StationDetail stationDetail = StationDetail.builder()
                        .station(lineDetail.getStation())
                        .indexLoc(lineDetail.getIndexListLineDetail())
                        .build();
                stationDetailList.add(stationDetail);
            }));
        }
    }

    private void fetchTrains() throws Exception {
        Optional<List<String>> optionalTrainString = loadActualPlanPort.loadDistinctTrainOrderByActualCode(actualCodeDate);
        List<String> trainStringList = optionalTrainString.orElse(new ArrayList<>());
        trainList = trainStringList.stream()
                .map(trainString -> Train.builder().noka(trainString).build()).collect(Collectors.toList());
    }

    private void fetchMinimumDate() throws Exception {
        Optional<Date> optionalMinimumDate = loadActualPlanPort.loadMinimumArrivalActualPlanByActualCode(actualCodeDate);
        minimumDate = optionalMinimumDate.orElse(null);
    }

    private void fetchMaximumDate() throws Exception {
        Optional<Date> optionalMaximumDate = loadActualPlanPort.loadMaximumDepartActualPlanByActualCode(actualCodeDate);
        maximumDate = optionalMaximumDate.orElse(null);
    }

    private void fetchActualPlanByActualCodeAndTypePlanAndRuteRole() throws Exception {
        Optional<List<ActualPlan>> optionalActualPlans;
        if(!ruteDetailList.isEmpty()) {
            optionalActualPlans = loadActualPlanPort.loadActualPlanByActualCodeAndRuteRoleAndTypePlan(actualCodeDate, ruteRole, typePlan);
        } else {
            optionalActualPlans = loadActualPlanPort.loadActualPlanByActualCodeAndTypePlan(actualCodeDate, typePlan);
        }
        actualPlans = optionalActualPlans.orElse(new ArrayList<>());
    }

    private void createGapekaStationDto() throws Exception {
        gapekaStationDtoList = new ArrayList<>();
        stationDetailList.forEach(ruteDetail -> {
            int indexRuteDetail = ruteDetail.getIndexLoc();
            String stationName = ruteDetail.getStation().getNameStation();
            stationMap.put(stationName.toUpperCase(), indexRuteDetail);

            GapekaStationDto gapekaStationDto = GapekaStationDto.builder()
                    .stationPoint(indexRuteDetail)
                    .stationName(stationName)
                    .build();
            gapekaStationDtoList.add(gapekaStationDto);
        });

        matchStationActualPlans = actualPlans.stream()
                .filter(actualPlan -> {
                    boolean match = false;
                    for(GapekaStationDto gapekaStationDto : gapekaStationDtoList) {
                        if(actualPlan.getStatiunActualPlan().compareToIgnoreCase(gapekaStationDto.getStationName()) == 0) {
                            match = true;
                            break;
                        }
                    }
                    return match;
                })
                .collect(Collectors.toList());

    }

    private void createGapekaTrainDto() throws Exception {
        gapekaTrainDtoList = new ArrayList<>();
        for(Train train : trainList) {
            GapekaTrainDto gapekaTrainDto = new GapekaTrainDto();
            gapekaTrainDto.setTrainName(train.getNoka());
            List<GapekaDataDto> gapekaDataDtoList = new ArrayList<>();
            for(ActualPlan actualPlan : matchStationActualPlans) {
                if(actualPlan.getTrainActualPlan().compareToIgnoreCase(train.getNoka()) == 0) {
                    Date arriveDate = actualPlan.getArriveActualPlan();
                    Date departDate = actualPlan.getDepartActualPlan();
                    if(arriveDate != null) {
                        GapekaDataDto gapekaDataDtoArrival = new GapekaDataDto();
                        gapekaDataDtoArrival.setStationPoint(stationMap.get(actualPlan.getStatiunActualPlan().toUpperCase()));
                        gapekaDataDtoArrival.setDatePoint(arriveDate.getTime());
                        gapekaDataDtoList.add(gapekaDataDtoArrival);
                    }

                    if(departDate != null) {
                        GapekaDataDto gapekaDataDtoDepart = new GapekaDataDto();
                        gapekaDataDtoDepart.setStationPoint(stationMap.get(actualPlan.getStatiunActualPlan().toUpperCase()));
                        gapekaDataDtoDepart.setDatePoint(departDate.getTime());
                        gapekaDataDtoList.add(gapekaDataDtoDepart);
                    }
                }
            }
            Comparator<GapekaDataDto> compareGapekaDataDto = Comparator.comparing(GapekaDataDto::getDatePoint)
                    .thenComparing(GapekaDataDto::getStationPoint);
            List<GapekaDataDto>  gapekaDataDtosToView = gapekaDataDtoList.stream()
                    .sorted(compareGapekaDataDto)
                    .collect(Collectors.toList());
            gapekaTrainDto.setGapekaDataDtos(gapekaDataDtosToView);
            gapekaTrainDtoList.add(gapekaTrainDto);
        }
    }

    private void createGapekaDto() throws Exception {
        GapekaDto gapekaDto = GapekaDto.builder()
                .gapekaTrainDtos(gapekaTrainDtoList)
                .gapekaStationDtos(gapekaStationDtoList)
                .maxDate((maximumDate != null ? maximumDate.getTime() : 0))
                .minDate(minimumDate != null ? minimumDate.getTime() : 0)
                .title("ACTUAL - " + actualCode)
                .build();
        optionalGapekaDto = Optional.of(gapekaDto);
    }

    @Override
    public GapekaDto getGapekaDto() throws Exception {
        return optionalGapekaDto.orElseThrow(DataNotFoundException::new);
    }
}
