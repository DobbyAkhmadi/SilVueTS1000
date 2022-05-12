package len.silvue.webpendukung.todays.adapter.out.persistence.repositories;

import len.silvue.webpendukung.domains.SuccessBrowseConflict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuccessRepository extends JpaRepository<SuccessBrowseConflict, Integer> {
}
