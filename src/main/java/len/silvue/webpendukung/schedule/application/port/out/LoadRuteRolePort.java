package len.silvue.webpendukung.schedule.application.port.out;

import len.silvue.webpendukung.domains.RuteRole;

import java.util.List;
import java.util.Optional;

public interface LoadRuteRolePort {
    Optional<List<RuteRole>> loadAllRuteRole() throws Exception;
    Optional<RuteRole> loadRuteRoleById(int idRuteRole) throws Exception;
}
