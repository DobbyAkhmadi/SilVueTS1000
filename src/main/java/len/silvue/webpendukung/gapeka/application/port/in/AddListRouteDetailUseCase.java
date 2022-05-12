package len.silvue.webpendukung.gapeka.application.port.in;

import len.silvue.webpendukung.gapeka.adapter.in.web.AddListRuteDetailCommand;
import len.silvue.webpendukung.gapeka.adapter.out.web.ListRuteDetailDto;

public interface AddListRouteDetailUseCase {
    ListRuteDetailDto saveListRouteDetail(AddListRuteDetailCommand addListRuteDetailCommand) throws Exception;
}
