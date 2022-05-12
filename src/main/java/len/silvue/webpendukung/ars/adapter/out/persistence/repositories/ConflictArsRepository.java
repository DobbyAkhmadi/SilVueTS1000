package len.silvue.webpendukung.ars.adapter.out.persistence.repositories;

import len.silvue.webpendukung.domains.ArsConflict;
import len.silvue.webpendukung.domains.ArsSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConflictArsRepository extends JpaRepository<ArsConflict,Integer> {

}
