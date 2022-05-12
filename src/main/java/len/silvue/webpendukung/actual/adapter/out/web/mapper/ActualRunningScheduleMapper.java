package len.silvue.webpendukung.actual.adapter.out.web.mapper;

import len.silvue.webpendukung.actual.adapter.out.web.ActualRunningScheduleDto;
import len.silvue.webpendukung.domains.ActualPlan;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ActualRunningScheduleMapper {
    ActualRunningScheduleMapper MAPPER = Mappers.getMapper(ActualRunningScheduleMapper.class);

    ActualRunningScheduleDto toActualRunningScheduleDto(ActualPlan actual);
    ActualPlan toActualRunningSchedule(ActualRunningScheduleDto nameactual);
    List<ActualRunningScheduleDto> toActualRunningScheduleDtoList(List<ActualPlan> actualList);
    List<ActualPlan> toActualPlan(List<ActualRunningScheduleDto> nameactualDtoList);



}
