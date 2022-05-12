package len.silvue.webpendukung.tmconfig.adapter.out.web.mapper;

import len.silvue.webpendukung.tmconfig.adapter.out.web.SettingPrintDto;
import len.silvue.webpendukung.domains.SettingPrint;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SettingPrintMapper {
    SettingPrintMapper MAPPER = Mappers.getMapper(SettingPrintMapper.class);

    SettingPrint toSettingPrint(SettingPrintDto settingPrintDto);

    SettingPrintDto toSettingPrintDto(SettingPrint settingPrint);
}
