package len.silvue.webpendukung.schedule.adapter.out.web.mapper;

import len.silvue.webpendukung.schedule.adapter.out.web.StationDto;
import len.silvue.webpendukung.domains.Station;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StationMapper {
    StationMapper MAPPER = Mappers.getMapper(StationMapper.class);

    StationDto toStationDto(Station station);
    Station toStation(StationDto stationDto);
    List<Station> toStationList(List<StationDto> stationDtoList);
    List<StationDto> toStationDtoList(List<Station> stationList);
}
