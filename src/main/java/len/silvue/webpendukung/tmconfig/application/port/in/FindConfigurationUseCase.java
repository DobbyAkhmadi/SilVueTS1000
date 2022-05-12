package len.silvue.webpendukung.tmconfig.application.port.in;

import len.silvue.webpendukung.tmconfig.adapter.out.web.ConfigurationDto;

import java.util.Optional;

public interface FindConfigurationUseCase {
    Optional<ConfigurationDto> getConfiguration() throws Exception;
}
