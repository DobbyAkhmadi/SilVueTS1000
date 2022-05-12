package len.silvue.webpendukung.schedule.application.port.in;

import len.silvue.webpendukung.schedule.adapter.in.web.CombineMasterPlanCommand;
import len.silvue.webpendukung.schedule.adapter.in.web.ModifyMasterPlanCommand;
import len.silvue.webpendukung.schedule.adapter.out.web.MasterPlanDto;

import java.util.List;

public interface ModifyMasterPlanUseCase {
    List<MasterPlanDto> modifyAllMasterPlan(ModifyMasterPlanCommand modifyMasterPlanCommand) throws Exception;
    MasterPlanDto modifyMasterPlan(ModifyMasterPlanCommand modifyMasterPlanCommand) throws Exception;
    List<MasterPlanDto> combineMasterPlanByTypeMasterPlan(CombineMasterPlanCommand combineMasterPlanCommand) throws Exception;
}
