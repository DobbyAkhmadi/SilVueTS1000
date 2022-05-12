package len.silvue.webpendukung.user.adapter.in.web.mapper;

import len.silvue.webpendukung.user.adapter.in.web.model.UserDto;
import len.silvue.webpendukung.domains.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserRunningMapper {
    UserRunningMapper MAPPER = Mappers.getMapper(UserRunningMapper.class);

    UserDto toUserDto(User userName);
    User toUser(UserDto usernameDto);
    List<UserDto> toUserDtoList(List<User> userNameList);
    List<User> toUser(List<UserDto> userNameDtoList);
}
