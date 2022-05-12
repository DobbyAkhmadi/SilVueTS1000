package len.silvue.webpendukung.schedule.application.port.in;
import len.silvue.webpendukung.schedule.adapter.out.web.ConflictMasterPlanDto;

import java.util.List;

public interface FindConflictTableMasterUseCase {
    List<ConflictMasterPlanDto> loadAllConflict() throws Exception;
}
