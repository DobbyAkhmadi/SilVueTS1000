package len.silvue.webpendukung.gapeka.application.port.in;

import len.silvue.webpendukung.gapeka.adapter.in.web.ModifyListLineDetailCommand;
import len.silvue.webpendukung.gapeka.adapter.out.web.ListLineDetailDto;

public interface ModifyListLineUseCase {
    ListLineDetailDto modifyListLine(ModifyListLineDetailCommand modifyListLineDetailCommand) throws Exception;
}
