package len.silvue.webpendukung.todays.application.port.in;

import len.silvue.webpendukung.todays.adapter.out.web.SuccessDto;

public interface FindSuccessUseCase {
    SuccessDto getBrowseStatus(int idStatus) throws Exception;
}
