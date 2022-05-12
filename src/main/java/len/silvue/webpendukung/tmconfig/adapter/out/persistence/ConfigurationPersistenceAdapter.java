package len.silvue.webpendukung.tmconfig.adapter.out.persistence;

import len.silvue.webpendukung.tmconfig.adapter.out.persistence.repositories.ConfigurationRepository;
import len.silvue.webpendukung.tmconfig.application.port.out.LoadConfigurationPort;
import len.silvue.webpendukung.tmconfig.application.port.out.SaveConfigurationPort;
import len.silvue.webpendukung.domains.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ConfigurationPersistenceAdapter implements LoadConfigurationPort, SaveConfigurationPort {

    private ConfigurationRepository configurationRepository;

    @Autowired
    public ConfigurationPersistenceAdapter(ConfigurationRepository configRepo) {
        this.configurationRepository = configRepo;
    }

    @Override
    public Optional<Configuration> loadDataConfiguration() throws Exception {
        try {
            List<Configuration> configurationList = configurationRepository.findAll();
            if (configurationList.isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.of(configurationList.get(0));
            }
        } catch(Exception e) {
            throw new Exception("Mengambil data konfigurasi tidak berhasil", e);
        }
    }

    @Override
    public Configuration storeDataConfiguration(Configuration configuration) throws Exception {
        try {
            return configurationRepository.save(configuration);
        } catch(Exception e) {
            throw new Exception("Gagal save data update actual", e);
        }
    }
}
