package len.silvue.webpendukung.schedule.adapter.out.persistence.repositories;

import len.silvue.webpendukung.domains.*;
import org.springframework.data.domain.Sort;
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
public interface MasterPlanRepository extends JpaRepository<MasterPlan, Integer> {
    List<MasterPlan> findByArrivalIsNotNullAndDepartIsNotNull() throws Exception;
    List<MasterPlan> findByArrivalIsNotNullOrDepartIsNotNull(Sort sort) throws Exception;

    @Query("SELECT mp FROM MasterPlan mp WHERE mp.typeMasterPlan=?1 AND mp.ruteRole=?2 ORDER BY mp.train.noka, mp.arrival")
    List<MasterPlan> findByTypeMasterPlanAndRuteRole(TypeMasterPlan typeMasterPlan, RuteRole ruteRole) throws Exception;

    @Query("SELECT DISTINCT m.train FROM MasterPlan m WHERE m.ruteRole=?2 AND m.typeMasterPlan=?1")
    List<Train> findDistinctTrainsFromMasterPlanByTypeMasterPlanAndRuteRole(TypeMasterPlan typeMasterPlan, RuteRole ruteRole) throws Exception;

    @Query("SELECT min(m.arrival) FROM MasterPlan m WHERE m.typeMasterPlan=?1 AND m.ruteRole=?2")
    Date findMinimumArrivalFromMasterPlanByTypeMasterPlanAndRuteRole(TypeMasterPlan typeMasterPlan, RuteRole ruteRole) throws Exception;

    @Query("SELECT max(m.depart) FROM MasterPlan m WHERE m.typeMasterPlan=?1 AND m.ruteRole=?2")
    Date findMaximumDepartFromMasterPlanByTypeMasterPlanAndRuteRole(TypeMasterPlan typeMasterPlan, RuteRole ruteRole) throws Exception;

    @Query("SELECT DISTINCT m.peronFrom.station FROM MasterPlan  m WHERE m.typeMasterPlan=?1 AND m.ruteRole=?2")
    List<Station> findDistinctStationsFromMasterPlanByTypeMasterPlanAndRuteRole(TypeMasterPlan typeMasterPlan, RuteRole ruteRole) throws Exception;

    @Query("SELECT mp FROM MasterPlan mp WHERE mp.typeMasterPlan=?1 AND mp.ruteRole=?2 AND mp.train=?3 ORDER BY mp.train.noka, mp.arrival")
    List<MasterPlan> findMasterPlanByTypeMasterPlanAndRuteRoleAndTrain(TypeMasterPlan typeMasterPlan, RuteRole ruteRole, Train train) throws Exception;

    @Query(value = "SELECT mp FROM MasterPlan mp WHERE mp.typeMasterPlan.idTypeMasterPlan = ?1 ORDER BY mp.train.idTrain, mp.ruteRole.idRuteRole, mp.peronFrom.station.idStation")
    Optional<List<MasterPlan>> findMasterPlanByTypeMasterPlan(@Param("idTypeMasterPlan") int idTypeMasterPlan);

    @Query("SELECT mp FROM MasterPlan mp WHERE mp.train.idTrain=?1")
    Optional<List<MasterPlan>> findMasterPlanByTrain(int idTrain);

    Optional<List<MasterPlan>> findMasterPlanByTrainAndTypeMasterPlan(Train train, TypeMasterPlan typeMasterPlan);

    @Query(value = "select DISTINCT p.idTrain from masterplan p.idmasterplan where p.idTypeMasterPlan=?1 order by idTrain", nativeQuery = true)
    Optional<List<MasterPlan>> findDistinctByTypeMasterPlan(int typeMasterPlan);

    @Query(value = "select * from masterplan mp where mp.idmasterplan=?1 or mp.idmasterplan=?2 order by idTrain", nativeQuery = true)
    Optional<List<MasterPlan>> findMasterPlanByIdMasterPlanAndIdMasterPlan(int typeMasterPlanA,int typeMasterPlanB);

    @Transactional
    @Modifying
    @Query(value = "delete from masterplan where idtrain=?1", nativeQuery = true)
    void deleteMasterPlanByTrain(@Param("idtrain") int idtrain);

    @Query("SELECT mp FROM MasterPlan mp ORDER BY mp.train.noka")
    List<MasterPlan> findAllOrderByTrain() throws Exception;

    @Transactional
    @Modifying
    @Query("DELETE FROM MasterPlan mp WHERE mp.typeMasterPlan.nameTypeMasterPlan=?1")
    void deleteAllByTypeMasterPlanName(String typeMasterPlanName) throws Exception;

    @Transactional
    @Modifying
    void deleteAllByTypeMasterPlan(TypeMasterPlan typeMasterPlan) throws Exception;
}
