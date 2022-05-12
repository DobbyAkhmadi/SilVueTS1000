package len.silvue.webpendukung.tmconfig.application.services;

import len.silvue.webpendukung.tmconfig.adapter.in.web.ModifySystemStatusCommand;
import len.silvue.webpendukung.tmconfig.adapter.out.web.SystemStatusDto;
import len.silvue.webpendukung.tmconfig.adapter.out.web.mapper.SystemStatusMapper;
import len.silvue.webpendukung.tmconfig.application.port.in.ModifySystemStatusUseCase;
import len.silvue.webpendukung.tmconfig.application.port.out.LoadSystemStatusPort;
import len.silvue.webpendukung.tmconfig.application.port.out.SaveSystemStatusPort;
import len.silvue.webpendukung.domains.SystemStatus;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ModifySystemStatusService implements ModifySystemStatusUseCase {
    private final LoadSystemStatusPort loadSystemStatusPort;
    private final SaveSystemStatusPort saveSystemStatusPort;
    private final SystemStatusMapper systemStatusMapper;

    @Override
    public SystemStatusDto modifyUpdateArs(ModifySystemStatusCommand modifySystemStatusCommand) throws Exception {
        try {
            Optional<SystemStatus> optionalSystemStatus = loadSystemStatusPort.loadSystemStatus();
            SystemStatus systemStatus = optionalSystemStatus.orElseThrow(DataNotFoundException::new);
            systemStatus.setLastUpdateArs(modifySystemStatusCommand.getLastUpdateArs());
            saveSystemStatusPort.storeDataSystemStatus(systemStatus);
            return systemStatusMapper.toSystemStatusDto(systemStatus);
        } catch (Exception e) {
            throw new Exception("Gagal mengubah configuration", e);
        }
    }
}
