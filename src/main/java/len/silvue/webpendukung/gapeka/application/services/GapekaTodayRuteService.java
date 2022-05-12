package len.silvue.webpendukung.gapeka.application.services;

import len.silvue.webpendukung.domains.*;
import len.silvue.webpendukung.gapeka.adapter.out.web.GapekaDataDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.GapekaDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.GapekaStationDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.GapekaTrainDto;
import len.silvue.webpendukung.gapeka.application.port.in.GapekaUseCase;
import len.silvue.webpendukung.gapeka.application.port.out.LoadListRuteDetailPort;
import len.silvue.webpendukung.schedule.application.port.out.LoadMasterPlanPort;
import len.silvue.webpendukung.schedule.application.port.out.LoadRuteRolePort;
import len.silvue.webpendukung.schedule.application.port.out.LoadTypeMasterPlanPort;
import len.silvue.webpendukung.todays.application.port.out.LoadTodayDetailPort;
import len.silvue.webpendukung.domains.TodayRunningSchedule;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class GapekaTodayRuteService implements GapekaUseCase {
    private final LoadTodayDetailPort loadTodayDetailPort;
    private final LoadRuteRolePort loadRuteRolePort;
    private final LoadMasterPlanPort loadMasterPlanPort;
    private final LoadListRuteDetailPort loadListRuteDetailPort;
    private final LoadTypeMasterPlanPort loadTypeMasterPlanPort;

    private List<TodayRunningSchedule> todayRunningScheduleList;
    private List<TodayRunningSchedule> todayRunningScheduleByRuteList;
    private List<Train> trainList;
    private List<Station> stationList;
    private TypeMasterPlan typeMasterPlan;
    private RuteRole ruteRole;
    private List<ListRuteDetail> ruteDetailList;
    private Date minimumDate;
    private Date maximumDate;
    private int ruteRoleId;
    private int typeMasterPlanId;
    private List<GapekaStationDto> gapekaStationDtoList;
    private final Map<Integer, Integer> stationMap = new HashMap<>();
    private List<GapekaTrainDto> gapekaTrainDtoList;
    private Optional<GapekaDto> optionalGapekaDto = Optional.empty();
    private String title;

    public void setRuteRoleId(int ruteRoleId) {
        this.ruteRoleId = ruteRoleId;
    }

    public void setTypeMasterPlanId(int typeMasterPlanId) {
        this.typeMasterPlanId = typeMasterPlanId;
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
        fetchRuteListByRuteRole();
        fetchTypeMasterPlan();
        fetchRuteRole();
        fetchTodayRunningScheduleByRuteList();
        fetchMinimumDate();
        fetchMaximumDate();
        fetchTrains();
        fetchStations();
    }

    private void fetchTodayRunningScheduleByRuteList() throws Exception {
        todayRunningScheduleByRuteList = new ArrayList<>();
        Optional<List<TodayRunningSchedule>> optionalTodayRunningSchedules = loadTodayDetailPort.loadTodayRunningScheduleByTypeMasterPlanIdAndRuteRoleId(typeMasterPlanId, ruteRoleId);
        todayRunningScheduleList = optionalTodayRunningSchedules.orElseThrow(DataNotFoundException::new);
        for(TodayRunningSchedule todayRunningSchedule : todayRunningScheduleList) {
            for(ListRuteDetail listRuteDetail : ruteDetailList) {
                if(todayRunningSchedule.getPeronFrom().getStation().getIdStation() == listRuteDetail.getStation().getIdStation()) {
                    todayRunningScheduleByRuteList.add(todayRunningSchedule);
                }
            }
        }
    }

    private void fetchStations() throws Exception {
        Optional<List<Station>> optionalStations = loadMasterPlanPort.loadStationsFromMasterPlanByTypeMasterPlanAndRuteRole(typeMasterPlan, ruteRole);
        stationList = optionalStations.orElseThrow(DataNotFoundException::new);
    }

    private void fetchTrains() throws Exception {
        trainList = new ArrayList<>();
        todayRunningScheduleByRuteList.forEach(todayRunningSchedule -> {
            Train train = todayRunningSchedule.getTrain();
            if(!trainList.contains(train)) {
                trainList.add(train);
            }
        });
    }

    private void fetchMaximumDate() throws Exception {
        maximumDate = new GregorianCalendar(1960, Calendar.JANUARY, 1).getTime();
        for(TodayRunningSchedule todayRunningSchedule : todayRunningScheduleByRuteList) {
            Date departDate = todayRunningSchedule.getDepart();
            if(departDate != null && departDate.getTime() > maximumDate.getTime()) {
                maximumDate = todayRunningSchedule.getDepart();
            }
        }
    }

    private void fetchMinimumDate() throws Exception {
        minimumDate = new Date();
        for(TodayRunningSchedule todayRunningSchedule : todayRunningScheduleByRuteList) {
            Date arrivalDate = todayRunningSchedule.getArrival();
            if(arrivalDate != null && arrivalDate.getTime() < minimumDate.getTime()) {
                minimumDate = todayRunningSchedule.getArrival();
            }
        }
    }

    private void fetchRuteListByRuteRole() throws Exception {
        Optional<List<ListRuteDetail>> optionalListRuteDetails = loadListRuteDetailPort.loadListRuteDetailByRuteRoleId(ruteRoleId);
        ruteDetailList = optionalListRuteDetails.orElseThrow(DataNotFoundException::new);
    }

    private void fetchTypeMasterPlan() throws Exception {
        Optional<TypeMasterPlan> optionalTypeMasterPlan = loadTypeMasterPlanPort.loadTypeMasterPlanById(typeMasterPlanId);
        typeMasterPlan = optionalTypeMasterPlan.orElseThrow(DataNotFoundException::new);
    }

    private void fetchRuteRole() throws Exception {
        Optional<RuteRole> optionalRuteRole = loadRuteRolePort.loadRuteRoleById(ruteRoleId);
        ruteRole = optionalRuteRole.orElseThrow(DataNotFoundException::new);
    }

    private void createGapekaStationDto() {
        gapekaStationDtoList = new ArrayList<>();
        ruteDetailList.forEach(ruteDetail -> {
            for(Station stationFromMasterPlan : stationList) {
                if(stationFromMasterPlan.getIdStation() == ruteDetail.getStation().getIdStation()) {
                    int stationPoint = ruteDetail.getIndexListRuteDetail();
                    Station station = ruteDetail.getStation();
                    GapekaStationDto gapekaStationDto = GapekaStationDto.builder()
                            .stationPoint(stationPoint)
                            .stationName(station.getNameStation())
                            .build();
                    gapekaStationDtoList.add(gapekaStationDto);
                    stationMap.put(station.getIdStation(), stationPoint);
                    break;
                }
            }
        });
    }

    private void createGapekaTrainDto() {
        gapekaTrainDtoList = new ArrayList<>();
        for(Train train : trainList) {
            GapekaTrainDto gapekaTrainDto = new GapekaTrainDto();
            gapekaTrainDto.setTrainName(train.getNoka());
            List<GapekaDataDto> gapekaDataDtoList = new ArrayList<>();
            gapekaTrainDto.setGapekaDataDtos(gapekaDataDtoList);
            for(TodayRunningSchedule todayRunningSchedule : todayRunningScheduleList) {
                if(train.getIdTrain() == todayRunningSchedule.getTrain().getIdTrain()) {
                    GapekaDataDto gapekaDataDtoArrival = new GapekaDataDto();
                    Date arrivalDate = todayRunningSchedule.getArrival();
                    gapekaDataDtoArrival.setStationPoint(stationMap.get(todayRunningSchedule.getPeronFrom().getStation().getIdStation()));
                    if(arrivalDate != null) {
                        gapekaDataDtoArrival.setDatePoint(arrivalDate.getTime());
                    }
                    gapekaDataDtoList.add(gapekaDataDtoArrival);

                    GapekaDataDto gapekaDataDtoDepart = new GapekaDataDto();
                    Date departDate = todayRunningSchedule.getDepart();
                    gapekaDataDtoDepart.setStationPoint(stationMap.get(todayRunningSchedule.getPeronFrom().getStation().getIdStation()));
                    if(departDate != null) {
                        gapekaDataDtoDepart.setDatePoint(departDate.getTime());
                    }
                    gapekaDataDtoList.add(gapekaDataDtoDepart);
                }
            }
            gapekaTrainDtoList.add(gapekaTrainDto);
        }
    }

    private void createGapekaDto() {
        String rute = ruteRole.getNameRoute();
        GapekaDto gapekaDto = GapekaDto.builder()
                .gapekaStationDtos(gapekaStationDtoList)
                .gapekaTrainDtos(gapekaTrainDtoList)
                .maxDate(maximumDate.getTime())
                .minDate(minimumDate.getTime())
                .title("TODAY - RUTE - " + rute)
                .build();
        optionalGapekaDto = Optional.of(gapekaDto);
    }

    @Override
    public GapekaDto getGapekaDto() throws Exception {
        return optionalGapekaDto.orElseThrow(DataNotFoundException::new);
    }
}
