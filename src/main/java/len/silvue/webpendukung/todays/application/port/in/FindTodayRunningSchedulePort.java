package len.silvue.webpendukung.todays.application.port.in;

import len.silvue.webpendukung.domains.TodayRunningSchedule;

import java.util.List;
import java.util.Optional;

public interface FindTodayRunningSchedulePort {
    Optional <TodayRunningSchedule> loadTodayRunningScheduleById(int id) throws Exception;
    Optional<List<TodayRunningSchedule>> loadAllTodayRunningSchedule() throws Exception;
    Optional<List<TodayRunningSchedule>> loadTodayRunningScheduleByTrain(int idTrain) throws Exception;
    Optional<List<TodayRunningSchedule>> getFlagToday() throws Exception;
}
