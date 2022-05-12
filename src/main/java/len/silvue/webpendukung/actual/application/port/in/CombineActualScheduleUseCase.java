package len.silvue.webpendukung.actual.application.port.in;


import len.silvue.webpendukung.actual.adapter.in.CombineActualPlanCommand;
import len.silvue.webpendukung.actual.adapter.out.web.ActualRunningScheduleDto;

import java.util.List;

public interface CombineActualScheduleUseCase {
    List<ActualRunningScheduleDto> combineActualByIndex(CombineActualPlanCommand combineActualPlanCommand) throws Exception;
}
