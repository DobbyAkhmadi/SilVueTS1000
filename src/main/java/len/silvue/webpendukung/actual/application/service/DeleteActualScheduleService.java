package len.silvue.webpendukung.actual.application.service;

import len.silvue.webpendukung.actual.adapter.out.web.ActualModifyDto;
import len.silvue.webpendukung.actual.application.port.in.ActualRunningScheduleUseCase;
import len.silvue.webpendukung.actual.application.port.in.DeleteActualScheduleUseCase;
import len.silvue.webpendukung.actual.application.port.out.DeleteActualPort;

import len.silvue.webpendukung.utility.DataFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteActualScheduleService implements DeleteActualScheduleUseCase {
    private final DeleteActualPort deleteActualSchedulePort;
    private final ActualRunningScheduleUseCase findActualScheduleUseCase;


    @Override
    public ActualModifyDto deleteActualScheduleById(int idActualPlan) throws Exception {
        try {
            ActualModifyDto actualRunningScheduleDto  = findActualScheduleUseCase.getActualRunningScheduleById(idActualPlan);
            deleteActualSchedulePort.eraseActualScheduleByActualSchedule(idActualPlan);
            return actualRunningScheduleDto;
        } catch (Exception e) {
            throw new Exception("Gagal delete actual by id", e);
        }
    }

    @Override
    public void deleteAllActualSchedule() throws Exception {
        try {
            deleteActualSchedulePort.eraseAllActualSchedule();
        } catch (Exception e) {
            throw new Exception("Gagal delete all actual ", e);
        }
    }

    @Override
    public void deleteAllActualScheduleByTrainId(int trainId) throws Exception {

    }

    @Override
    public void deleteAllActualScheduleSpesificDate(String actualCode) throws Exception {
        try {
            SimpleDateFormat spesific = new SimpleDateFormat("yyyy-MM-dd");
            Date cobadate = spesific.parse(actualCode);
            deleteActualSchedulePort.eraseActualScheduleByActualSpesificDate(spesific.parse(actualCode));

        } catch (Exception e) {
            throw new Exception("Gagal delete all spesific date ", e);
        }
        //return null;
    }
}
