package len.silvue.webpendukung.gapeka.application.port.in;

import len.silvue.webpendukung.gapeka.adapter.out.web.ListRuteDetailDto;
import len.silvue.webpendukung.gapeka.adapter.in.web.ModifyListRuteDetailCommand;

public interface ModifyListRouteUseCase {
    ListRuteDetailDto modifyListRoute(ModifyListRuteDetailCommand modifyListRuteDetailCommand) throws Exception;
}
