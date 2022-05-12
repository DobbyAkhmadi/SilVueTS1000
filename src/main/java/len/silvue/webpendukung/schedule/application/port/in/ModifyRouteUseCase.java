package len.silvue.webpendukung.schedule.application.port.in;

import len.silvue.webpendukung.schedule.adapter.in.web.ModifyRouteCommand;
import len.silvue.webpendukung.schedule.adapter.out.web.RouteDto;

public interface ModifyRouteUseCase {
    RouteDto modifyRoute(ModifyRouteCommand modifyRouteCommand) throws Exception;
}
