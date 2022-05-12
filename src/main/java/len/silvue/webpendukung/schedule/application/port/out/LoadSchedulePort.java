package len.silvue.webpendukung.schedule.application.port.out;

import len.silvue.webpendukung.domains.Schedule;

import java.util.List;
import java.util.Optional;

public interface LoadSchedulePort {
    Optional<List<Schedule>> loadAllSchedules() throws Exception;
    Optional<Schedule> loadScheduleById(int id) throws Exception;
}
