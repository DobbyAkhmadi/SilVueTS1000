package len.silvue.webpendukung.schedule.application.port.out;

import len.silvue.webpendukung.domains.TypeMasterPlan;

import java.util.List;
import java.util.Optional;

public interface LoadTypeMasterPlanPort {
    Optional<List<TypeMasterPlan>> loadAllTypeMasterPlan() throws Exception;
    Optional<TypeMasterPlan> loadTypeMasterPlanById(int idTypeMasterPlan) throws Exception;
    Optional<TypeMasterPlan> loadTypeMasterPlanByName(String typeMasterPlan) throws Exception;
}
