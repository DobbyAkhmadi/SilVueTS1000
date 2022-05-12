package len.silvue.webpendukung.schedule.adapter.out.web.mapper;

import len.silvue.webpendukung.schedule.adapter.out.web.MasterPlanDto;
import len.silvue.webpendukung.domains.MasterPlan;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MasterPlanMapper {
    MasterPlanMapper MAPPER = Mappers.getMapper(MasterPlanMapper.class);

    MasterPlanDto toMasterPlanDto(MasterPlan masterPlan);
    MasterPlan toMasterPlan(MasterPlanDto masterPlanDto);
    List<MasterPlanDto> toMasterPlanDtoList(List<MasterPlan> masterPlanList);
    List<MasterPlan> toMasterPlanList(List<MasterPlanDto> masterPlanDtoList);
}
