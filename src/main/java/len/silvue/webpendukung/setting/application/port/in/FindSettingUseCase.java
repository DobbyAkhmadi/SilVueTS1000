package len.silvue.webpendukung.setting.application.port.in;

import len.silvue.webpendukung.gapeka.adapter.out.web.ListLineDetailDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.ListRuteDetailDto;

import java.util.List;

public interface FindSettingUseCase {
    List<ListRuteDetailDto> getAllRouteSettingDetail() throws Exception;
    List<ListLineDetailDto> getAllLineSettingDetail() throws Exception;
}
