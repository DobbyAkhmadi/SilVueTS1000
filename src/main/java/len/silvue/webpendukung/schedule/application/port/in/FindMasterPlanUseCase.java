package len.silvue.webpendukung.schedule.application.port.in;

import len.silvue.webpendukung.schedule.adapter.out.web.*;
import len.silvue.webpendukung.utility.DataNotFoundException;

import java.util.List;

public interface FindMasterPlanUseCase {
    List<MasterPlanDto> getAllMasterPlan() throws Exception;
    List<MasterPlanDto> getAllMasterPlanByTypeMasterPlan(int id) throws Exception;
    MasterPlanDto getMasterPlanById(int id) throws DataNotFoundException;
    MasterPlanDto getHeadMasterPlan(int id) throws DataNotFoundException;
    List<MasterPlanDto> getMasterPlanByTrainIdAndTypeMasterPlanId(int trainId, int typeMasterPlanId) throws Exception;
   // List<MasterScheduleDto> getAllMasterScheduleByTypeMasterPlan(int typeMasterPlanId) throws Exception;
}
