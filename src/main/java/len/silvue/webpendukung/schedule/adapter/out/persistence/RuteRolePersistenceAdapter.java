package len.silvue.webpendukung.schedule.adapter.out.persistence;

import len.silvue.webpendukung.schedule.adapter.out.persistence.repositories.RuteRoleRepository;
import len.silvue.webpendukung.schedule.application.port.out.DeleteRuteRolePort;
import len.silvue.webpendukung.schedule.application.port.out.LoadRuteRolePort;
import len.silvue.webpendukung.schedule.application.port.out.SaveRuteRolePort;
import len.silvue.webpendukung.domains.RuteRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RuteRolePersistenceAdapter implements LoadRuteRolePort, DeleteRuteRolePort, SaveRuteRolePort {
    private final RuteRoleRepository ruteRoleRepository;

    @Override
    public Optional<List<RuteRole>> loadAllRuteRole() throws Exception {
        try {
            return Optional.of(ruteRoleRepository.findAll());
        } catch(Exception e) {
            throw new Exception("Gagal mengambil rute role", e);
        }
    }

    @Override
    public Optional<RuteRole> loadRuteRoleById(int idRuteRole) throws Exception {
        try {
            return ruteRoleRepository.findById(idRuteRole);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil rute role by id", e);
        }
    }

    @Override
    public void eraseRuteRoleById(int idRuteRole) throws Exception {
        try {
            ruteRoleRepository.deleteById(idRuteRole);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil rute role by id", e);
        }
    }

    @Override
    public void eraseAllRuteRole() throws Exception {
        try {
            ruteRoleRepository.deleteAll();
        } catch(Exception e) {
            throw new Exception("Gagal menghapus semua rute role", e);
        }
    }

    @Override
    public List<RuteRole> saveAllRuteRole(List<RuteRole> ruteRoleList) throws Exception {
        try {
            return ruteRoleRepository.saveAll(ruteRoleList);
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan semua rute role", e);
        }
    }

    @Override
    public RuteRole saveRuteRole(RuteRole ruteRole) throws Exception {
        try {
            return ruteRoleRepository.save(ruteRole);
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan rute role", e);
        }
    }
}
