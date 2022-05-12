package len.silvue.webpendukung.schedule.application.port.out;

import len.silvue.webpendukung.domains.RuteRole;

import java.util.List;

public interface SaveRuteRolePort {
    List<RuteRole> saveAllRuteRole(List<RuteRole> ruteRoleList) throws Exception;
    RuteRole saveRuteRole(RuteRole ruteRole) throws Exception;
}
