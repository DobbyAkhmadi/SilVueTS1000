package len.silvue.webpendukung.setting.adapter.out.web.mapper;

import len.silvue.webpendukung.domains.ListRuteDetail;
import len.silvue.webpendukung.setting.adapter.out.web.SettingListRuteDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SettingMapper {
    SettingMapper MAPPER = Mappers.getMapper(SettingMapper.class);

    //ColorTrainDto toColorTrainDto(ColorTrain colorTrain);
    //ColorTrain toColorTrain(ColorTrainDto colorTrainDto);
    List<SettingListRuteDetailDto> getSettingListRuteDetailDtoList(List<ListRuteDetail> settingListRuteDetailDtoList);
    //List<ColorTrain> toColorTrainList(List<ColorTrain> colorTrainDtoList);
}
