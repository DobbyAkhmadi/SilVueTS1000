package len.silvue.webpendukung.actual.application.port.in;

import len.silvue.webpendukung.actual.adapter.out.web.ActualModifyDto;

public interface DeleteActualScheduleUseCase {


    ActualModifyDto deleteActualScheduleById(int idActualPlan) throws Exception;
    void deleteAllActualSchedule() throws Exception;
    void deleteAllActualScheduleByTrainId(int trainId) throws Exception;
    void deleteAllActualScheduleSpesificDate(String actualCode) throws Exception;
}
