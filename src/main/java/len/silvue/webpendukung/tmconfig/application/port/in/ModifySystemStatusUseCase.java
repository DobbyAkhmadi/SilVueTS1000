package len.silvue.webpendukung.tmconfig.application.port.in;

import len.silvue.webpendukung.tmconfig.adapter.in.web.ModifySystemStatusCommand;
import len.silvue.webpendukung.tmconfig.adapter.out.web.SystemStatusDto;

public interface ModifySystemStatusUseCase {
    SystemStatusDto modifyUpdateArs(ModifySystemStatusCommand modifySystemStatusCommand) throws Exception;
}
