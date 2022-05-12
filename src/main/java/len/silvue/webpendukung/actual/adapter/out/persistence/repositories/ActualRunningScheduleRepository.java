package len.silvue.webpendukung.actual.adapter.out.persistence.repositories;

import len.silvue.webpendukung.domains.ActualPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ActualRunningScheduleRepository extends JpaRepository<ActualPlan,Integer> {
    @Query("SELECT MAX(ac.timeData) FROM ActualPlan ac")
    Optional<Date> findMaxDateFromActualPlan();

    @Query(value = "delete from actualplan where idActualPlan=?1", nativeQuery = true)
    Optional<List<ActualPlan>> deleteActualScheduleById(@Param("idActualPlan") int idActualPlan);

    @Query(value = "select * from actualplan where trainActualPlan=?1", nativeQuery = true)
    Optional<List<ActualPlan>> findActualPlanByTrainActualPlan(String trainActualPlan);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ActualPlan cc WHERE cc.actualCode=?1")
    void deleteActualScheduleBySpesificDate(Date actualCode);

    @Query(value = "SELECT DISTINCT ap.typePlan FROM ActualPlan ap WHERE ap.actualCode=?1")
    Optional<List<String>> findDistinctTypePlanByActualCode(Date actualCode);

    @Query(value = "SELECT DISTINCT ap.ruteRoleActualPlan FROM ActualPlan ap WHERE ap.actualCode=?1")
    Optional<List<String>> findDistinctRuteRoleByActualCode(Date actualCode);

    @Query(value = "SELECT DISTINCT ap.typePlan FROM ActualPlan ap")
    Optional<List<String>> findDistinctAllTypePlan();

    @Query(value = "SELECT DISTINCT ap.ruteRoleActualPlan FROM ActualPlan ap")
    Optional<List<String>> findDistinctAllRuteRole();

    @Query(value = "select * from actualplan where actualCode between ?1 and ?2", nativeQuery = true)
    Optional<List<ActualPlan>> findActualPlanByActualCode(Date actualCodeFrom, Date actualCodeTo);

    @Query(value = "select * from actualplan where actualCode=?1", nativeQuery = true)
    Optional<List<ActualPlan>> selectActualPlanByActualCode(Date selectActualCode);

    @Query(value = "select max (indexactual) from actualplan ", nativeQuery = true)
    Optional <Integer> getmaxindeactual() throws Exception ;

    @Query(value = "select DISTINCT a.indexActual from ActualPlan a order by a.indexActual")
    Optional<List<Integer>> findDistinctByIndexActual();

    Optional<List<ActualPlan>> findAllByIndexActualOrderByTrainActualPlan(int index);
}
