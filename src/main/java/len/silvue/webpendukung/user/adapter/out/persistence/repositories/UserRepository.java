package len.silvue.webpendukung.user.adapter.out.persistence.repositories;

import len.silvue.webpendukung.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    @Query(value = "SELECT user.username, role.name FROM user \n" +
//            "LEFT JOIN user_role ON user.id = user_role.users_id \n" +
//            "LEFT JOIN role ON role.id = user_role.roles_id ", nativeQuery = true)
//    List<User> findAlluser();


Optional<User> findByUsername(String username);

}
