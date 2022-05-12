package len.silvue.webpendukung.gapeka.adapter.out.web.mapper;

import len.silvue.webpendukung.gapeka.adapter.out.web.ListLineDetailDto;
import len.silvue.webpendukung.domains.ListLineDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ListLineDetailMapper {
    ListLineDetailMapper MAPPER = Mappers.getMapper(ListLineDetailMapper.class);

    ListLineDetail toListLineDetail(ListLineDetailDto listLineDetailDto);
    ListLineDetailDto toListLineDetailDto(ListLineDetail listLineDetail);
    List<ListLineDetail> toListLineDetailDto(List<ListLineDetailDto> listLineDetailDtoList);
    List<ListLineDetailDto> toListLineDetailDtoList(List<ListLineDetail> listLineDetails);
}
