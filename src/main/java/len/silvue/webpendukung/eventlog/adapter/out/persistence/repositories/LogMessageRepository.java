package len.silvue.webpendukung.eventlog.adapter.out.persistence.repositories;

import len.silvue.webpendukung.domains.LogMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface LogMessageRepository extends JpaRepository<LogMessage, Integer> {
    @Query("SELECT lm FROM LogMessage  lm ORDER BY lm.timeLogMessage DESC")
    Optional<List<LogMessage>> findAllLogMessage();

    @Query("SELECT lm FROM LogMessage lm WHERE lm.timeLogMessage=?1")
    Optional<List<LogMessage>> findLogMessageByDate(Date timeMessage);

}
