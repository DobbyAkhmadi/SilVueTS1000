package len.silvue.webpendukung.actual.application.port.out;

import len.silvue.webpendukung.domains.ActualPlan;


import java.util.List;
import java.util.Optional;

public interface LoadActualSchedulePort {
   //Optional<List<ActualPlan>> loadActualScheduleByTrainAndTypeSchedule(Train train, TypeMasterPlan typeMasterPlan) throws Exception;
    Optional<List<ActualPlan>> loadActualScheduleByTrain(String trainId) throws Exception;
    //Optional<List<ActualPlan>> loadAllActualPlanByActualCode(String actualCode) throws Exception;
}
