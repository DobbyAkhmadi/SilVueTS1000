package len.silvue.webpendukung.schedule.application.port.in;

import len.silvue.webpendukung.schedule.adapter.out.web.NumberTrainDto;

import java.util.List;

public interface FindNumberTrainUseCase {
    List<NumberTrainDto> getAllNumberTrain() throws Exception;
}
