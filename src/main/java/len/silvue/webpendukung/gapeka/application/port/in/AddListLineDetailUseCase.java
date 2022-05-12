package len.silvue.webpendukung.gapeka.application.port.in;

import len.silvue.webpendukung.gapeka.adapter.in.web.AddListLineDetailCommand;
import len.silvue.webpendukung.gapeka.adapter.out.web.ListLineDetailDto;

public interface AddListLineDetailUseCase {
    ListLineDetailDto saveListLineDetail(AddListLineDetailCommand addListLineDetailCommand) throws Exception;
}
