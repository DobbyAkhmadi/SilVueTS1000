package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.schedule.adapter.out.web.*;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.MasterPlanMapper;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.MasterScheduleMapper;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.ScheduleMapper;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.StationMapper;
import len.silvue.webpendukung.schedule.application.port.in.FindMasterScheduleUseCase;
import len.silvue.webpendukung.schedule.application.port.out.*;
import len.silvue.webpendukung.domains.MasterPlan;
import len.silvue.webpendukung.domains.Schedule;
import len.silvue.webpendukung.domains.Station;
import len.silvue.webpendukung.domains.TypeMasterPlan;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindMasterScheduleService implements FindMasterScheduleUseCase {
    private final LoadSchedulePort loadSchedulePort;
    private final LoadStationPort loadStationPort;
    private List<RouteDto> routeDtoList = null;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
    private final ScheduleMapper scheduleMapper;
    private final LoadMasterPlanPort loadMasterPlanPort;
    private final LoadTypeMasterPlanPort loadTypeMasterPlanPort;
    private final LoadMasterSchedulePort loadMasterSchedulePort;
    private final MasterPlanMapper masterPlanMapper;
    private final MasterScheduleMapper masterScheduleMapper;

    @Transactional
    @Override
    public List<ScheduleDto> getAllMasterSchedule() throws Exception {
        try {
            Optional<List<Schedule>> optionalSchedules = loadSchedulePort.loadAllSchedules();
            List<ScheduleDto> scheduleDtoList = ScheduleMapper.MAPPER.toScheduleDtoList(optionalSchedules.orElse(new ArrayList<>()));

            makeScheduleToViewFromAllStation(scheduleDtoList);

            return scheduleDtoList;
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception("Gagal mengambil service master schedule", e);
        }
    }

    @Override
    public List<ViewMasterScheduleDto> getViewMasterSchedule() throws Exception {
        try {
            List<ViewMasterScheduleDto> viewMasterScheduleDtoList = new ArrayList<>();
            List<ScheduleDto> scheduleDtoList = getAllMasterSchedule();
            scheduleDtoList.forEach(scheduleDto -> {
                ViewMasterScheduleDto viewMasterScheduleDto = new ViewMasterScheduleDto();
                viewMasterScheduleDto.setScheduleId(scheduleDto.getScheduleId());
                viewMasterScheduleDto.setRouteName(scheduleDto.getScheduleName());
                viewMasterScheduleDto.setTrainName(scheduleDto.getTrain().getNoka());
                viewMasterScheduleDto.setScheduleType(scheduleDto.getTypeMasterPlan().getNameTypeMasterPlan());
                List<ViewRoute> viewRouteList = new ArrayList<>();
//                scheduleDto.getRoutes().forEach(routeDto -> {
//                    ViewRoute viewRouteArrival = new ViewRoute();
//                    ViewRoute viewRouteDepart = new ViewRoute();
//                    if(routeDto.getRouteId() > 0) {
//                        Date arrivalDate = routeDto.getArrival();
//                        Date departDate = routeDto.getDepart();
//                        viewRouteArrival.setTimeString((arrivalDate == null ? "00:00" : simpleDateFormat.format(routeDto.getArrival())));
//                        viewRouteDepart.setTimeString((departDate == null ? "00:00" : simpleDateFormat.format(routeDto.getDepart())));
//                    }
//                    viewRouteList.add(viewRouteArrival);
//                    viewRouteList.add(viewRouteDepart);
//                });
                viewMasterScheduleDto.setViewRouteList(viewRouteList);

                viewMasterScheduleDtoList.add(viewMasterScheduleDto);
            });
            return viewMasterScheduleDtoList;
        } catch(Exception e) {
            throw new Exception("Gagal mengambil view master schedule", e);
        }
    }

    @Override
    public ScheduleDto getScheduleById(int id) throws DataNotFoundException {
        try {
            Optional<Schedule> optionalSchedule = loadSchedulePort.loadScheduleById(id);
            return scheduleMapper.toScheduleDto(optionalSchedule.orElseThrow());
        } catch(Exception e) {
            throw new DataNotFoundException("Gagal mengambil schedule berdasarkan id", e);
        }
    }

    @Override
    public List<MasterPlanDto> getAllMasterPlan() throws Exception {
        try {
            Optional<List<MasterPlan>> optionalMasterPlans = loadMasterPlanPort.loadAllMasterPlan();
            return masterPlanMapper.toMasterPlanDtoList(optionalMasterPlans.orElseThrow());
        } catch(Exception e) {
            throw new DataNotFoundException("Data Masterplan kosong", e);
        }
    }

    @Override
    public List<MasterScheduleDto> getAllMasterScheduleByTypeMasterPlan(int typeMasterPlanId) throws Exception {
        try {
            Optional<TypeMasterPlan> optionalTypeMasterPlan =loadTypeMasterPlanPort.loadTypeMasterPlanById(typeMasterPlanId);

            Optional<List<MasterPlan>> optionalMasterPlanList = loadMasterSchedulePort.loadMasterScheduleByTypeMasterPlan(optionalTypeMasterPlan.orElseThrow().getIdTypeMasterPlan());
            return masterScheduleMapper.toMasterScheduleList(optionalMasterPlanList.orElseThrow());
        } catch(Exception e) {
            throw new DataNotFoundException("Data Master plan kosong", e);
        }
    }

    private void makeScheduleToViewFromAllStation(List<ScheduleDto> scheduleDtoList) throws Exception {
        try {
            for (ScheduleDto scheduleDto : scheduleDtoList) {
                List<RouteDto> routeDtoListFromSchedule = new ArrayList<>();
                //routeDtoListFromSchedule.addAll(scheduleDto.getRoutes());

                Optional<List<Station>> optionalStationList = loadStationPort.loadAllStation();
                List<StationDto> allStation = StationMapper.MAPPER.toStationDtoList(optionalStationList.orElse(new ArrayList<>()));
                List<RouteDto> routeDtoListIn = makeRouteDtoFromStations(allStation);

                List<RouteDto> routeDtoListForSchedule = new ArrayList<>(routeDtoListIn);
                routeDtoListForSchedule.forEach(allRoutes -> {
                    routeDtoListFromSchedule.forEach(realRouteDto -> {
                        if (realRouteDto.getStation().getIdStation() == allRoutes.getStation().getIdStation()) {
                            allRoutes.setStation(realRouteDto.getStation());
                            allRoutes.setDepart(realRouteDto.getDepart());
                            allRoutes.setArrival(realRouteDto.getArrival());
                            allRoutes.setRouteId(realRouteDto.getRouteId());
                        }
                    });
                });
         //       scheduleDto.setRoutes(routeDtoListForSchedule);
            }
        } catch(Exception e) {
            throw new Exception("Gagal membuat schedule dari semua station", e);
        }
    }


    private List<RouteDto> makeRouteDtoFromStations(List<StationDto> allStation) throws Exception {
        try {
            List<RouteDto> routeDtoList = new ArrayList<>();
            allStation.forEach(station -> {
                RouteDto routeDto = RouteDto.builder()
                        .station(station)
                        .build();
                routeDtoList.add(routeDto);
            });
            return routeDtoList;
        } catch(Exception e) {
            throw new Exception("Gagal membuat semua route dari station", e);
        }
    }
}
