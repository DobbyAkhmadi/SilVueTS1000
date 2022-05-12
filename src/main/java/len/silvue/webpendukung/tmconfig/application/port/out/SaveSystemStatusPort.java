package len.silvue.webpendukung.tmconfig.application.port.out;

import len.silvue.webpendukung.domains.SystemStatus;

public interface SaveSystemStatusPort {
    SystemStatus storeDataSystemStatus(SystemStatus systemStatus) throws Exception;
}
