package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.domains.*;
import len.silvue.webpendukung.schedule.adapter.in.web.AddScheduleCommand;
import len.silvue.webpendukung.schedule.adapter.in.web.AddScheduleRouteCommand;
import len.silvue.webpendukung.schedule.adapter.out.web.RouteDto;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.RouteMapper;
import len.silvue.webpendukung.schedule.application.port.in.AddScheduleUseCase;
import len.silvue.webpendukung.schedule.application.port.out.*;
import len.silvue.webpendukung.utility.DataNotFoundException;
import len.silvue.webpendukung.utility.SaveScheduleFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddScheduleService implements AddScheduleUseCase {
    private final LoadTrainPort loadTrainPort;
    private final LoadTypeMasterPlanPort loadTypeMasterPlanPort;
    private final LoadRuteRolePort loadRuteRolePort;
    private final SaveSchedulePort saveSchedulePort;
    private final LoadSchedulePort loadSchedulePort;
    private final LoadStationPort loadStationPort;
    private final LoadPeronPort loadPeronPort;
    private final RouteMapper routeMapper;
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    private final SaveMasterPlanPort saveMasterPlanPort;

    @Override
    public Schedule saveSchedule(AddScheduleCommand addScheduleCommand) throws SaveScheduleFailedException {
        try {
            Optional<Train> optionalTrain = loadTrainPort.loadTrainById(addScheduleCommand.getIdTrain());
            Optional<TypeMasterPlan> optionalTypeMasterPlan = loadTypeMasterPlanPort.loadTypeMasterPlanById(addScheduleCommand.getIdTypeMasterPlan());
            Optional<RuteRole> optionalRuteRole = loadRuteRolePort.loadRuteRoleById(addScheduleCommand.getIdRuteRole());

            Schedule schedule = Schedule.builder()
                    .scheduleName(addScheduleCommand.getScheduleName())
                    .train(optionalTrain.orElse(null))
                    .typeMasterPlan(optionalTypeMasterPlan.orElse(null))
                    .ruteRole(optionalRuteRole.orElse(null))
                  //  .flagMaster(addScheduleCommand.getFlagMaster())
                    .build();
            schedule = saveSchedulePort.storeSchedule(schedule);
            return schedule;
        } catch (Exception e) {
            throw new SaveScheduleFailedException("Gagal menyimpan schedule", e);
        }
    }

    @Override
    public RouteDto saveScheduleRoute(AddScheduleRouteCommand addScheduleRouteCommand) throws DataNotFoundException {
        try {
            Optional<Schedule> optionalSchedule = loadSchedulePort.loadScheduleById(addScheduleRouteCommand.getScheduleId());
            Schedule schedule = optionalSchedule.orElseThrow(DataNotFoundException::new);

            Optional<Station> optionalStation = loadStationPort.loadStationById(addScheduleRouteCommand.getIdStation());
            Optional<Peron>  optionalPeron = loadPeronPort.loadPeronById(addScheduleRouteCommand.getIdPeron());

            Date arrivalDate = makeTimeStringToDate(addScheduleRouteCommand.getArrivalDate());
            Date departDate = makeTimeStringToDate(addScheduleRouteCommand.getDepartDate());
            Route route = Route.builder()
                    .station(optionalStation.orElseThrow())
                    .arrival(arrivalDate)
                    .depart(departDate)
                    .peron(optionalPeron.orElseThrow())
                    .build();
            List<Route> routeList = getRouteListFromSchedule(schedule);
            routeList.add(route);

            dataScheduleRouteSaveToMasterPlan(addScheduleRouteCommand);

          //  schedule.setRoutes(routeList);
            saveSchedulePort.storeSchedule(schedule);
            return routeMapper.toRouteDto(route);
        } catch(Exception e) {
            throw new DataNotFoundException("Data kosong", e);
        }
    }

    private void dataScheduleRouteSaveToMasterPlan(AddScheduleRouteCommand addScheduleRouteCommand) throws Exception {
        try {
            Optional<Schedule> optionalSchedule = loadSchedulePort.loadScheduleById(addScheduleRouteCommand.getScheduleId());
            Schedule schedule = optionalSchedule.orElseThrow(DataNotFoundException::new);
            Optional<Peron>  optionalPeron = loadPeronPort.loadPeronById(addScheduleRouteCommand.getIdPeron());
            Peron peron = optionalPeron.orElseThrow(DataNotFoundException::new);

            Date arrivalDate = makeTimeStringToDate(addScheduleRouteCommand.getArrivalDate());
            Date departDate = makeTimeStringToDate(addScheduleRouteCommand.getDepartDate());

            MasterPlan masterPlan = MasterPlan
                    .builder()
                    .arrival(arrivalDate)
                    .depart(departDate)
                    .typeMasterPlan(schedule.getTypeMasterPlan())
                 //   .flagMasterPlan(schedule.getFlagMaster().compareTo("on") == 0 ? 1 : 0)
                    .peronFrom(peron)
                    .peronTo(peron)
                    .train(schedule.getTrain())
                    .ruteRole(schedule.getRuteRole())
                    .build();
            saveMasterPlanPort.saveMasterPlan(masterPlan);
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan data schedule route ke masterplan", e);
        }
    }

    private List<Route> getRouteListFromSchedule(Schedule schedule) throws Exception {
        try {
            List<Route> routeList = null;
//            if(schedule.getRoutes() == null) {
//                routeList = new ArrayList<>();
//            } else {
//                routeList = schedule.getRoutes();
//            }
            return routeList;
        } catch(Exception e) {
            throw new Exception("Error mengambil list route", e);
        }
    }

    private Date makeTimeStringToDate(String time) throws Exception {
        try {
            return sdf.parse(time);
        } catch (Exception e) {
            throw new Exception("Error make time string to date", e);
        }
    }
}
