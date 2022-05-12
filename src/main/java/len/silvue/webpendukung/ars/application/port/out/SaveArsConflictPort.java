package len.silvue.webpendukung.ars.application.port.out;

import len.silvue.webpendukung.domains.ArsConflict;
import len.silvue.webpendukung.domains.ArsSchedule;

import java.util.List;

public interface SaveArsConflictPort {
    List<ArsSchedule> storeArsList(List<ArsSchedule> arsSchedule) throws Exception;

    ArsConflict storeDataConflictArs(ArsConflict arsConflict) throws Exception;
}
