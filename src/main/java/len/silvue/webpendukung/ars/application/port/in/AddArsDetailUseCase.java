package len.silvue.webpendukung.ars.application.port.in;


import len.silvue.webpendukung.ars.adapter.out.web.ArsScheduleDto;

import java.util.List;

public interface AddArsDetailUseCase {
    List<ArsScheduleDto> setArsScheduleFromActualPlan() throws Exception;
    void setLastUpdateArs() throws Exception;
}
