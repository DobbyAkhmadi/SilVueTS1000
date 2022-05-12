package len.silvue.webpendukung.ars.application.port.in;

import len.silvue.webpendukung.ars.adapter.in.web.ModifyArsCommand;
import len.silvue.webpendukung.ars.adapter.out.web.ArsScheduleDto;

import java.util.List;

public interface ModifyArsUseCase {
    ArsScheduleDto modifyArsConflict(ModifyArsCommand modifyArsCommand) throws Exception;
    List<ArsScheduleDto> modifyArsConflictList(ModifyArsCommand modifyArsCommand) throws Exception;
    void feedbackToTDS(int idTrain, int idRouteStick) throws Exception;
}
