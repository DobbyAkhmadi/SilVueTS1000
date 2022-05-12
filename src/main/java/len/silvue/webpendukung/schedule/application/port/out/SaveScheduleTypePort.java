package len.silvue.webpendukung.schedule.application.port.out;

import len.silvue.webpendukung.domains.TypeMasterPlan;

import java.util.Optional;

public interface SaveScheduleTypePort {
    Optional<TypeMasterPlan> storeScheduleType(TypeMasterPlan typeMasterPlan) throws Exception;
}
