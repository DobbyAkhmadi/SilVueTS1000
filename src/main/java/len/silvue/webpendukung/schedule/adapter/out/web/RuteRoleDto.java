package len.silvue.webpendukung.schedule.adapter.out.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RuteRoleDto {
    private int idRuteRole;
    private String nameRoute;
}
