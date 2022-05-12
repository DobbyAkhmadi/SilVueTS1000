package len.silvue.webpendukung.todays.application.port.out;

import len.silvue.webpendukung.domains.ConflictTableToday;

import java.util.List;
import java.util.Optional;

public interface LoadConflictTableTodayPort {
    Optional<List<ConflictTableToday>> loadAllConflict() throws Exception;
}
