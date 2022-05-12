package len.silvue.webpendukung.schedule.application.port.in;

import len.silvue.webpendukung.schedule.adapter.out.web.TrainDto;

import java.util.List;

public interface FindTrainUseCase {
    List<TrainDto> getAllTrain() throws Exception;
    TrainDto getTrainById(int idTrain) throws Exception;
}
