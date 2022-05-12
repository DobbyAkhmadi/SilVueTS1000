package len.silvue.webpendukung.gapeka.adapter.out.web.mapper;

import len.silvue.webpendukung.gapeka.adapter.out.web.LineDto;
import len.silvue.webpendukung.domains.Line;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LineMapper {
    LineMapper MAPPER = Mappers.getMapper(LineMapper.class);

    Line toLine(LineDto lineDto);
    LineDto toLineDto(Line line);
    List<Line> toLineList(List<LineDto> lineDtoList);
    List<LineDto> toLineDtoList(List<Line> lineList);
}
