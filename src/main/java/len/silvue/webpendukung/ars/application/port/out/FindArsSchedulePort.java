package len.silvue.webpendukung.ars.application.port.out;

import len.silvue.webpendukung.domains.ArsSchedule;

import java.util.List;

public interface FindArsSchedulePort {
    List<ArsSchedule> loadAllArsSchedule() throws Exception;

}

