package len.silvue.webpendukung.todays.application.port.in;


import len.silvue.webpendukung.actual.adapter.out.web.ActualRunningScheduleDto;
import len.silvue.webpendukung.todays.adapter.out.web.TodayRunningScheduleDto;

import java.util.List;

public interface AddTodayToActualUseCase {
    List<ActualRunningScheduleDto> setActualPlanFromToday() throws Exception;
    List<ActualRunningScheduleDto> setActualPlanFromTodayWithConflict() throws Exception;

}
