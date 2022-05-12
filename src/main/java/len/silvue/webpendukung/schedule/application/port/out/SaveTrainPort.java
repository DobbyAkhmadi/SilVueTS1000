package len.silvue.webpendukung.schedule.application.port.out;

import len.silvue.webpendukung.domains.Train;

import java.util.List;

public interface SaveTrainPort {
    void saveTrain(Train train) throws Exception;
    List<Train> saveAllTrain(List<Train> trainList) throws Exception;
}
