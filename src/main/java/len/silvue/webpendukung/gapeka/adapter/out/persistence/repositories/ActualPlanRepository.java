package len.silvue.webpendukung.gapeka.adapter.out.persistence.repositories;

import len.silvue.webpendukung.domains.ActualPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ActualPlanRepository extends JpaRepository<ActualPlan, Integer> {
    List<ActualPlan> findActualPlansByRuteRoleActualPlan(String ruterRoleActualPlan) throws Exception;

    @Query("SELECT ap FROM ActualPlan ap WHERE ap.typePlan=?1 AND ap.actualCode=?2 AND ap.ruteRoleActualPlan=?3 AND ap.indexActual = (SELECT MAX(ap.indexActual) FROM ActualPlan ap) ORDER BY ap.trainActualPlan, ap.statiunActualPlan")
    List<ActualPlan> findActualPlansByActualCodeAndRuteRoleActualPlan(String typePlan, Date actualCode, String ruteRole) throws Exception;

    @Query("SELECT DISTINCT ap.statiunActualPlan FROM ActualPlan ap WHERE ap.actualCode=?1")
    List<String> findDistinctStatiunByActualCode(Date actualCodeDate) throws Exception;

    @Query("SELECT MIN(ap.arriveActualPlan) FROM ActualPlan ap WHERE ap.actualCode=?1")
    Date findMinimumArriveActualPlanByActualCode(Date actualCodeDate) throws Exception;

    @Query("SELECT MAX(ap.departActualPlan) FROM ActualPlan ap WHERE ap.actualCode=?1")
    Date findMaximumDepartActualPlanByActualCode(Date actualPlanDate) throws Exception;

    @Query("SELECT DISTINCT ap.trainActualPlan FROM ActualPlan ap WHERE ap.actualCode=?1")
    List<String> findDistinctTrainByActualCode(Date actualCodeDate) throws Exception;

    @Query("SELECT ap FROM ActualPlan ap WHERE ap.actualCode=?1 AND ap.typePlan=?2 AND ap.indexActual = (SELECT MAX(ap.indexActual) FROM ActualPlan ap) ORDER BY ap.trainActualPlan, ap.arriveActualPlan")
    List<ActualPlan> findActualPlansByActualCodeAndTypePlan(Date actualCode, String typePlan) throws Exception;
}
