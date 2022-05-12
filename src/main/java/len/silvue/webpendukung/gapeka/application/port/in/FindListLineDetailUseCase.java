package len.silvue.webpendukung.gapeka.application.port.in;

import len.silvue.webpendukung.gapeka.adapter.out.web.LineDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.ListLineDetailDto;

import java.util.List;

public interface FindListLineDetailUseCase {
    List<ListLineDetailDto> getAllListLineDetail() throws Exception;
    List<ListLineDetailDto> getAllListLineDetailByLineId(int idLine) throws Exception;
    ListLineDetailDto getListLineDetailById(int idLine) throws Exception;
    List<LineDto> getAllListLineDetailByDistinctLine() throws Exception;
}
