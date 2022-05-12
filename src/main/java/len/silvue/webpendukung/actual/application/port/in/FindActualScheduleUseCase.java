package len.silvue.webpendukung.actual.application.port.in;

import len.silvue.webpendukung.schedule.adapter.out.web.RouteDto;

public interface FindActualScheduleUseCase {
    RouteDto getScheduleById(int id) throws Exception;
}
