package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.schedule.adapter.out.web.RouteDto;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.RouteMapper;
import len.silvue.webpendukung.schedule.application.port.in.FindRouteUseCase;
import len.silvue.webpendukung.schedule.application.port.out.LoadRoutePort;
import len.silvue.webpendukung.domains.Route;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindRouteService implements FindRouteUseCase {
    private final LoadRoutePort loadRoutePort;
    private final RouteMapper routeMapper;

    @Override
    public List<RouteDto> getAllRoute() throws Exception {
        try {
            Optional<List<Route>> optionalRouteList = loadRoutePort.loadAllRoute();
            return routeMapper.toRouteDtoList(optionalRouteList.orElse(new ArrayList<>()));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil semua route", e);
        }
    }
    @Override
    public RouteDto getRouteById(int id) throws Exception {
        try {
            Optional<Route> optionalRoute = loadRoutePort.loadRouteById(id);
            return routeMapper.toRouteDto(optionalRoute.orElseThrow(DataNotFoundException::new));
        } catch(DataNotFoundException e) {
            throw new Exception("Gagal mengambil data route", e);
        }
    }
}
