package len.silvue.webpendukung.tmconfig.application.services;

import len.silvue.webpendukung.tmconfig.adapter.in.web.ModifyConfigurationCommand;
import len.silvue.webpendukung.tmconfig.adapter.out.web.ConfigurationDto;
import len.silvue.webpendukung.tmconfig.adapter.out.web.mapper.ConfigurationMapper;
import len.silvue.webpendukung.tmconfig.application.port.in.ModifyConfigurationUseCase;
import len.silvue.webpendukung.tmconfig.application.port.out.LoadConfigurationPort;
import len.silvue.webpendukung.tmconfig.application.port.out.SaveConfigurationPort;
import len.silvue.webpendukung.domains.Configuration;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ModifyConfigurationService implements ModifyConfigurationUseCase {
    private final LoadConfigurationPort loadConfigurationPort;
    private final SaveConfigurationPort saveConfigurationPort;
    private final ConfigurationMapper configurationMapper;

    @Override
    public ConfigurationDto modifyUpdateActual(ModifyConfigurationCommand modifyConfigurationCommand) throws Exception {
        try {
            Optional<Configuration> optionalConfiguration = loadConfigurationPort.loadDataConfiguration();
            Configuration configuration = optionalConfiguration.orElseThrow(DataNotFoundException::new);
            configuration.setAutoUpdateActual(modifyConfigurationCommand.getAutoUpdateActual());
            configuration.setAutoUpdateEnable(modifyConfigurationCommand.getAutoUpdateEnableTemp());
            saveConfigurationPort.storeDataConfiguration(configuration);
            return configurationMapper.toConfigurationDto(configuration);
        } catch (Exception e) {
            throw new Exception("Gagal mengubah configuration", e);
        }
    }
}
