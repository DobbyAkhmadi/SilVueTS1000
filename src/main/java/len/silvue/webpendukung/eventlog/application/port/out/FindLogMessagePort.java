package len.silvue.webpendukung.eventlog.application.port.out;

import len.silvue.webpendukung.domains.LogMessage;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FindLogMessagePort {
    Optional<List<LogMessage>> loadAllLoadMessage() throws Exception;
    Optional<List<LogMessage>> loadAllLogMessageByDate(Date timeMessage) throws Exception;

}

