package len.silvue.webpendukung.actual.application.port.in;

import len.silvue.webpendukung.actual.adapter.out.web.ActualModifyDto;
import len.silvue.webpendukung.actual.adapter.out.web.ActualRunningScheduleDto;

import len.silvue.webpendukung.utility.DataNotFoundException;

import java.util.Date;
import java.util.List;

public interface ActualRunningScheduleUseCase {
    List<ActualRunningScheduleDto> getAllActualRunningScheduleByIndex(int index) throws Exception;
    List<ActualRunningScheduleDto> getAllActualRunningByActualCode(String actualCodeFrom, String actualCodeTo) throws Exception;
    List<ActualRunningScheduleDto> getActualScheduleByTrainId(String trainId) throws Exception;
    ActualModifyDto getActualRunningScheduleById(int idActualPlan) throws DataNotFoundException;
    List<ActualRunningScheduleDto> getActualPlanByActualCode(String selectActualCode) throws Exception;
    List<Integer> getindexActual() throws Exception;
    Date getMaximumTimeData() throws DataNotFoundException;
    List<ActualRunningScheduleDto> getAllActualByMaxIndex() throws Exception;
}
