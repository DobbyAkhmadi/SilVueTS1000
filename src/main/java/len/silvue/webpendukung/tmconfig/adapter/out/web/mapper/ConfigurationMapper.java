package len.silvue.webpendukung.tmconfig.adapter.out.web.mapper;

import len.silvue.webpendukung.tmconfig.adapter.out.web.ConfigurationDto;
import len.silvue.webpendukung.domains.Configuration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConfigurationMapper {
    ConfigurationMapper MAPPER = Mappers.getMapper(ConfigurationMapper.class);

    Configuration toConfiguration(ConfigurationDto dto);

    ConfigurationDto toConfigurationDto(Configuration config);
}
