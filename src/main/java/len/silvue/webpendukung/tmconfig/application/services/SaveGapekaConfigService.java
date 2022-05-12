package len.silvue.webpendukung.tmconfig.application.services;

import len.silvue.webpendukung.tmconfig.adapter.out.persistence.repositories.ConfigurationRepository;
import len.silvue.webpendukung.tmconfig.application.port.in.SaveGapekaConfigUseCase;
import len.silvue.webpendukung.domains.Configuration;
import len.silvue.webpendukung.tmconfig.adapter.in.web.GapekaConfigCommand;
import len.silvue.webpendukung.utility.SaveGapekaConfigFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveGapekaConfigService implements SaveGapekaConfigUseCase {
    private final ConfigurationRepository configurationRepository;

    private Configuration configuration;
    private List<Configuration> configurationList;
    private GapekaConfigCommand gapekaConfigCommand;

    @Override
    public void store(GapekaConfigCommand gapekaConfigCommand) throws SaveGapekaConfigFailedException {
        try {
            if(gapekaConfigCommand == null)
                throw new Exception("Gapeka config kosong");
            this.gapekaConfigCommand = gapekaConfigCommand;
            fetchConfigurations();
            setConfigurationFromGapeka();
            saveToDatabase();
        } catch(Exception e) {
            throw new SaveGapekaConfigFailedException("Gagal menyimpan konfigurasi gapeka", e);
        }
    }

    private void fetchConfigurations() throws Exception {
        configurationList = configurationRepository.findAll();
        if(configurationList != null && !configurationList.isEmpty()) {
            configuration = configurationList.get(0);
        } else {
            configuration = new Configuration();
        }
    }

    private void setConfigurationFromGapeka() throws Exception {
        configuration.setTdgBase(gapekaConfigCommand.getBase());
        configuration.setTdgBaseLive(gapekaConfigCommand.getBaseLive());
        configuration.setTdgLine(gapekaConfigCommand.getLine());
        configuration.setTdgLineLive(gapekaConfigCommand.getLineLive());
        configuration.setTdgRute(gapekaConfigCommand.getRute());
        configuration.setTdgRuteLive(gapekaConfigCommand.getRute());
        configuration.setTdgDate(gapekaConfigCommand.getDate());
        configuration.setTdgDateLive(gapekaConfigCommand.getDateLive());
        configuration.setTdgPlan(gapekaConfigCommand.getPlan());
        configuration.setTdgPlanLive(gapekaConfigCommand.getPlanLive());
        configuration.setTdgSch(gapekaConfigCommand.getSchedule());
        configuration.setTdgSchLive(gapekaConfigCommand.getScheduleLive());
        configuration.setTdgRangeALive(gapekaConfigCommand.getRangeALive());
        configuration.setTdgRangeBLive(gapekaConfigCommand.getRangeBLive());
    }

    private void saveToDatabase() throws Exception {
        configurationRepository.save(configuration);
    }
}
