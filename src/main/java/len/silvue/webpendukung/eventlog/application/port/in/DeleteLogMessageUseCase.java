package len.silvue.webpendukung.eventlog.application.port.in;

import len.silvue.webpendukung.eventlog.adapter.out.web.EventLogDto;


public interface DeleteLogMessageUseCase {


    EventLogDto deleteeventById(int idDepartement) throws Exception;
    void deleteAllEvent() throws Exception;

}
