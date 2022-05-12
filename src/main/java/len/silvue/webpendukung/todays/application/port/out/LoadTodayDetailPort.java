package len.silvue.webpendukung.todays.application.port.out;

import len.silvue.webpendukung.domains.Train;
import len.silvue.webpendukung.domains.TypeMasterPlan;
import len.silvue.webpendukung.domains.TodayRunningSchedule;

import java.util.List;
import java.util.Optional;

public interface LoadTodayDetailPort {
    Optional<List<TodayRunningSchedule>> loadTodayRunningScheduleByTrainAndTypeSchedule(Train train, TypeMasterPlan typeMasterPlan) throws Exception;
    Optional<List<TodayRunningSchedule>> loadAllTodayRunningSchedule() throws Exception;
    Optional<List<TodayRunningSchedule>> loadTodayRunningScheduleByTypeMasterPlanIdAndRuteRoleId(int typeMasterPlanId, int ruteRoleId) throws Exception;
    Optional<List<TodayRunningSchedule>> loadTodayRunningScheduleByTypeMasterPlanId(int typeMasterPlanId) throws Exception;
//    Optional<List<TodayRunningSchedule>> loadTodayDetailToActual() throws Exception;
}
