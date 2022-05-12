package len.silvue.webpendukung.actual.adapter.out.persistence.repositories;

import len.silvue.webpendukung.domains.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Integer> {
}
