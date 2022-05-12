package len.silvue.webpendukung.tmconfig.adapter.out.persistence.repositories;

import len.silvue.webpendukung.domains.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Integer> {
}
