package len.silvue.webpendukung.gapeka.application.services;

import len.silvue.webpendukung.gapeka.adapter.out.web.ListRuteDetailDto;
import len.silvue.webpendukung.gapeka.application.port.in.DeleteListRuteDetailUseCase;
import len.silvue.webpendukung.gapeka.application.port.in.FindListRuteDetailUseCase;
import len.silvue.webpendukung.gapeka.application.port.out.DeleteListRuteDetailPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteListRuteDetailService implements DeleteListRuteDetailUseCase {
    private final DeleteListRuteDetailPort deleteListRuteDetailPort;
    private final FindListRuteDetailUseCase findListRuteDetailUseCase;
    @Override
    public ListRuteDetailDto deleteListRouteById(int idRouteList) throws Exception {
        try {
            ListRuteDetailDto listRuteDetailDto  = findListRuteDetailUseCase.getListRuteDetailById(idRouteList);
            deleteListRuteDetailPort.eraseListRoute(idRouteList);
            return listRuteDetailDto;
        } catch (Exception e) {
            throw new Exception("Gagal delete list route by id ", e);
        }
    }

    @Override
    public void deleteAllListRouteById(int idRouteList) throws Exception {
        try {
            deleteListRuteDetailPort.eraseAllListRoute(idRouteList);
        } catch (Exception e) {
            throw new Exception("Gagal delete list route by id ", e);
        }
    }
}
