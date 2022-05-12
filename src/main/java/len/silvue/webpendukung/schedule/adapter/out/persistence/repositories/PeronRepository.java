package len.silvue.webpendukung.schedule.adapter.out.persistence.repositories;

import len.silvue.webpendukung.domains.Peron;
import len.silvue.webpendukung.domains.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeronRepository extends JpaRepository<Peron, Integer> {
    Optional<List<Peron>> findPeronsByStation(Station station);
}
