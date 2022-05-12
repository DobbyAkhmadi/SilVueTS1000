package len.silvue.webpendukung.eventlog.adapter.out.persistence;

import len.silvue.webpendukung.domains.LogMessage;
import len.silvue.webpendukung.eventlog.adapter.out.persistence.repositories.LogMessageRepository;
import len.silvue.webpendukung.eventlog.application.port.out.DeleteLogMessagePort;
import len.silvue.webpendukung.eventlog.application.port.out.FindLogMessagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LogMessageAdapter implements FindLogMessagePort, DeleteLogMessagePort {
    private final LogMessageRepository logMessageRepository;

    @Override
    public Optional<List<LogMessage>> loadAllLoadMessage() throws Exception {
        try{
            return logMessageRepository.findAllLogMessage();
        } catch(Exception e) {
            throw new Exception("Gagal mengambil semua event", e);
        }
    }

    @Override
    public Optional<List<LogMessage>> loadAllLogMessageByDate(Date timeMessage) throws Exception {
        try {
            return logMessageRepository.findLogMessageByDate(timeMessage);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil Data Alarm", e);
        }
    }

    @Override
    public void eraseEventtById(int idDepartement) throws Exception {

    }

    @Override
    public void eraseAllEvent() throws Exception {
        try {
            logMessageRepository.deleteAll();
        } catch(Exception e) {
            throw new Exception("failed to delete station all repository", e);
        }
    }
}

