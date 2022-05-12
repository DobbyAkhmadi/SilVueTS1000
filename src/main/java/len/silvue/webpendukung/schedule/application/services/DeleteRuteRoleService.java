package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.schedule.application.port.in.DeleteRuteRoleUseCase;
import len.silvue.webpendukung.schedule.application.port.out.DeleteRuteRolePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteRuteRoleService implements DeleteRuteRoleUseCase {
    private final DeleteRuteRolePort deleteRuteRolePort;
    @Override
    public void deleteRuteRoleById(int idRuteRole) throws Exception {
        try {
            deleteRuteRolePort.eraseRuteRoleById(idRuteRole);
        } catch (Exception e) {
            throw new Exception("Gagal delete schedule by id", e);
        }
    }
}
