package len.silvue.webpendukung.todays.adapter.out.persistence;

import len.silvue.webpendukung.todays.adapter.out.persistence.repositories.ConflictTableTodayRepository;
import len.silvue.webpendukung.todays.application.port.out.LoadConflictTableTodayPort;
import len.silvue.webpendukung.domains.ConflictTableToday;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConflictTableTodayPersistenceAdapter implements LoadConflictTableTodayPort {
    private final ConflictTableTodayRepository conflictTableMasterRepository;

    @Override
    public Optional<List<ConflictTableToday>> loadAllConflict() throws Exception {
        try {
            Optional<List<ConflictTableToday>> conflictTableMasterList = Optional.of(conflictTableMasterRepository.findAll());
            return conflictTableMasterList;
        } catch(Exception e) {
            throw new Exception("failed load Today by id", e);
        }
    }
}
