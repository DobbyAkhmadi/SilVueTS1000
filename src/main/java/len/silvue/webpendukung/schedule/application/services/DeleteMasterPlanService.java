package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.schedule.adapter.out.web.MasterPlanDto;
import len.silvue.webpendukung.schedule.application.port.in.DeleteMasterPlanUseCase;
import len.silvue.webpendukung.schedule.application.port.in.FindMasterPlanUseCase;
import len.silvue.webpendukung.schedule.application.port.out.DeleteMasterPlanPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteMasterPlanService implements DeleteMasterPlanUseCase {
    private final DeleteMasterPlanPort deleteMasterPlanPort;
    private final FindMasterPlanUseCase findMasterPlanUseCase;
    @Override
    public MasterPlanDto deleteMasterPlanByTrainId(int trainId) throws Exception {
        try {
            MasterPlanDto masterPlanDto  = findMasterPlanUseCase.getMasterPlanById(trainId);
            deleteMasterPlanPort.eraseMasterPlanByTrainId(trainId);
            return masterPlanDto;
        } catch (Exception e) {
            throw new Exception("Gagal delete schedule by id", e);
        }
    }
    @Override
    public void deleteAllMasterPlan() throws Exception {
        try {
            deleteMasterPlanPort.eraseAllMasterPlan();
        } catch (Exception e) {
            throw new Exception("Gagal delete all schedule ", e);
        }
    }

    @Override
    public void deleteAllMasterPlanByTrainId(int trainId) throws Exception {
        try {
            deleteMasterPlanPort.eraseAllMasterPlanByTrainId(trainId);
        } catch (Exception e) {
            throw new Exception("Gagal delete schedule by id", e);
        }
    }


}
