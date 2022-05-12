package len.silvue.webpendukung.schedule.application.port.out;

import len.silvue.webpendukung.domains.Station;

import java.util.List;
import java.util.Optional;

public interface LoadStationPort {
    Optional<List<Station>> loadAllStation() throws Exception;
    Optional<Station> loadStationById(int idStation) throws Exception;
    Optional<Station> loadStationByName(String nameStation) throws Exception;
    Optional<List<Station>> LoadAllStationsNotInMasterPlan() throws Exception;
    Optional<List<Station>> loadAllStationsNotInRouteDetail(int idRoute) throws Exception;
}
