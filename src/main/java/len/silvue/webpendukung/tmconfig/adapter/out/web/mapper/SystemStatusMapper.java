package len.silvue.webpendukung.tmconfig.adapter.out.web.mapper;

import len.silvue.webpendukung.tmconfig.adapter.out.web.SystemStatusDto;
import len.silvue.webpendukung.domains.SystemStatus;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SystemStatusMapper {
    SystemStatusMapper MAPPER = Mappers.getMapper(SystemStatusMapper.class);

    SystemStatus toSystemStatus(SystemStatusDto systemStatusDto);

    SystemStatusDto toSystemStatusDto(SystemStatus systemStatus);
}
