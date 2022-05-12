package len.silvue.webpendukung.eventlog.application.services;

import len.silvue.webpendukung.domains.LogMessage;
import len.silvue.webpendukung.eventlog.adapter.out.web.EventLogDto;
import len.silvue.webpendukung.eventlog.adapter.out.web.LogMessageDto;
import len.silvue.webpendukung.eventlog.adapter.out.web.mapper.EventLogMapper;
import len.silvue.webpendukung.eventlog.adapter.out.web.mapper.LogMessageMapper;
import len.silvue.webpendukung.eventlog.application.port.in.LoadLogMessageUseCase;
import len.silvue.webpendukung.eventlog.application.port.out.FindLogMessagePort;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindLoadServiceMessage implements LoadLogMessageUseCase {
    private final FindLogMessagePort findLogMessagePort;
    private final LogMessageMapper mapper;

    @Override
    public List<LogMessageDto> getAllLogMessageByDate(Date timeMessage) throws Exception {
        try {

            Optional<List<LogMessage>> optionalEventLogList = findLogMessagePort.loadAllLogMessageByDate(timeMessage);
            List<LogMessage> logMessages = optionalEventLogList.orElse(new ArrayList<>());
            return mapper.toLogMessageDtoList(logMessages);
        } catch (Exception e){
            throw new DataNotFoundException("Data Alarm Kosong",e);
        }
    }

    @Override
    public List<LogMessageDto> getAllLogMessage() throws Exception {
        try {
            Optional<List<LogMessage>> optionalLogMessages = findLogMessagePort.loadAllLoadMessage();
            List<LogMessage> logMessages = optionalLogMessages.orElse(new ArrayList<>());
            return mapper.toLogMessageDtoList(logMessages);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil data ars schedule", e);
        }
    }
}
