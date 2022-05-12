package len.silvue.webpendukung.schedule.application.port.in;

import len.silvue.webpendukung.schedule.adapter.out.web.ParamDto;

public interface FindParamMasterUseCase {
    ParamDto getAllParam() throws Exception;
}
