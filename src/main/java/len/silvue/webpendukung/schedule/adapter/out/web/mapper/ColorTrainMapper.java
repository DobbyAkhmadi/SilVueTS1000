package len.silvue.webpendukung.schedule.adapter.out.web.mapper;

import len.silvue.webpendukung.schedule.adapter.out.web.ColorTrainDto;
import len.silvue.webpendukung.domains.ColorTrain;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ColorTrainMapper {
    ColorTrainMapper MAPPER = Mappers.getMapper(ColorTrainMapper.class);

    ColorTrainDto toColorTrainDto(ColorTrain colorTrain);
    ColorTrain toColorTrain(ColorTrainDto colorTrainDto);
    List<ColorTrainDto> toColorTrainDtoList(List<ColorTrain> colorTrainList);
    List<ColorTrain> toColorTrainList(List<ColorTrain> colorTrainDtoList);
}
