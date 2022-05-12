package len.silvue.webpendukung.actual.application.port.out;

import len.silvue.webpendukung.domains.ActualPlan;


import java.util.List;
import java.util.Optional;

public interface SaveActualSchedulePort {
    void saveActualSchedule(ActualPlan actual) throws Exception;
    Optional<ActualPlan> storeActualSchedule(ActualPlan actualSchedule) throws Exception;
    void  storeActualScheduleList(List<ActualPlan> actual) throws Exception;
}
