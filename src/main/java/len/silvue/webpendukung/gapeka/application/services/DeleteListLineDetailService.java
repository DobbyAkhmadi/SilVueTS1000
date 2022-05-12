package len.silvue.webpendukung.gapeka.application.services;

import len.silvue.webpendukung.gapeka.adapter.out.web.ListLineDetailDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.ListRuteDetailDto;
import len.silvue.webpendukung.gapeka.application.port.in.DeleteListLineDetailUseCase;
import len.silvue.webpendukung.gapeka.application.port.in.DeleteListRuteDetailUseCase;
import len.silvue.webpendukung.gapeka.application.port.in.FindListLineDetailUseCase;
import len.silvue.webpendukung.gapeka.application.port.in.FindListRuteDetailUseCase;
import len.silvue.webpendukung.gapeka.application.port.out.DeleteListLineDetailPort;
import len.silvue.webpendukung.gapeka.application.port.out.DeleteListRuteDetailPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteListLineDetailService implements DeleteListLineDetailUseCase {
    private final DeleteListLineDetailPort deleteListLineDetailPort;
    private final FindListLineDetailUseCase findListLineDetailUseCase;

    @Override
    public ListLineDetailDto deleteListLineById(int idLineList) throws Exception {
        try {
            ListLineDetailDto listLineDetailDto  = findListLineDetailUseCase.getListLineDetailById(idLineList);
            deleteListLineDetailPort.eraseListLine(idLineList);
            return listLineDetailDto;
        } catch (Exception e) {
            throw new Exception("Gagal delete list Line by id ", e);
        }
    }

    @Override
    public void deleteAllListLineById(int idLineList) throws Exception {
        try {
            deleteListLineDetailPort.eraseAllListLine(idLineList);
        } catch (Exception e) {
            throw new Exception("Gagal delete list line by id ", e);
        }
    }
}
