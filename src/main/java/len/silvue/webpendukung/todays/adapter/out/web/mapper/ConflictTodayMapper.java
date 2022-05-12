package len.silvue.webpendukung.todays.adapter.out.web.mapper;

import len.silvue.webpendukung.todays.adapter.out.web.ConflictTodayDto;
import len.silvue.webpendukung.domains.ConflictTableToday;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ConflictTodayMapper {
    ConflictTodayMapper MAPPER = Mappers.getMapper(ConflictTodayMapper.class);

    ConflictTodayDto toConflictMasterPlanDto(ConflictTableToday conflictTableMaster);
    ConflictTableToday toConflictTableMaster(ConflictTodayDto conflictMasterPlanDto);
    List<ConflictTodayDto> toConflictMasterPlanDto(List<ConflictTableToday> conflictTableMasterList);
}
