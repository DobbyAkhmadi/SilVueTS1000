package len.silvue.webpendukung.schedule.application.port.out;


import len.silvue.webpendukung.domains.ColorTrain;

import java.util.List;
import java.util.Optional;

public interface LoadColorTrainPort {
    Optional<List<ColorTrain>> loadAllColorTrain() throws Exception;
    Optional<ColorTrain> loadColorTrainById(int idColorTrain) throws Exception;
}
