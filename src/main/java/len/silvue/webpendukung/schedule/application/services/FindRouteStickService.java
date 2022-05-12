package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.schedule.adapter.out.web.RouteStickDto;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.RouteStickMapper;
import len.silvue.webpendukung.schedule.application.port.in.FindRouteStickUseCase;
import len.silvue.webpendukung.schedule.application.port.out.LoadRouteStickPort;
import len.silvue.webpendukung.domains.RouteStick;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindRouteStickService implements FindRouteStickUseCase {
    private final LoadRouteStickPort loadRouteStickPort;
    private final RouteStickMapper routeStickMapper;

    @Override
    public List<RouteStickDto> getAllRouteStick() throws Exception {
        try {
            Optional<List<RouteStick>> optionalRouteSticks = loadRouteStickPort.loadAllRouteStick();
            return routeStickMapper.toRouteStickDtoList(optionalRouteSticks.orElse(new ArrayList<>()));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil route stick", e);
        }
    }
}
