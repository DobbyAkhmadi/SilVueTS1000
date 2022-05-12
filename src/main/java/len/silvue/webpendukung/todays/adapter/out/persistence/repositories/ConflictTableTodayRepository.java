package len.silvue.webpendukung.todays.adapter.out.persistence.repositories;

import len.silvue.webpendukung.domains.ConflictTableToday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConflictTableTodayRepository extends JpaRepository<ConflictTableToday, Integer> {

}
