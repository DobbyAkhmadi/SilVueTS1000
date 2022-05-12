package len.silvue.webpendukung.trainlocation.adapter.out.web.mapper;

import len.silvue.webpendukung.domains.RouteStickTrain;
import len.silvue.webpendukung.trainlocation.adapter.out.web.RouteStickTrainDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RouteStickTrainMapper {
    RouteStickTrainMapper MAPPER = Mappers.getMapper(RouteStickTrainMapper.class);

    RouteStickTrainDTO toRouteStickTrainDto(RouteStickTrain numberTrain);
    RouteStickTrain toRouteStickTrain(RouteStickTrainDTO numberTrainDto);
    List<RouteStickTrainDTO> toRouteStickTrainDtoList(List<RouteStickTrain> numberTrainList);
    List<RouteStickTrain> toRouteStickTrain(List<RouteStickTrainDTO> numberTrainDtoList);
}
