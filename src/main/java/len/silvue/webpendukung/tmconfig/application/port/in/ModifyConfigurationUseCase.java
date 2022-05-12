package len.silvue.webpendukung.tmconfig.application.port.in;

import len.silvue.webpendukung.tmconfig.adapter.in.web.ModifyConfigurationCommand;
import len.silvue.webpendukung.tmconfig.adapter.out.web.ConfigurationDto;

public interface ModifyConfigurationUseCase {
    ConfigurationDto modifyUpdateActual(ModifyConfigurationCommand modifyConfigurationCommand) throws Exception;
}
