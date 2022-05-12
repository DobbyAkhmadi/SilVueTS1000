package len.silvue.webpendukung.schedule.adapter.out.web.mapper;

import len.silvue.webpendukung.schedule.adapter.out.web.ScheduleDto;
import len.silvue.webpendukung.domains.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ScheduleMapper {
    ScheduleMapper MAPPER = Mappers.getMapper(ScheduleMapper.class);

    Schedule toSchedule(ScheduleDto scheduleDto);
    ScheduleDto toScheduleDto(Schedule schedule);
    List<Schedule> toScheduleList(List<ScheduleDto> scheduleDtoList);
    List<ScheduleDto> toScheduleDtoList(List<Schedule> scheduleList);
}
