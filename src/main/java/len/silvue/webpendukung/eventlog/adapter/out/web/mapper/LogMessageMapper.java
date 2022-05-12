package len.silvue.webpendukung.eventlog.adapter.out.web.mapper;

import len.silvue.webpendukung.domains.LogMessage;
import len.silvue.webpendukung.eventlog.adapter.out.web.LogMessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LogMessageMapper {
    LogMessageMapper MAPPER = Mappers.getMapper(LogMessageMapper.class);

    LogMessageDto toLogMessageDto(LogMessage logMessage);
    LogMessage toLogMessage(LogMessageDto logMessageDto);
    List<LogMessageDto> toLogMessageDtoList(List<LogMessage> logMessage);
    List<LogMessage> toLogMessageList(List<LogMessage> logMessages);
}
