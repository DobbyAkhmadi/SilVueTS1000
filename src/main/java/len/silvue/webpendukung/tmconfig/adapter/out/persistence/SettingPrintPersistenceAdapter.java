package len.silvue.webpendukung.tmconfig.adapter.out.persistence;

import len.silvue.webpendukung.tmconfig.adapter.out.persistence.repositories.SettingPrintRepository;
import len.silvue.webpendukung.tmconfig.application.port.out.LoadSettingPrintPort;
import len.silvue.webpendukung.tmconfig.application.port.out.SaveSettingPrintPort;
import len.silvue.webpendukung.domains.SettingPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SettingPrintPersistenceAdapter implements SaveSettingPrintPort, LoadSettingPrintPort {
    private SettingPrintRepository settingPrintRepository;

    @Autowired
    public SettingPrintPersistenceAdapter(SettingPrintRepository settingPrintRepository) {
        this.settingPrintRepository = settingPrintRepository;
    }

    @Override
    public SettingPrint storeDataPrint(SettingPrint settingPrint) throws Exception {
        try {
            return settingPrintRepository.save(settingPrint);
        } catch(Exception e) {
            throw new Exception("failed update setting print", e);
        }
    }

    @Override
    public Optional<SettingPrint> loadDataSettingPort() throws Exception {
        try {
            List<SettingPrint> settingPrintList = settingPrintRepository.findAll();
            if (settingPrintList.isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.of(settingPrintList.get(0));
            }
        } catch(Exception e) {
            throw new Exception("failed to get load setting port", e);
        }
    }
}
