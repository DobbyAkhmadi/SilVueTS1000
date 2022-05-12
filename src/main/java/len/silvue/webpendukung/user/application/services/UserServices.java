package len.silvue.webpendukung.user.application.services;



import len.silvue.webpendukung.user.adapter.in.web.mapper.UserRunningMapper;
import len.silvue.webpendukung.user.adapter.in.web.model.UserDto;
import len.silvue.webpendukung.user.application.out.FindUserPort;
import len.silvue.webpendukung.domains.User;
import len.silvue.webpendukung.user.application.in.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserServices implements UserUseCase {
    private final FindUserPort findUserPort;
    private final UserRunningMapper userRunningMapper;

    @Override
    public List<UserDto> getAllUser() {
        System.out.println(findUserPort.loadAllUser());
        return userRunningMapper.toUserDtoList(findUserPort.loadAllUser());
    }

    @Override
    public UserDto getUserById(int id) {
        Optional<User> optionalUser = findUserPort.loadUserById(id);
        return userRunningMapper.toUserDto(optionalUser.orElse(null));
    }
}
