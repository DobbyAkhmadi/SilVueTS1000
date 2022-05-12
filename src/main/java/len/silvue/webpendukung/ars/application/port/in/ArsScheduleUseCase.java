package len.silvue.webpendukung.ars.application.port.in;

import len.silvue.webpendukung.ars.adapter.out.web.ArsScheduleDto;
import len.silvue.webpendukung.domains.ArsSchedule;

import java.util.List;

public interface ArsScheduleUseCase {
    List<ArsScheduleDto> getAllArsSchedule() throws Exception;
}
