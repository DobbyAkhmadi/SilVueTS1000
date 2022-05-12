package len.silvue.webpendukung.gapeka.adapter.out.persistence.repositories;

import len.silvue.webpendukung.domains.ListRuteDetail;
import len.silvue.webpendukung.domains.RuteRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ListRuteDetailRepository extends JpaRepository<ListRuteDetail, Integer> {
    @Query("SELECT ldp FROM ListRuteDetail ldp WHERE ldp.ruteRole=?1 ORDER BY ldp.indexListRuteDetail")
    List<ListRuteDetail> findListRuteDetailsByRuteRole(RuteRole ruteRole);

    @Query("SELECT DISTINCT m.ruteRole FROM ListRuteDetail m")
    List<RuteRole> findDistinctRuteRolesFromListRuteDetail() throws Exception;

    @Query("SELECT m FROM ListRuteDetail m order by m.ruteRole.idRuteRole , m.indexListRuteDetail")
    List<ListRuteDetail> findAllOrderByIdLineAndIndex() throws Exception;



    @Transactional
    @Modifying
    @Query(value = "delete from listrutedetail where idruterole=?1", nativeQuery = true)
    void deleteListRuteDetailByRuteRole(@Param("idruterole") int idruterole);

    @Query(value = "SELECT ldp FROM ListRuteDetail  ldp WHERE ldp.ruteRole.nameRoute=?1 ORDER BY ldp.indexListRuteDetail")
    List<ListRuteDetail> findListRuteDetailByRuteRoleName(String ruteRole) throws Exception;
}
