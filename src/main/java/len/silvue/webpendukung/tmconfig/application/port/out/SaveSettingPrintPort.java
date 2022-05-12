package len.silvue.webpendukung.tmconfig.application.port.out;

import len.silvue.webpendukung.domains.SettingPrint;

public interface SaveSettingPrintPort {
    SettingPrint storeDataPrint(SettingPrint settingPrint) throws Exception;
}
