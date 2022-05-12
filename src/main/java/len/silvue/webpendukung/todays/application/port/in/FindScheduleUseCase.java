package len.silvue.webpendukung.todays.application.port.in;

import len.silvue.webpendukung.schedule.adapter.out.web.RouteDto;

public interface FindScheduleUseCase {
    RouteDto getScheduleById(int id) throws Exception;
}
