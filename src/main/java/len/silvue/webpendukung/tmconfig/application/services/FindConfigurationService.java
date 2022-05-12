package len.silvue.webpendukung.tmconfig.application.services;

import len.silvue.webpendukung.tmconfig.adapter.out.web.ConfigurationDto;
import len.silvue.webpendukung.tmconfig.adapter.out.web.mapper.ConfigurationMapper;
import len.silvue.webpendukung.tmconfig.application.port.in.FindConfigurationUseCase;
import len.silvue.webpendukung.tmconfig.application.port.out.LoadConfigurationPort;
import len.silvue.webpendukung.domains.Configuration;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FindConfigurationService implements FindConfigurationUseCase {
    private final LoadConfigurationPort loadConfigurationPort;
    private final ConfigurationMapper configurationMapper;

    @Override
    public Optional<ConfigurationDto> getConfiguration() throws Exception {
        try {
            Optional<Configuration> config = loadConfigurationPort.loadDataConfiguration();
            return Optional.ofNullable(configurationMapper.toConfigurationDto(config.orElse(null)));
        } catch(Exception e) {
            throw new Exception("Mengambil konfigurasi gagal", e);
        }
    }
}
