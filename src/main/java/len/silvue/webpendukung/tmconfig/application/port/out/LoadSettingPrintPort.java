package len.silvue.webpendukung.tmconfig.application.port.out;

import len.silvue.webpendukung.domains.SettingPrint;

import java.util.Optional;

public interface LoadSettingPrintPort {
    Optional<SettingPrint> loadDataSettingPort() throws Exception;
}
