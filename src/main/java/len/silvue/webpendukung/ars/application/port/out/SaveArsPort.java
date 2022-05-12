package len.silvue.webpendukung.ars.application.port.out;

import len.silvue.webpendukung.domains.ArsSchedule;

import java.util.List;

public interface SaveArsPort {

    public void saveAll(List<ArsSchedule> arsScheduleList);
}
