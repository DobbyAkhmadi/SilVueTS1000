package len.silvue.webpendukung.user.adapter.out.persistence.repositories;


import len.silvue.webpendukung.domains.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
