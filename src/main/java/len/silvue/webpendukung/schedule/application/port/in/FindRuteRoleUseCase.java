package len.silvue.webpendukung.schedule.application.port.in;

import len.silvue.webpendukung.schedule.adapter.out.web.RuteRoleDto;

import java.util.List;

public interface FindRuteRoleUseCase {
    List<RuteRoleDto> getAllRuteRole() throws Exception;
}
