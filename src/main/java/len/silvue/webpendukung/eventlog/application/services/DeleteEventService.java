package len.silvue.webpendukung.eventlog.application.services;

import len.silvue.webpendukung.eventlog.adapter.out.web.EventLogDto;
import len.silvue.webpendukung.eventlog.application.port.in.DeleteLogMessageUseCase;
import len.silvue.webpendukung.eventlog.application.port.out.DeleteLogMessagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DeleteEventService implements DeleteLogMessageUseCase {
   private final DeleteLogMessagePort deleteLogMessagePort;


    @Override
    public EventLogDto deleteeventById(int idDepartement) throws Exception {
        return null;
    }

    @Override
    public void deleteAllEvent() throws Exception {
        try {
            deleteLogMessagePort.eraseAllEvent();
        } catch (Exception e) {
            throw new Exception("Gagal delete all schedule ", e);
        }
    }
}
