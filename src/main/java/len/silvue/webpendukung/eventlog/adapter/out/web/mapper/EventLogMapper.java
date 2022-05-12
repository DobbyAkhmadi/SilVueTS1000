package len.silvue.webpendukung.eventlog.adapter.out.web.mapper;

import len.silvue.webpendukung.eventlog.adapter.out.web.EventLogDto;
import len.silvue.webpendukung.domains.EventLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EventLogMapper {
    EventLogMapper MAPPER = Mappers.getMapper(EventLogMapper.class);
    List<EventLogDto> toEventDtoList(List<EventLog> numberTrainList);
    List<EventLogDto> toAlarmDtoList(List<EventLog> eventList);
}

