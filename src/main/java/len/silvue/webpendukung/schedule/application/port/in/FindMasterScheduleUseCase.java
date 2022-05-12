package len.silvue.webpendukung.schedule.application.port.in;

import len.silvue.webpendukung.schedule.adapter.out.web.MasterPlanDto;
import len.silvue.webpendukung.schedule.adapter.out.web.MasterScheduleDto;
import len.silvue.webpendukung.schedule.adapter.out.web.ScheduleDto;
import len.silvue.webpendukung.schedule.adapter.out.web.ViewMasterScheduleDto;
import len.silvue.webpendukung.utility.DataNotFoundException;
import len.silvue.webpendukung.web.controllers.MasterPlanController;

import java.util.List;

public interface FindMasterScheduleUseCase {
    List<ScheduleDto> getAllMasterSchedule() throws Exception;
    List<ViewMasterScheduleDto> getViewMasterSchedule() throws Exception;
    ScheduleDto getScheduleById(int id) throws DataNotFoundException;
    List<MasterPlanDto> getAllMasterPlan() throws Exception;
    List<MasterScheduleDto> getAllMasterScheduleByTypeMasterPlan(int idTypeMasterPlan) throws Exception;
}
