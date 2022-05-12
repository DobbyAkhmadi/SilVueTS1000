package len.silvue.webpendukung.actual.application.port.in;

import len.silvue.webpendukung.schedule.adapter.out.web.RouteDto;

public interface FindRouteUseCase {
    RouteDto getRouteById(int id) throws Exception;
}
