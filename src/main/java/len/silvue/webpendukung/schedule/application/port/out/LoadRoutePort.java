package len.silvue.webpendukung.schedule.application.port.out;

import len.silvue.webpendukung.domains.Route;
import len.silvue.webpendukung.utility.DataNotFoundException;

import java.util.List;
import java.util.Optional;

public interface LoadRoutePort {
    Optional<List<Route>> loadAllRoute() throws Exception;
    Optional<Route> loadRouteById(int id) throws DataNotFoundException;
}
