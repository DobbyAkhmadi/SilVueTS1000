package len.silvue.webpendukung.schedule.application.port.out;

import len.silvue.webpendukung.domains.RouteStick;

import java.util.List;
import java.util.Optional;

public interface LoadRouteStickPort {
    Optional<List<RouteStick>> loadAllRouteStick() throws Exception;
}
