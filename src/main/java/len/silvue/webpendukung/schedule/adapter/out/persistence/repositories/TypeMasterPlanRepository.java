package len.silvue.webpendukung.schedule.adapter.out.persistence.repositories;

import len.silvue.webpendukung.domains.TypeMasterPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeMasterPlanRepository extends JpaRepository<TypeMasterPlan, Integer> {
    Optional<TypeMasterPlan>  findTypeMasterPlanByNameTypeMasterPlan(String nameTypeMasterPlan) throws Exception;
}
