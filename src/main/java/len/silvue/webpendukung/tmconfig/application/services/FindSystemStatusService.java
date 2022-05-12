package len.silvue.webpendukung.tmconfig.application.services;

import len.silvue.webpendukung.tmconfig.adapter.out.web.SystemStatusDto;
import len.silvue.webpendukung.tmconfig.adapter.out.web.mapper.SystemStatusMapper;
import len.silvue.webpendukung.tmconfig.application.port.in.FindSystemStatusUseCase;
import len.silvue.webpendukung.tmconfig.application.port.out.LoadSystemStatusPort;
import len.silvue.webpendukung.domains.SystemStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindSystemStatusService implements FindSystemStatusUseCase {
    private final LoadSystemStatusPort loadSystemStatusPort;

    @Override
    public SystemStatusDto getSystemStatus() throws Exception {
        try {
            Optional<SystemStatus> optionalSystemStatus = loadSystemStatusPort.loadSystemStatus();
            return SystemStatusMapper.MAPPER.toSystemStatusDto(optionalSystemStatus.orElse(new SystemStatus()));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil system status", e);
        }
    }
}
