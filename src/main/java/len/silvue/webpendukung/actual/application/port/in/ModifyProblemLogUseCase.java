package len.silvue.webpendukung.actual.application.port.in;

import len.silvue.webpendukung.actual.adapter.in.web.ModifyActualCommand;
import len.silvue.webpendukung.actual.adapter.out.web.ActualModifyDto;
import len.silvue.webpendukung.actual.adapter.out.web.ActualRunningScheduleDto;

public interface ModifyProblemLogUseCase {
    ActualModifyDto modifyProblemLogType(ModifyActualCommand modifyActualCommand) throws Exception;
    ActualRunningScheduleDto modifyAllProblemLogType(ModifyActualCommand modifyActualCommand) throws Exception;

    ActualRunningScheduleDto modifyAllProblemLogType(int IdActualPlan) throws Exception;
}
