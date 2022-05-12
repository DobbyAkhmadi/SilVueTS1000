package len.silvue.webpendukung.todays.adapter.out.web.mapper;

import len.silvue.webpendukung.todays.adapter.out.web.TodayRunningScheduleDto;
import len.silvue.webpendukung.domains.TodayRunningSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TodayRunningScheduleMapper {
    TodayRunningScheduleMapper MAPPER = Mappers.getMapper(TodayRunningScheduleMapper.class);

    TodayRunningScheduleDto toTodayRunningScheduleDto(TodayRunningSchedule today);
    TodayRunningSchedule toTodayRunningSchedule(TodayRunningScheduleDto todayDto);
    List<TodayRunningScheduleDto> toTodayRunningScheduleDtoList(List<TodayRunningSchedule> todayRunningScheduleList);
    List<TodayRunningSchedule> toTodayRunningSchedule(List<TodayRunningScheduleDto> todayDtoList);
}
