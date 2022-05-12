package len.silvue.webpendukung.todays.application.port.in;
import len.silvue.webpendukung.todays.adapter.out.web.ConflictTodayDto;

import java.util.List;

public interface FindConflictTableTodayUseCase {
    List<ConflictTodayDto> loadAllConflict() throws Exception;
}
