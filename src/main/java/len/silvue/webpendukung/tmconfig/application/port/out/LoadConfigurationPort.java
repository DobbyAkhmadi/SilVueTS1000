package len.silvue.webpendukung.tmconfig.application.port.out;

import len.silvue.webpendukung.domains.Configuration;

import java.util.Optional;

public interface LoadConfigurationPort {
    Optional<Configuration> loadDataConfiguration() throws Exception;
}
