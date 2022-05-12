package len.silvue.webpendukung.schedule.application.port.in;

import len.silvue.webpendukung.schedule.adapter.in.web.ModifyScheduleTypeCommand;
import len.silvue.webpendukung.schedule.adapter.out.web.TypeMasterPlanDto;

public interface ModifyScheduleTypeUseCase {
    TypeMasterPlanDto modifyScheduleType(ModifyScheduleTypeCommand modifyScheduleTypeCommand) throws Exception;
}
