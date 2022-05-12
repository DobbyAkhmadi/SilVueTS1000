package len.silvue.webpendukung.schedule.adapter.out.web.mapper;

import len.silvue.webpendukung.schedule.adapter.out.web.ConflictMasterPlanDto;
import len.silvue.webpendukung.domains.ConflictTableMaster;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ConflictMasterPlanMapper {
    ConflictMasterPlanMapper MAPPER = Mappers.getMapper(ConflictMasterPlanMapper.class);

    ConflictMasterPlanDto toConflictMasterPlanDto(ConflictTableMaster conflictTableMaster);
    ConflictTableMaster toConflictTableMaster(ConflictMasterPlanDto conflictMasterPlanDto);
    List<ConflictMasterPlanDto> toConflictMasterPlanDto(List<ConflictTableMaster> conflictTableMasterList);
}
