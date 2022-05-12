package len.silvue.webpendukung.ars.application.services;

import len.silvue.webpendukung.ars.adapter.out.web.ArsScheduleDto;
import len.silvue.webpendukung.ars.adapter.out.web.mapper.ArsScheduleMapper;
import len.silvue.webpendukung.ars.application.port.in.ArsScheduleUseCase;
import len.silvue.webpendukung.ars.application.port.out.FindArsSchedulePort;
import len.silvue.webpendukung.domains.ArsSchedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArsScheduleService implements ArsScheduleUseCase {
    private final FindArsSchedulePort findArsSchedulePort;
    private final ArsScheduleMapper mapper;


    @Override
    public List<ArsScheduleDto> getAllArsSchedule() throws Exception {
        try {
            List<ArsSchedule> arsSchedules = findArsSchedulePort.loadAllArsSchedule();
            List<ArsSchedule> scheduleToView = new ArrayList<>();
            String train = "";
            for (ArsSchedule arsSchedule : arsSchedules) {
                if(train.isBlank() || train.compareToIgnoreCase(arsSchedule.getTrain().getNoka()) != 0) {
                    scheduleToView.add(arsSchedule);
                    train = arsSchedule.getTrain().getNoka();
                }
            }
            return mapper.toArsScheduleDtoList(scheduleToView);
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception("Gagal mengambil data ars schedule", e);
        }
    }

//    private void getAllRunningSchedule() {
//        List<TodayRunningSchedule> todayRunningSchedules = findTodayRunningSchedulePort.loadAllTodayRunningSchedule();
//
//        List<ArsSchedule> arsSchedules = new ArrayList<>();
//        for(TodayRunningSchedule todayRunningSchedule : todayRunningSchedules) {
//            ArsSchedule ars = ArsSchedule.builder()
//                    .typeMasterPlan()
//            .numberTrain()
//            .peronFromArs()
//            .peronToArs()
//            .train()
//            .ruteRole()
//            .departArs()
//            .arrivalArs()
//            .scheduleStatusArs()
//            .routeSettingStatus()
//            .flagArsSchedule;
//            arsSchedules.add(ars);
//        }
//        return ArsScheduleRepository.saveAll(arsSchedules);
//    }



}
