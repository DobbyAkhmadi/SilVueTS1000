package len.silvue.webpendukung.ars.adapter.out.persistence.repositories;

import len.silvue.webpendukung.domains.ArsSchedule;
import len.silvue.webpendukung.domains.TodayRunningSchedule;
import len.silvue.webpendukung.domains.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArsScheduleRepository extends JpaRepository<ArsSchedule,Integer> {
    @Query("SELECT ar FROM ArsSchedule  ar WHERE ar.scheduleStatusArs=0 ORDER BY ar.train.noka, ar.departArs")
    List<ArsSchedule> findAllArsScheduleByScheduleStatusArsIs0() throws Exception;

    @Query("SELECT ar FROM ArsSchedule  ar WHERE ar.train.idTrain=?1")
    List<ArsSchedule> findArsScheduleByTrain(int idTrain) throws Exception;

    @Modifying
    @Transactional
    @Query(value = "UPDATE SYSTEMSTATUS SET LASTUPDATEARS = NOW() WHERE IDSYSTEMSTATUS = 1", nativeQuery = true)
    void saveBtn();
}
