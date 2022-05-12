package len.silvue.webpendukung.schedule.adapter.out.web.mapper;

import len.silvue.webpendukung.schedule.adapter.out.web.RuteRoleDto;
import len.silvue.webpendukung.domains.RuteRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RuteRoleMapper {
    RuteRoleMapper MAPPER = Mappers.getMapper(RuteRoleMapper.class);

    RuteRoleDto toRuteRoleDto(RuteRole ruteRole);
    RuteRole toRuteRole(RuteRoleDto ruteRoleDto);
    List<RuteRoleDto> toRuteRoleDtoList(List<RuteRole> ruteRoleList);
    List<RuteRole> toRuteRoleList(List<RuteRoleDto> ruteRoleDtoList);
}
