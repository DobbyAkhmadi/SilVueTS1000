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
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@RequiredArgsConstructor
@Component
@Slf4j
public class GapekaMasterPlanRuteService implements GapekaUseCase {
    private final LoadTypeMasterPlanPort loadTypeMasterPlanPort;
    private final LoadRuteRolePort loadRuteRolePort;
    private final LoadMasterPlanPort loadMasterPlanPort;
    private final LoadListRuteDetailPort loadListRuteDetailPort;

    private int ruteRoleId;
    private int typeMasterPlanId;
    private TypeMasterPlan typeMasterPlan;
    private RuteRole ruteRole;
    private List<ListRuteDetail> ruteDetailList;
    private Date minimumDate;
    private Date maximumDate;
    private final List<GapekaStationDto> gapekaStationDtoList = new ArrayList<>();
    private final List<GapekaTrainDto> gapekaTrainDtoList = new ArrayList<>();
    private List<Train> trainList;
    private List<Station> stationList;
    private final Map<Integer, Integer> stationMap = new HashMap<>();
    private Optional<GapekaDto> gapekaDtoOptional = Optional.empty();

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

    @Override
    public GapekaDto getGapekaDto() throws Exception {
        try {
            return gapekaDtoOptional.orElseThrow(DataNotFoundException::new);
        } catch(DataNotFoundException de) {
            throw new Exception("Data Gapeka Kosong", de);
        }
    }

    private void createGapekaDto() throws Exception {
        GapekaDto gapekaDto = GapekaDto.builder()
                .gapekaStationDtos(gapekaStationDtoList)
                .gapekaTrainDtos(gapekaTrainDtoList)
                .maxDate(maximumDate.getTime())
                .minDate(minimumDate.getTime())
                .title("MASTERPLAN - RUTE - " + ruteRole.getNameRoute())
                .build();
        gapekaDtoOptional = Optional.of(gapekaDto);
    }

    private void createGapekaTrainDto() throws Exception {
        gapekaTrainDtoList.clear();
        for (Train train : trainList) {
            GapekaTrainDto gapekaTrainDto = new GapekaTrainDto();
            gapekaTrainDto.setTrainName(train.getNoka());
            Optional<List<MasterPlan>> optionalMasterPlanList = loadMasterPlanPort.loadMasterPlanByTypeMasterPlanAndRuteRoleAndTrain(typeMasterPlan, ruteRole, train);
            List<MasterPlan> masterPlanList = optionalMasterPlanList.orElseThrow(DataNotFoundException::new);
            List<GapekaDataDto> gapekaDataDtoList = new ArrayList<>();
            for(MasterPlan masterPlan : masterPlanList) {
                ruteDetailList.forEach(ruteDetail -> {
                    if(ruteDetail.getStation().getIdStation() == masterPlan.getPeronFrom().getStation().getIdStation()) {
                        GapekaDataDto gapekaDataDtoArrival = new GapekaDataDto();
                        gapekaDataDtoArrival.setStationPoint(stationMap.get(masterPlan.getPeronFrom().getStation().getIdStation()));
                        long arrivalTime = 0;
                        if(masterPlan.getArrival() != null) {
                            arrivalTime = masterPlan.getArrival().getTime();
                        }
                        gapekaDataDtoArrival.setDatePoint(arrivalTime);
                        gapekaDataDtoList.add(gapekaDataDtoArrival);

                        GapekaDataDto gapekaDataDtoDepart = new GapekaDataDto();
                        gapekaDataDtoDepart.setStationPoint(stationMap.get(masterPlan.getPeronTo().getStation().getIdStation()));
                        long departTime = 0;
                        if(masterPlan.getDepart() != null) {
                            departTime = masterPlan.getDepart().getTime();
                        }
                        gapekaDataDtoDepart.setDatePoint(departTime);
                        gapekaDataDtoList.add(gapekaDataDtoDepart);
                    }
                });
            }
            gapekaTrainDto.setGapekaDataDtos(gapekaDataDtoList);
            if(!gapekaDataDtoList.isEmpty())
                gapekaTrainDtoList.add(gapekaTrainDto);
        }
        log.info("Banyak Kereta: " + gapekaTrainDtoList.size());
    }

    private void createGapekaStationDto() throws Exception {
        gapekaStationDtoList.clear();
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

    private void fetchData() throws Exception {
        fetchTypeMasterPlan();
        fetchRuteRole();
        fetchRuteListByRuteRole();
        fetchMinimumDate();
        fetchMaximumDate();
        fetchTrains();
        fetchStations();
    }

    private void fetchStations() throws Exception {
        Optional<List<Station>> optionalStations = loadMasterPlanPort.loadStationsFromMasterPlanByTypeMasterPlanAndRuteRole(typeMasterPlan, ruteRole);
        stationList = optionalStations.orElseThrow(DataNotFoundException::new);
    }

    private void fetchTrains() throws Exception {
        Optional<List<Train>> optionalTrainList = loadMasterPlanPort.loadTrainsFromMasterPlanByTypeMasterPlanAndRuteRole(typeMasterPlan, ruteRole);
        trainList = optionalTrainList.orElseThrow(DataNotFoundException::new);
    }

    private void fetchMaximumDate() throws Exception {
        Optional<Date> optionalDate = loadMasterPlanPort.loadMaximumDepartFromMasterPlanByTypeMasterPlanAndRuteRole(typeMasterPlan, ruteRole);
        maximumDate = optionalDate.orElseThrow(DataNotFoundException::new);
    }

    private void fetchMinimumDate() throws Exception {
        Optional<Date> optionalDate = loadMasterPlanPort.loadMinimumArrivalFromMasterPlanByTypeMasterPlanAndRuteRole(typeMasterPlan, ruteRole);
        minimumDate = optionalDate.orElseThrow(DataNotFoundException::new);
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

}
