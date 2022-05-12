package len.silvue.webpendukung.schedule.adapter.out.persistence;

import len.silvue.webpendukung.schedule.adapter.out.persistence.repositories.ConflictTableMasterRepository;
import len.silvue.webpendukung.schedule.application.port.out.LoadConflictTableMasterPort;
import len.silvue.webpendukung.domains.ConflictTableMaster;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConflictTableMasterPersistenceAdapter implements LoadConflictTableMasterPort {
    private final ConflictTableMasterRepository conflictTableMasterRepository;

    @Override
    public Optional<List<ConflictTableMaster>> loadAllConflict() throws Exception {
        try {
            Optional<List<ConflictTableMaster>> conflictTableMasterList = Optional.of(conflictTableMasterRepository.findAll());
            return conflictTableMasterList;
        } catch(Exception e) {
            throw new Exception("failed load masterPlan by id", e);
        }
    }
}
