package len.silvue.webpendukung.tmconfig.application.port.in;

import len.silvue.webpendukung.tmconfig.adapter.out.web.SettingPrintDto;

public interface FindSettingPrintUseCase {
    SettingPrintDto getSettingPrint() throws Exception;
}
