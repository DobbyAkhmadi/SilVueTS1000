package len.silvue.webpendukung.gapeka.adapter.out.persistence.repositories;

import len.silvue.webpendukung.domains.Line;
import len.silvue.webpendukung.domains.ListLineDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ListLineDetailRepository extends JpaRepository<ListLineDetail, Integer> {
    List<ListLineDetail> findListLineDetailsByLine(Line line);

    @Query("SELECT m FROM ListLineDetail m order by m.line.idLine , m.indexListLineDetail")
    List<ListLineDetail> findAllOrderByIdLineAndIndex() throws Exception;


    @Query("SELECT DISTINCT m.line FROM ListLineDetail m")
    List<Line> findDistinctLinesFromListLineDetail() throws Exception;

    @Transactional
    @Modifying
    @Query(value = "delete from listlinedetail where idLine=?1", nativeQuery = true)
    void deleteListLineDetailByLine(@Param("idLine") int idLine);

    @Query("SELECT ll FROM ListLineDetail ll WHERE ll.line.nameLine=?1")
    List<ListLineDetail> findAllByNameLine(String nameLine) throws Exception;
}
