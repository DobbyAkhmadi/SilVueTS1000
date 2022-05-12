package len.silvue.webpendukung.tmconfig.application.port.out;

import len.silvue.webpendukung.domains.SystemStatus;

import java.util.Optional;

public interface LoadSystemStatusPort {
    Optional<SystemStatus> loadSystemStatus() throws Exception;
}
