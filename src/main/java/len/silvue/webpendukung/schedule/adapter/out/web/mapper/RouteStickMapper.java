package len.silvue.webpendukung.schedule.adapter.out.web.mapper;

import len.silvue.webpendukung.schedule.adapter.out.web.RouteStickDto;
import len.silvue.webpendukung.domains.RouteStick;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RouteStickMapper {
    RouteStickMapper MAPPER = Mappers.getMapper(RouteStickMapper.class);

    RouteStick toRouteStick(RouteStickDto dto);
    RouteStickDto toRouteStickDto(RouteStick routeStick);
    List<RouteStick> toRouteStickList(List<RouteStickDto> routeStickDtos);
    List<RouteStickDto> toRouteStickDtoList(List<RouteStick> routeSticks);
}
