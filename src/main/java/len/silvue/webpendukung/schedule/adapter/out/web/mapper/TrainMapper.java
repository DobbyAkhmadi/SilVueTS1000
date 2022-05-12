package len.silvue.webpendukung.schedule.adapter.out.web.mapper;

import len.silvue.webpendukung.schedule.adapter.out.web.TrainDto;
import len.silvue.webpendukung.domains.Train;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TrainMapper {
    TrainMapper MAPPER = Mappers.getMapper(TrainMapper.class);

    TrainDto toTrainDto(Train train);
    Train toTrain(TrainDto trainDto);
    List<TrainDto> toTrainDtoList(List<Train> trainList);
    List<Train> toTrainList(List<TrainDto> trainDtoList);
}
