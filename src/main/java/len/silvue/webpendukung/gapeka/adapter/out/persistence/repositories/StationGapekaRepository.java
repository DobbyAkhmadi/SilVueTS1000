package len.silvue.webpendukung.gapeka.adapter.out.persistence.repositories;

import len.silvue.webpendukung.domains.StationGapeka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationGapekaRepository extends JpaRepository<StationGapeka, Integer> {
}
