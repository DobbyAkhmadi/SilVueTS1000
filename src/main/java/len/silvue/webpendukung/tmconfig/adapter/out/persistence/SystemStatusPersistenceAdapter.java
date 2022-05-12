package len.silvue.webpendukung.tmconfig.adapter.out.persistence;

import len.silvue.webpendukung.tmconfig.adapter.out.persistence.repositories.SystemStatusRepository;
import len.silvue.webpendukung.tmconfig.application.port.out.LoadSystemStatusPort;
import len.silvue.webpendukung.tmconfig.application.port.out.SaveSystemStatusPort;
import len.silvue.webpendukung.domains.SystemStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class SystemStatusPersistenceAdapter implements LoadSystemStatusPort, SaveSystemStatusPort {
    private final SystemStatusRepository systemStatusRepository;

    @Override
    public Optional<SystemStatus> loadSystemStatus() throws Exception {
        try {
            List<SystemStatus> systemStatusList = systemStatusRepository.findAll();
            if(systemStatusList.isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.of(systemStatusList.get(0));
            }
        } catch(Exception e) {
            throw new Exception("Gagal mengambil data system status", e);
        }
    }

    @Override
    public SystemStatus storeDataSystemStatus(SystemStatus systemStatus) throws Exception {
        try {
            return systemStatusRepository.save(systemStatus);
        } catch(Exception e) {
            throw new Exception("Gagal save data update ars", e);
        }
    }
}
