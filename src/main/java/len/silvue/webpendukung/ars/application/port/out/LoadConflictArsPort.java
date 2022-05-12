package len.silvue.webpendukung.ars.application.port.out;

import len.silvue.webpendukung.domains.ArsConflict;
import len.silvue.webpendukung.domains.ArsSchedule;
import len.silvue.webpendukung.domains.RouteStick;

import java.util.List;
import java.util.Optional;

public interface LoadConflictArsPort {
    Optional<List<ArsConflict>> loadAllConflictArs() throws Exception;
    Optional<ArsSchedule> loadArsScheduleById (int idArsSchedule) throws Exception;
    Optional<List<ArsSchedule>> loadArsScheduleByTrain(int idTrain) throws Exception;
    Optional<RouteStick> loadRouteStickById (int idRouteStick) throws Exception;
}
