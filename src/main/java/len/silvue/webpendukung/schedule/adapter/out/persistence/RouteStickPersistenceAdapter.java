package len.silvue.webpendukung.schedule.adapter.out.persistence;

import len.silvue.webpendukung.schedule.adapter.out.persistence.repositories.RouteStickRepository;
import len.silvue.webpendukung.schedule.application.port.out.LoadRouteStickPort;
import len.silvue.webpendukung.domains.RouteStick;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class RouteStickPersistenceAdapter implements LoadRouteStickPort {
    private final RouteStickRepository routeStickRepository;

    @Override
    public Optional<List<RouteStick>> loadAllRouteStick() throws Exception {
        try {
            List<RouteStick> routeStickList = routeStickRepository.findAll();
            return Optional.of(routeStickList);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil data Route Stick", e);
        }
    }
}
