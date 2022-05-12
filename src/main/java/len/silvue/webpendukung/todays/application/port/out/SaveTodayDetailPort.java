package len.silvue.webpendukung.todays.application.port.out;

import len.silvue.webpendukung.domains.TodayRunningSchedule;

import java.util.List;
import java.util.Optional;

public interface SaveTodayDetailPort {
    void saveTodayRunningSchedule(TodayRunningSchedule today) throws Exception;
    Optional<TodayRunningSchedule> storeTodayDetailSchedule(TodayRunningSchedule todayRunningSchedule) throws Exception;
    void  storeTodayDetailScheduleList(List<TodayRunningSchedule> today) throws Exception;
    List<TodayRunningSchedule> storeTodayList (List<TodayRunningSchedule> todayRunningScheduleList)throws Exception;
}
