package len.silvue.webpendukung.ars.adapter.out.web.mapper;

import len.silvue.webpendukung.ars.adapter.out.web.ArsScheduleDto;
import len.silvue.webpendukung.domains.ArsSchedule;
import len.silvue.webpendukung.domains.RouteStick;
import len.silvue.webpendukung.schedule.adapter.out.web.RouteStickDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ArsScheduleMapper {
    len.silvue.webpendukung.ars.adapter.out.web.mapper.ArsScheduleMapper MAPPER = Mappers.getMapper(len.silvue.webpendukung.ars.adapter.out.web.mapper.ArsScheduleMapper.class);
    ArsScheduleDto toArsScheduleDto(ArsSchedule arsSchedule);
    RouteStickDto toRouteStickDto(RouteStick routeStick);
    ArsSchedule toArsSchedule(ArsScheduleDto arsScheduleDto);
    List<ArsScheduleDto> toArsScheduleDtoList(List<ArsSchedule> arsScheduleList);
    List<ArsSchedule> toArsScheduleList(List<ArsSchedule> arsScheduleList);

}

