package len.silvue.webpendukung.schedule.application.port.in;

import len.silvue.webpendukung.schedule.adapter.in.web.AddMasterPlanCommand;

public interface AddMasterPlanUseCase {
    void saveMasterPlan(AddMasterPlanCommand addMasterPlanCommand) throws Exception;
}
