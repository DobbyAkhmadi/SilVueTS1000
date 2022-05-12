package len.silvue.webpendukung.todays.application.port.in;


import len.silvue.webpendukung.todays.adapter.out.web.TodayRunningScheduleDto;

import java.util.List;

public interface AddTodayDetailUseCase {
    List<TodayRunningScheduleDto> setTodayRunningScheduleFromMasterPlan(String commaSeperatedTypeMasterPlanID) throws Exception;
}
