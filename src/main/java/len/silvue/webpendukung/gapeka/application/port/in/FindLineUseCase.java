package len.silvue.webpendukung.gapeka.application.port.in;

import len.silvue.webpendukung.gapeka.adapter.out.web.LineDto;

import java.util.List;

public interface FindLineUseCase {
    List<LineDto> getAllLine() throws Exception;
}
