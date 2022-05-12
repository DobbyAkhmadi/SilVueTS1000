package len.silvue.webpendukung.ars.application.port.in;

import len.silvue.webpendukung.ars.adapter.out.web.ArsConflictDto;
import len.silvue.webpendukung.domains.ArsConflict;
import len.silvue.webpendukung.schedule.adapter.out.web.StationDto;

import java.util.List;

public interface AddArsConflictUseCase {
    ArsConflictDto saveArsConflict(ArsConflict arsConflict) throws Exception;
}
