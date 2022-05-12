package len.silvue.webpendukung.schedule.adapter.out.web.mapper;

import len.silvue.webpendukung.schedule.adapter.out.web.MasterScheduleDto;
import len.silvue.webpendukung.domains.MasterPlan;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MasterScheduleMapper {
    MasterScheduleMapper MAPPER = Mappers.getMapper(MasterScheduleMapper.class);
    List<MasterScheduleDto> toMasterScheduleList(List<MasterPlan> masterScheduleDtos);
}
