package len.silvue.webpendukung.todays.application.services;

import len.silvue.webpendukung.todays.application.port.in.DeleteTodayDetailUseCase;
import len.silvue.webpendukung.todays.application.port.in.TodayRunningScheduleUseCase;
import len.silvue.webpendukung.todays.adapter.out.web.TodayRunningScheduleDto;
import len.silvue.webpendukung.todays.application.port.out.DeleteTodayPort;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DeleteTodayDetailService implements DeleteTodayDetailUseCase {
   private final DeleteTodayPort deleteTodayDetailPort;
    private final TodayRunningScheduleUseCase findTodayDetailUseCase;

    @Override
    public TodayRunningScheduleDto deleteTodayRunningScheduleById(int idTodayRunningSchedule) throws Exception {
        try {
            TodayRunningScheduleDto todayRunningScheduleDto  = findTodayDetailUseCase.getTodayRunningScheduleById(idTodayRunningSchedule);
            deleteTodayDetailPort.eraseTodayDetailByTodayRunningSchedule(idTodayRunningSchedule);
            return todayRunningScheduleDto;
        } catch (Exception e) {
            throw new Exception("Gagal delete schedule by id", e);
        }
    }

    @Override
    public void deleteAllTodayDetail() throws Exception {
        try {
            deleteTodayDetailPort.eraseAllTodayDetail();
        } catch (Exception e) {
            throw new Exception("Gagal delete all schedule ", e);
        }
    }

    @Override
    public void deleteAllTodayDetailByTrainId(int trainId) throws Exception {
        try {
            deleteTodayDetailPort.eraseAllTodayDetailByTrainId(trainId);
        } catch (Exception e) {
            throw new Exception("Gagal delete schedule by id", e);
        }
    }
}
