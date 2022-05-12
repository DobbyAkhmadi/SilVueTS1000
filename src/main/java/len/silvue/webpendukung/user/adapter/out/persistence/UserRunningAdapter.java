package len.silvue.webpendukung.user.adapter.out.persistence;




import len.silvue.webpendukung.user.adapter.out.persistence.repositories.UserRepository;
import len.silvue.webpendukung.user.application.out.FindUserPort;
import len.silvue.webpendukung.domains.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRunningAdapter implements FindUserPort {
    private final UserRepository userRepository;

    @Override
    public List<User> loadAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> loadUserById(int id) {
        return userRepository.findById(id);
    }
}
