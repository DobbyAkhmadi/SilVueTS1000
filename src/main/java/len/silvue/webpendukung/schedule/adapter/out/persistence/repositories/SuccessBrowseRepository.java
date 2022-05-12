package len.silvue.webpendukung.schedule.adapter.out.persistence.repositories;

import len.silvue.webpendukung.domains.SuccessBrowse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuccessBrowseRepository extends JpaRepository<SuccessBrowse, Integer> {
}
