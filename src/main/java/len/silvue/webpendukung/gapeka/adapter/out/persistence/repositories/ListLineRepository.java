package len.silvue.webpendukung.gapeka.adapter.out.persistence.repositories;

import len.silvue.webpendukung.domains.ListLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListLineRepository extends JpaRepository<ListLine, Integer> {
    @Query("SELECT ll FROM ListLine ll WHERE ll.line.idLine = ?1")
    List<ListLine> findListLinesByLineId(int lineId) throws Exception;
}
