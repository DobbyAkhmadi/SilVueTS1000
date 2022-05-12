package len.silvue.webpendukung.actual.application.port.in;


import len.silvue.webpendukung.actual.adapter.out.web.ActualRunningScheduleDto;


import java.util.List;

public interface AddActualScheduleUseCase {
    List<ActualRunningScheduleDto> setActualScheduleFromMasterPlan(int typeMasterPlan) throws Exception;
}
