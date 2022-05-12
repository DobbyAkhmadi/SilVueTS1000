package len.silvue.webpendukung.ars.application.services;


import len.silvue.webpendukung.ars.adapter.out.web.ArsScheduleDto;
import len.silvue.webpendukung.ars.adapter.out.web.mapper.ArsScheduleMapper;
import len.silvue.webpendukung.ars.application.port.in.AddArsDetailUseCase;
import len.silvue.webpendukung.ars.application.port.in.StatusArsUseCase;
import len.silvue.webpendukung.ars.application.port.out.DeleteArsSchedulePort;
import len.silvue.webpendukung.ars.application.port.out.SaveArsDetailPort;
import len.silvue.webpendukung.domains.ArsSchedule;
import len.silvue.webpendukung.domains.SystemStatus;
import len.silvue.webpendukung.tmconfig.application.port.out.LoadSystemStatusPort;
import len.silvue.webpendukung.tmconfig.application.port.out.SaveSystemStatusPort;
import len.silvue.webpendukung.todays.application.port.out.LoadTodayDetailPort;
import len.silvue.webpendukung.domains.TodayRunningSchedule;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j

public class AddArsDetailService implements AddArsDetailUseCase {

    private final SaveArsDetailPort saveArsDetailPort;
    private final ArsScheduleMapper arsScheduleMapper;
    private final DeleteArsSchedulePort deleteArsSchedulePort;
    private final LoadTodayDetailPort loadTodayDetailPort;
    private final LoadSystemStatusPort loadSystemStatusPort;
    private final SaveSystemStatusPort saveSystemStatusPort;
    private final StatusArsUseCase statusArsUseCase;

    @Override
    public List<ArsScheduleDto> setArsScheduleFromActualPlan() throws Exception {
        List<ArsSchedule> actualList = new ArrayList<>();
        try {
            Optional<List<TodayRunningSchedule>> optionalTodaysPlans = loadTodayDetailPort.loadAllTodayRunningSchedule();
            List<TodayRunningSchedule> todaysPlanList = optionalTodaysPlans.orElseThrow(DataNotFoundException::new);

            todaysPlanList.forEach(todaysPlan -> {
                ArsSchedule arsSchedule = ArsSchedule.builder()
                        .typeMasterPlan(todaysPlan.getTypeMasterPlan())
                        .numberTrain(todaysPlan.getNumberTrain())
                        .train(todaysPlan.getTrain())
                        .peronFromArs(todaysPlan.getPeronFrom())
                        .peronToArs(todaysPlan.getPeronTo())
                        .flagArsSchedule(todaysPlan.getFlagMasterPlan())
                        .ruteRole(todaysPlan.getRuteRole())
                        .departArs(todaysPlan.getDepart())
                        .arrivalArs(todaysPlan.getArrival())
                        .actualCodeArs(new Date())
                        .build();
                actualList.add(arsSchedule);
            });
            if(!actualList.isEmpty()) {
                deleteArsSchedulePort.eraseAllArsSchedule();
                saveArsDetailPort.storeArsDetailScheduleList(actualList);
            }
            disableArs();
            saveLastStatusArsUpdate();
        } catch(Exception e) {
            log.error("Cannot update ars", e);
        }
        return arsScheduleMapper.toArsScheduleDtoList(actualList);
    }

    private void saveLastStatusArsUpdate() throws Exception {
        Optional<SystemStatus> optionalSystemStatus = loadSystemStatusPort.loadSystemStatus();
        SystemStatus systemStatus = optionalSystemStatus.orElseGet(SystemStatus::new);
        systemStatus.setLastUpdateArs(new Date());
        saveSystemStatusPort.storeDataSystemStatus(systemStatus);
    }

    private void disableArs() throws Exception {
        statusArsUseCase.disableArs();
    }

    @Override
    public void setLastUpdateArs() throws Exception {

    }

}
