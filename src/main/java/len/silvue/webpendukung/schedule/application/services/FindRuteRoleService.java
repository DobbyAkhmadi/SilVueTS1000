package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.schedule.adapter.out.web.RuteRoleDto;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.RuteRoleMapper;
import len.silvue.webpendukung.schedule.application.port.in.FindRuteRoleUseCase;
import len.silvue.webpendukung.schedule.application.port.out.LoadRuteRolePort;
import len.silvue.webpendukung.domains.RuteRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindRuteRoleService implements FindRuteRoleUseCase {
    private final LoadRuteRolePort loadRuteRolePort;

    @Override
    public List<RuteRoleDto> getAllRuteRole() throws Exception {
        try {
            Optional<List<RuteRole>> ruteRoleDtoList = loadRuteRolePort.loadAllRuteRole();
            return RuteRoleMapper.MAPPER.toRuteRoleDtoList(ruteRoleDtoList.orElse(new ArrayList<>()));
        } catch(Exception e) {
            throw new Exception("Gagal mengeksekusi service find rute role", e);
        }
    }
}
