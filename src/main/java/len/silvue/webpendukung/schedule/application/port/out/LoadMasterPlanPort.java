package len.silvue.webpendukung.schedule.application.port.out;

import len.silvue.webpendukung.domains.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LoadMasterPlanPort {
    Optional<List<MasterPlan>> loadAllMasterPlan() throws Exception;
    Optional<List<MasterPlan>> loadMasterPlanOrderByNokaAndArrivalAndDepart() throws Exception;
    Optional<List<MasterPlan>> loadMasterPlanByTypeMasterPlanAndRuteRole(TypeMasterPlan typeMasterPlan, RuteRole ruteRole) throws Exception;
    Optional<List<Train>> loadTrainsFromMasterPlanByTypeMasterPlanAndRuteRole(TypeMasterPlan typeMasterPlan, RuteRole ruteRole) throws Exception;
    Optional<Date> loadMinimumArrivalFromMasterPlanByTypeMasterPlanAndRuteRole(TypeMasterPlan typeMasterPlan, RuteRole ruteRole) throws Exception;
    Optional<Date> loadMaximumDepartFromMasterPlanByTypeMasterPlanAndRuteRole(TypeMasterPlan typeMasterPlan, RuteRole ruteRole) throws Exception;
    Optional<List<MasterPlan>> loadMasterPlanByTypeMasterPlanAndRuteRoleAndTrain(TypeMasterPlan typeMasterPlan, RuteRole ruteRole, Train train) throws Exception;
    Optional<List<Station>> loadStationsFromMasterPlanByTypeMasterPlanAndRuteRole(TypeMasterPlan typeMasterPlan, RuteRole ruteRole) throws Exception;
    Optional<List<MasterPlan>> loadMasterPlanByTrain(int idTrain) throws Exception;
    Optional<MasterPlan> loadMasterPlanById(int idMasterPlan) throws Exception;
    Optional<List<MasterPlan>> loadAllMasterPlanByTypeMasterPlan(int idTypeMasterPlan) throws Exception;
    Optional<MasterPlan> loadMasterPlanByTypeMasterPlan(int idMasterPlan) throws Exception;
    Optional<List<MasterPlan>> loadMasterPlanByTrainAndTypeMasterPlan(Train train, TypeMasterPlan typeMasterPlan) throws Exception;
    Optional<List<MasterPlan>> loadAllMasterPlanByIDAAndB(int idTypeMasterPlanA, int idTypeMasterPlanB) throws Exception;
}
