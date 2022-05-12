package len.silvue.webpendukung.schedule.application.port.in;

import len.silvue.webpendukung.schedule.adapter.in.web.AddScheduleTypeCommand;
import len.silvue.webpendukung.schedule.adapter.out.web.TypeMasterPlanDto;

public interface AddScheduleTypeUseCase {
    TypeMasterPlanDto savaScheduleType(AddScheduleTypeCommand addScheduleTypeCommand) throws Exception;
}
