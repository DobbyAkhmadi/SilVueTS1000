package len.silvue.webpendukung.user.adapter.in.web.model;

import len.silvue.webpendukung.domains.Role;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private Integer id;
    private String username;
    private Role idRoles;
    private String password;

}
