package len.silvue.webpendukung.schedule.application.port.in;

import len.silvue.webpendukung.schedule.adapter.out.web.TypeMasterPlanDto;

import java.util.List;

public interface FindTypeMasterPlanUseCase {
    List<TypeMasterPlanDto> getAllTypeMasterPlan() throws Exception;
    TypeMasterPlanDto getTypeMasterPlanById(int id) throws Exception;
}
