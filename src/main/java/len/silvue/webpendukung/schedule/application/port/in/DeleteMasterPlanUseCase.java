package len.silvue.webpendukung.schedule.application.port.in;

import len.silvue.webpendukung.schedule.adapter.out.web.MasterPlanDto;

public interface DeleteMasterPlanUseCase {
    MasterPlanDto deleteMasterPlanByTrainId(int trainId) throws Exception;
    void deleteAllMasterPlan() throws Exception;
    void deleteAllMasterPlanByTrainId(int trainId) throws Exception;
}
