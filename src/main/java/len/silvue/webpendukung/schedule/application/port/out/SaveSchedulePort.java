package len.silvue.webpendukung.schedule.application.port.out;

import len.silvue.webpendukung.domains.Schedule;

public interface SaveSchedulePort {
    Schedule storeSchedule(Schedule schedule) throws Exception;
}
