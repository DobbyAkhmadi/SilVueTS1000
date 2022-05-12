package len.silvue.webpendukung.schedule.application.port.out;
import len.silvue.webpendukung.domains.MasterPlan;

import java.util.List;
import java.util.Optional;

public interface SaveMasterPlanPort {
    void saveMasterPlan(MasterPlan masterPlan) throws Exception;
    Optional<MasterPlan> storeMasterPlan(MasterPlan masterPlan) throws Exception;
    List<MasterPlan>  storeMasterPlanList(List<MasterPlan> masterPlan) throws Exception;
}
