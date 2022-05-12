package len.silvue.webpendukung.user.application.in;


import len.silvue.webpendukung.user.adapter.in.web.model.UserDto;

import java.util.List;
public interface UserUseCase {
    List<UserDto> getAllUser();
    UserDto getUserById(int id);
}
