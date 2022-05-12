package len.silvue.webpendukung.gapeka.adapter.out.persistence.repositories;

import len.silvue.webpendukung.domains.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineRepository extends JpaRepository<Line, Integer> {
}
