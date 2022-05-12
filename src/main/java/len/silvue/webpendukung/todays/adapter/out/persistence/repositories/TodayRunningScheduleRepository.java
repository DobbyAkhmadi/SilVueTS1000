package len.silvue.webpendukung.todays.adapter.out.persistence.repositories;

import len.silvue.webpendukung.domains.Train;
import len.silvue.webpendukung.domains.TypeMasterPlan;
import len.silvue.webpendukung.domains.TodayRunningSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodayRunningScheduleRepository extends JpaRepository<TodayRunningSchedule,Integer> {
    Optional<List<TodayRunningSchedule>> findTodayRunningScheduleByTrainAndTypeMasterPlan(Train train, TypeMasterPlan typeMasterPlan);

    @Query(value = "SELECT mp FROM TodayRunningSchedule mp WHERE mp.typeMasterPlan.idTypeMasterPlan = ?1 AND mp.ruteRole.idRuteRole = ?2 ORDER BY mp.train.idTrain, mp.peronFrom.station.idStation, mp.ruteRole.idRuteRole")
    Optional<List<TodayRunningSchedule>> findAllByTypeMasterPlanIdAndRuteRoleIdOrderByTrainIdAndStationidAndRuteRoleId(int idTypeMasterPlan, int idRuteRole);

   // @Query(value = "select * from todayrunningschedule where idTypeMasterPlan=?1 order by idtrain", nativeQuery = true)
  //  Optional<List<TodayRunningSchedule>> findTodayRunningScheduleByTrain(@Param("idTypeMasterPlan") int idTypeMasterPlan);

    @Query("SELECT mp FROM TodayRunningSchedule mp WHERE mp.train.idTrain=?1")
    Optional<List<TodayRunningSchedule>> findTodayRunningScheduleByTrain(int idTrain);

    @Query(value = "delete from todayrunningschedule where idtrain=?1", nativeQuery = true)
    Optional<List<TodayRunningSchedule>> deleteTodayRunningScheduleByTrain(@Param("idtrain") int idtrain);

    @Transactional
    @Modifying
    @Query(value = "delete from todayrunningschedule where idtrain=?1", nativeQuery = true)
    void deleteTodayDetailByTrain(@Param("idtrain") int idtrain);

    @Query(value = "SELECT td FROM TodayRunningSchedule  td WHERE td.typeMasterPlan.idTypeMasterPlan = ?1 ORDER BY td.train.idTrain, td.peronFrom.station.idStation")
    Optional<List<TodayRunningSchedule>> findAllByTypeMasterPlanId(int typeMasterPlanId) throws Exception;

    @Query(value = "select flagtoday from TodayRunningSchedule ", nativeQuery = true)
    Optional <List<TodayRunningSchedule>> getFlagToday() throws Exception ;

    @Transactional
    @Modifying
    @Query(value = "delete from todayrunningschedule where idTypeMasterPlan=?1", nativeQuery = true)
    void deleteTodayRunningScheduleByTypeMasterPlan(@Param("idTypeMasterPlan") int idTypeMasterPlan);

    @Query("SELECT td FROM TodayRunningSchedule td ORDER BY td.train.noka")
    List<TodayRunningSchedule> findAllOrderByTrain() throws Exception;
}
