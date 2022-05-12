package len.silvue.webpendukung.schedule.application.port.out;


import len.silvue.webpendukung.domains.Train;

import java.util.List;
import java.util.Optional;

public interface LoadTrainPort {
    Optional<Train> loadTrainByNoka(String noka) throws Exception;
    Optional<List<Train>> loadAllTrain() throws Exception;
    Optional<Train> loadTrainById(int idTrain) throws Exception;
}
