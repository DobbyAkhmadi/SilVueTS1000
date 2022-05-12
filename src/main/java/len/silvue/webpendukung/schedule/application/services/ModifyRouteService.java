package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.schedule.adapter.in.web.ModifyRouteCommand;
import len.silvue.webpendukung.schedule.adapter.out.web.RouteDto;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.RouteMapper;
import len.silvue.webpendukung.schedule.application.port.in.ModifyRouteUseCase;
import len.silvue.webpendukung.schedule.application.port.out.LoadPeronPort;
import len.silvue.webpendukung.schedule.application.port.out.LoadRoutePort;
import len.silvue.webpendukung.schedule.application.port.out.LoadStationPort;
import len.silvue.webpendukung.schedule.application.port.out.SaveRoutePort;
import len.silvue.webpendukung.domains.Peron;
import len.silvue.webpendukung.domains.Route;
import len.silvue.webpendukung.domains.Station;
import len.silvue.webpendukung.utility.DataFormat;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ModifyRouteService implements ModifyRouteUseCase {
    private final SaveRoutePort saveRoutePort;
    private final LoadStationPort loadStationPort;
    private final LoadPeronPort loadPeronPort;
    private final LoadRoutePort loadRoutePort;
    private final RouteMapper routeMapper;

    @Override
    public RouteDto modifyRoute(ModifyRouteCommand modifyRouteCommand) throws Exception {
        try {
            Optional<Station> optionalStation = loadStationPort.loadStationById(modifyRouteCommand.getStationId());
            Optional<Peron> optionalPeron = loadPeronPort.loadPeronById(modifyRouteCommand.getPeronId());

            Optional<Route> optionalRoute = loadRoutePort.loadRouteById(modifyRouteCommand.getRouteId());

            Route route = optionalRoute.orElseThrow(DataNotFoundException::new);
            route.setStation(optionalStation.orElseThrow(DataNotFoundException::new));
            route.setPeron(optionalPeron.orElseThrow(DataNotFoundException::new));
            route.setArrival(DataFormat.changeStrTimeToDate(modifyRouteCommand.getArrival()));
            route.setDepart(DataFormat.changeStrTimeToDate(modifyRouteCommand.getDepart()));
            saveRoutePort.saveRoute(route);
            //routeDto.setScheduleId(route.getSchedule().getScheduleId());
            return routeMapper.toRouteDto(route);
        } catch(Exception e) {
            throw new Exception("Service modify route gagal", e);
        }
    }
}
