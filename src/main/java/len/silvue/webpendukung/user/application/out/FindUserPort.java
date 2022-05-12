package len.silvue.webpendukung.user.application.out;


import len.silvue.webpendukung.domains.User;

import java.util.List;
import java.util.Optional;

public interface FindUserPort {
    List<User> loadAllUser();
    Optional <User> loadUserById(int id);
}
