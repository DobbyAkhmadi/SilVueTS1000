package len.silvue.webpendukung.eventlog.application.port.in;

import len.silvue.webpendukung.eventlog.adapter.out.web.LogMessageDto;

import java.util.Date;
import java.util.List;

public interface LoadLogMessageUseCase {
    List<LogMessageDto> getAllLogMessage() throws Exception;

    List<LogMessageDto> getAllLogMessageByDate(Date timeDate) throws Exception;
}
