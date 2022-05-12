package len.silvue.webpendukung.schedule.application.port.out;

import len.silvue.webpendukung.domains.TypeMasterPlan;

public interface DeleteMasterPlanPort {
    void eraseMasterPlanByTrainId(int trainId) throws Exception;
    void eraseAllMasterPlan() throws Exception;
    void eraseAllMasterPlanByTrainId(int trainId) throws Exception;
    void eraseAllMasterPlanByTypeMasterPlanName(String typeMasterPlanName) throws Exception;
    void eraseAllMasterPlanByTypeMasterPlan(TypeMasterPlan typeMasterPlan) throws Exception;
}
