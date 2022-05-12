package len.silvue.webpendukung.schedule.application.port.out;


import len.silvue.webpendukung.domains.MasterPlan;

import java.util.List;
import java.util.Optional;

public interface LoadMasterSchedulePort {
    Optional<List<MasterPlan>> loadMasterScheduleByTypeMasterPlan(int idTypeMasterPlan) throws Exception;
}
