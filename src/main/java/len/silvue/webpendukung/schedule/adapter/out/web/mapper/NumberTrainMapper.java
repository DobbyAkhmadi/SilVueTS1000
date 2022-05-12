package len.silvue.webpendukung.schedule.adapter.out.web.mapper;

import len.silvue.webpendukung.schedule.adapter.out.web.NumberTrainDto;
import len.silvue.webpendukung.domains.NumberTrain;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NumberTrainMapper {
    NumberTrainMapper MAPPER = Mappers.getMapper(NumberTrainMapper.class);

    NumberTrainDto toNumberTrainDto(NumberTrain numberTrain);
    NumberTrain toNumberTrain(NumberTrainDto numberTrainDto);
    List<NumberTrainDto> toNumberTrainDtoList(List<NumberTrain> numberTrainList);
    List<NumberTrain> toNumberTrain(List<NumberTrainDto> numberTrainDtoList);
}
