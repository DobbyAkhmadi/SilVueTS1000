package len.silvue.webpendukung.schedule.application.port.in;

import len.silvue.webpendukung.schedule.adapter.out.web.RouteDto;

import java.util.List;

public interface FindRouteUseCase {
    List<RouteDto> getAllRoute() throws Exception;
    RouteDto getRouteById(int id) throws Exception;
}
