package len.silvue.webpendukung.schedule.adapter.out.persistence.repositories;

import len.silvue.webpendukung.domains.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
    Optional<Station> findStationByNameStation(String nameStation);
}
