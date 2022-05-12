package len.silvue.webpendukung.ars.application.port.in;

import len.silvue.webpendukung.ars.adapter.out.web.ArsConflictDto;
import len.silvue.webpendukung.ars.adapter.out.web.ArsScheduleDto;
import len.silvue.webpendukung.domains.ArsConflict;
import len.silvue.webpendukung.domains.RouteStick;
import len.silvue.webpendukung.schedule.adapter.out.web.MasterPlanDto;
import len.silvue.webpendukung.schedule.adapter.out.web.RouteStickDto;
import len.silvue.webpendukung.utility.DataNotFoundException;

import java.util.List;

public interface FindArsConflictUseCase {
    List<ArsConflictDto> getAllArsConflict() throws  Exception;
    ArsConflictDto getArsConflictById(int idArsSchedule) throws DataNotFoundException;
    ArsScheduleDto getArsScheduleById (int idArsSchedule) throws Exception;
    RouteStickDto getRoutestickById (int idRouteStick) throws Exception;
}