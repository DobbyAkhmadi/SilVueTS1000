package len.silvue.webpendukung.schedule.adapter.out.web.mapper;

import len.silvue.webpendukung.schedule.adapter.out.web.TypeMasterPlanDto;
import len.silvue.webpendukung.domains.TypeMasterPlan;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TypeMasterPlanMapper {
    TypeMasterPlanMapper MAPPER = Mappers.getMapper(TypeMasterPlanMapper.class);

    TypeMasterPlan toTypeMasterPlan(TypeMasterPlanDto dto);
    TypeMasterPlanDto toTypeMasterPlanDto(TypeMasterPlan typeMasterPlan);
    List<TypeMasterPlan> toTypeMasterPlanList(List<TypeMasterPlanDto> typeMasterPlanDtos);
    List<TypeMasterPlanDto> toTypeMasterPlanDtoList(List<TypeMasterPlan> typeMasterPlans);
}
