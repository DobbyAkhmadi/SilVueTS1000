package len.silvue.webpendukung.schedule.adapter.out.persistence.repositories;

import len.silvue.webpendukung.domains.ColorTrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorTrainRepository extends JpaRepository<ColorTrain, Integer> {
}
