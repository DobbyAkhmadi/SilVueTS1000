package len.silvue.webpendukung.todays.application.port.in;


import len.silvue.webpendukung.schedule.adapter.out.web.MasterPlanDto;
import len.silvue.webpendukung.todays.adapter.in.ModifyTodayCommand;
import len.silvue.webpendukung.todays.adapter.out.web.TodayRunningScheduleDto;

import java.util.List;

public interface ModifyTodayDetailUseCase {
    TodayRunningScheduleDto modifyTodayRunningScheduleType(ModifyTodayCommand modifyTodayCommand) throws Exception;
    List<TodayRunningScheduleDto> modifyAllTodayDetailScheduleType(ModifyTodayCommand modifyTodayCommand) throws Exception;
}
