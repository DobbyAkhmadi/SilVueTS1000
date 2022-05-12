package len.silvue.webpendukung.schedule.application.port.in;

import len.silvue.webpendukung.schedule.adapter.out.web.RouteStickDto;

import java.util.List;

public interface FindRouteStickUseCase {
    List<RouteStickDto> getAllRouteStick() throws Exception;
}
