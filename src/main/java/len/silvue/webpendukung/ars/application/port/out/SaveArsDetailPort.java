package len.silvue.webpendukung.ars.application.port.out;

import len.silvue.webpendukung.domains.ArsSchedule;

import java.util.List;
import java.util.Optional;

public interface SaveArsDetailPort {
    void saveArsSchedule(ArsSchedule ars) throws Exception;
    void  storeArsDetailScheduleList(List<ArsSchedule> ars) throws Exception;
    Optional<ArsSchedule> storeArsConflict(ArsSchedule arsSchedule) throws Exception;
    List<ArsSchedule> storeArsList(List<ArsSchedule> arsSchedule) throws Exception;
}
