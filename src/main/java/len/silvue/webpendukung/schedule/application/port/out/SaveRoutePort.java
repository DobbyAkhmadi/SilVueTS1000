package len.silvue.webpendukung.schedule.application.port.out;

import len.silvue.webpendukung.domains.Route;

public interface SaveRoutePort {
    void saveRoute(Route route) throws Exception;
}
