package len.silvue.webpendukung.schedule.application.port.in;

import len.silvue.webpendukung.schedule.adapter.out.web.SuccessBrowseDto;
import len.silvue.webpendukung.schedule.adapter.out.web.TrainDto;

import java.util.List;

public interface FindSuccessBrowseUseCase {
    SuccessBrowseDto getBrowseStatus(int idStatus) throws Exception;
}
