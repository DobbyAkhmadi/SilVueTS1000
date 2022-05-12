package len.silvue.webpendukung.actual.application.port.in;

import len.silvue.webpendukung.domains.ActualPlan;

import java.util.List;
import java.util.Optional;

public interface FindActualSchedulePort {
  // Optional <ActualPlan> loadActualScheduleById(int id) throws Exception;
    Optional<List<ActualPlan>> loadAllActualSchedule() throws Exception;
  //  Optional<List<ActualPlan>> loadActualScheduleByTrain(int idTrain) throws Exception;
}
