package len.silvue.webpendukung.schedule.adapter.out.web.mapper;

import len.silvue.webpendukung.schedule.adapter.out.web.RouteDto;
import len.silvue.webpendukung.domains.Route;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RouteMapper {
    RouteMapper MAPPER = Mappers.getMapper(RouteMapper.class);

    Route toRoute(RouteDto routeDto);
    RouteDto toRouteDto(Route route);
    List<Route> toRouteList(List<RouteDto> routeDtoList);
    List<RouteDto> toRouteDtoList(List<Route> routeList);

}
