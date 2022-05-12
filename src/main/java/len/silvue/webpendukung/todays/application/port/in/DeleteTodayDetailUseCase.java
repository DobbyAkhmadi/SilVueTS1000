package len.silvue.webpendukung.todays.application.port.in;

import len.silvue.webpendukung.todays.adapter.out.web.TodayRunningScheduleDto;

public interface DeleteTodayDetailUseCase {


    TodayRunningScheduleDto deleteTodayRunningScheduleById(int idTodayRunningSchedule) throws Exception;
    void deleteAllTodayDetail() throws Exception;
    void deleteAllTodayDetailByTrainId(int trainId) throws Exception;
}
