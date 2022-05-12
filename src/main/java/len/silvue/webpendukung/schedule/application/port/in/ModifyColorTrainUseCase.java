package len.silvue.webpendukung.schedule.application.port.in;

import len.silvue.webpendukung.schedule.adapter.in.web.ModifyColorTrainCommand;
import len.silvue.webpendukung.schedule.adapter.out.web.ColorTrainDto;

public interface ModifyColorTrainUseCase {
    ColorTrainDto modifyColorTrain(ModifyColorTrainCommand modifyColorTrainCommand) throws Exception;
}
