package len.silvue.webpendukung.schedule.application.port.in;

import len.silvue.webpendukung.schedule.adapter.in.web.AddScheduleCommand;
import len.silvue.webpendukung.schedule.adapter.in.web.AddScheduleRouteCommand;
import len.silvue.webpendukung.schedule.adapter.out.web.RouteDto;
import len.silvue.webpendukung.domains.Schedule;
import len.silvue.webpendukung.utility.DataNotFoundException;

public interface AddScheduleUseCase {
    Schedule saveSchedule(AddScheduleCommand addScheduleCommand) throws Exception;
    RouteDto saveScheduleRoute(AddScheduleRouteCommand addScheduleRouteCommand) throws DataNotFoundException;
}
