package len.silvue.webpendukung.schedule.application.port.in;

import len.silvue.webpendukung.schedule.adapter.in.web.ModifyScheduleCommand;
import len.silvue.webpendukung.schedule.adapter.out.web.ScheduleDto;

public interface ModifyScheduleUseCase {
    ScheduleDto modifySchedule(ModifyScheduleCommand modifyScheduleCommand) throws Exception;
}
