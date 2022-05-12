package len.silvue.webpendukung.schedule.application.port.out;

import len.silvue.webpendukung.domains.NumberTrain;

import java.util.List;
import java.util.Optional;

public interface LoadNumberTrainPort {
    Optional<List<NumberTrain>> loadAllNumberTrain() throws Exception;
}
