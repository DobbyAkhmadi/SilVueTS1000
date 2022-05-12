package len.silvue.webpendukung.schedule.adapter.out.web.mapper;

import len.silvue.webpendukung.schedule.adapter.out.web.PeronDto;
import len.silvue.webpendukung.domains.Peron;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PeronMapper {
    PeronMapper MAPPER = Mappers.getMapper(PeronMapper.class);

    PeronDto toPeronDto(Peron peron);
    Peron toPeron(PeronDto peron);
    List<PeronDto> toPeronDtoList(List<Peron> peronList);
    List<Peron> toPeronList(List<PeronDto> peronDtoList);
}
