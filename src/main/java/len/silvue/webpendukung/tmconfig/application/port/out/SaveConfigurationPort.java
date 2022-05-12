package len.silvue.webpendukung.tmconfig.application.port.out;

import len.silvue.webpendukung.domains.Configuration;

public interface SaveConfigurationPort {
    Configuration storeDataConfiguration(Configuration configuration) throws Exception;
}
