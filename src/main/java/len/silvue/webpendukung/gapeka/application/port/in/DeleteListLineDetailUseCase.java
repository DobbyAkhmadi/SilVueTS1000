package len.silvue.webpendukung.gapeka.application.port.in;

import len.silvue.webpendukung.gapeka.adapter.out.web.ListLineDetailDto;

public interface DeleteListLineDetailUseCase {
    ListLineDetailDto deleteListLineById(int idLineList) throws Exception;
    void deleteAllListLineById (int idLineList) throws Exception;
}
