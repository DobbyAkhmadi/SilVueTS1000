package len.silvue.webpendukung.actual.application.port.out;


import len.silvue.webpendukung.domains.ActualPlan;


import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FindActualRunningSchedulePort {
    Optional<ActualPlan> loadActualRunningScheduleById(int id);
    Optional<Date> loadMaxTimeData() throws  Exception;
    Optional<List<ActualPlan>> loadAllActualRunningSchedule(int index) throws Exception;
    Optional<Integer> getmaxindeactual() throws Exception;
    Optional<List<Integer>> getindex() throws Exception;

    Optional<List<ActualPlan>> loadAllActualRunningSchedule() throws Exception;
    Optional<List<String>> loadTypePlanFromActualPlanByActualCode(Date actualCode) throws Exception;
    Optional<List<String>> loadRuteFromActualPlanByActualCode(Date actualCode) throws Exception;
    Optional<List<String>> loadAllTypePlanFromActualPlan() throws Exception;
    Optional<List<String>> loadAllRuteRoleFromActualPlan() throws Exception;
    Optional<List<ActualPlan>> loadAllActualRunningScheduleByActualCode(Date actualCodeFrom, Date actualCodeTo) throws Exception;
    Optional<List<ActualPlan>> loadActualPlanByActualCode(Date selectActualCode) throws Exception;
}
