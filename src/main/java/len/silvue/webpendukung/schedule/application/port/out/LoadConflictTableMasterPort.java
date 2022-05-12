package len.silvue.webpendukung.schedule.application.port.out;

import len.silvue.webpendukung.domains.ConflictTableMaster;

import java.util.List;
import java.util.Optional;

public interface LoadConflictTableMasterPort {
    Optional<List<ConflictTableMaster>> loadAllConflict() throws Exception;
}
