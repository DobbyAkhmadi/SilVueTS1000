package len.silvue.webpendukung.schedule.application.port.in;

import len.silvue.webpendukung.schedule.adapter.out.web.ColorTrainDto;

import java.util.List;

public interface FindColorTrainUseCase {
    List<ColorTrainDto> getAllTrainColor() throws Exception;
    ColorTrainDto getColorTrainById(int id) throws Exception;
}
