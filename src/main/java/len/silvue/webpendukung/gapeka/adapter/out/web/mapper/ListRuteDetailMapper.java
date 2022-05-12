package len.silvue.webpendukung.gapeka.adapter.out.web.mapper;

import len.silvue.webpendukung.gapeka.adapter.out.web.ListRuteDetailDto;
import len.silvue.webpendukung.domains.ListRuteDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ListRuteDetailMapper {
    ListRuteDetailMapper MAPPER = Mappers.getMapper(ListRuteDetailMapper.class);

    ListRuteDetail toListRuteDetail(ListRuteDetailDto listRuteDetailDto);
    ListRuteDetailDto toListRuteDetailDto(ListRuteDetail listRuteDetail);
    List<ListRuteDetail> toListRuteDetailList(List<ListRuteDetailDto> listRuteDetailDtos);
    List<ListRuteDetailDto> toListRuteDetailDtoList(List<ListRuteDetail> listRuteDetails);
}
