package len.silvue.webpendukung.actual.application.port.in;

import len.silvue.webpendukung.actual.adapter.in.web.ModifyActualCommand;
import len.silvue.webpendukung.actual.adapter.out.web.ActualModifyDto;
import len.silvue.webpendukung.actual.adapter.out.web.ActualRunningScheduleDto;



import java.util.List;

public interface ModifyActualTypeUseCase {
    ActualModifyDto modifyActualScheduleType(ModifyActualCommand modifyActualCommand) throws Exception;
    List<ActualRunningScheduleDto> modifyAllActualScheduleType(ModifyActualCommand modifyActualCommand) throws Exception;
}
