package len.silvue.webpendukung.todays.application.port.in;

import len.silvue.webpendukung.todays.adapter.out.web.TodayRunningScheduleDto;
import len.silvue.webpendukung.utility.DataNotFoundException;

import java.util.List;

public interface TodayRunningScheduleUseCase {
    TodayRunningScheduleDto getTodayRunningScheduleById(int id) throws DataNotFoundException;

    TodayRunningScheduleDto getHeadTodayRunningSchedule(int id) throws DataNotFoundException;

    List<TodayRunningScheduleDto> getAllTodayRunningSchedule() throws Exception;

    List<TodayRunningScheduleDto> getTodayRunningScheduleByTrainIdAndTypeMasterPlanId(int trainId, int typeMasterPlanId) throws Exception;




}

