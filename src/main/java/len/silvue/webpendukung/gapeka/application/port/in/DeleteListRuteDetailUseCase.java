package len.silvue.webpendukung.gapeka.application.port.in;

import len.silvue.webpendukung.gapeka.adapter.out.web.ListRuteDetailDto;

public interface DeleteListRuteDetailUseCase {
    ListRuteDetailDto deleteListRouteById(int idRouteList) throws Exception;
    void deleteAllListRouteById (int idRouteList) throws Exception;
}
