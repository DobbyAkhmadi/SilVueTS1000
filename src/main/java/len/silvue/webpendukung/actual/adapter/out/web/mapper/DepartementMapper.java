package len.silvue.webpendukung.actual.adapter.out.web.mapper;

import len.silvue.webpendukung.actual.adapter.out.web.DepartementDto;

import len.silvue.webpendukung.domains.Departement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DepartementMapper {
    DepartementMapper MAPPER = Mappers.getMapper(DepartementMapper.class);

    Departement toDepartement(DepartementDto dto);
    DepartementDto toDepartementDto(Departement departement);
    List<Departement> toDepartementList(List<DepartementDto> departementDtos);
    List<DepartementDto> toDepartementDtoList(List<Departement> departements);
}
