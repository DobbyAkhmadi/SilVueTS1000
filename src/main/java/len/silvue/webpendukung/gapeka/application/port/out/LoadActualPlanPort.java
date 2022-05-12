package len.silvue.webpendukung.gapeka.application.port.out;

import len.silvue.webpendukung.domains.ActualPlan;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LoadActualPlanPort {
    Optional<List<ActualPlan>> loadAllActualPlan() throws Exception;
    Optional<List<ActualPlan>> loadActualPlanByRuteRole(String ruteRole) throws Exception;
    Optional<List<ActualPlan>> loadActualPlanByActualCodeAndRuteRoleAndTypePlan(Date actualCode, String ruteRole, String typePlan) throws Exception;
    Optional<List<String>> loadDistinctStationOrderByActualCode(Date actualCodeDate) throws Exception;
    Optional<Date> loadMinimumArrivalActualPlanByActualCode(Date actualCode) throws Exception;
    Optional<Date> loadMaximumDepartActualPlanByActualCode(Date actualCode) throws Exception;
    Optional<List<String>> loadDistinctTrainOrderByActualCode(Date actualCodeDate) throws Exception;
    Optional<List<ActualPlan>> loadActualPlanByActualCodeAndTypePlan(Date actualCode, String typePlan) throws Exception;
}
