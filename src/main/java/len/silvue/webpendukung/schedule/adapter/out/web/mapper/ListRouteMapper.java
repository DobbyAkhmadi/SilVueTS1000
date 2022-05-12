package len.silvue.webpendukung.schedule.adapter.out.web.mapper;

import len.silvue.webpendukung.gapeka.adapter.out.web.ListRuteDetailDto;
import len.silvue.webpendukung.domains.ListRuteDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ListRouteMapper {
    ListRouteMapper MAPPER = Mappers.getMapper(ListRouteMapper.class);
    ListRuteDetailDto toListRuteDetailDto(ListRuteDetail listRuteDetail);
}
