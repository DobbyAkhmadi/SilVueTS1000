package len.silvue.webpendukung.tmconfig.application.port.in;

import len.silvue.webpendukung.tmconfig.adapter.out.web.SystemStatusDto;

public interface FindSystemStatusUseCase {
    SystemStatusDto getSystemStatus() throws Exception;
}
