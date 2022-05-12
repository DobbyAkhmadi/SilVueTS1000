package len.silvue.webpendukung.todays.application.services;


import len.silvue.webpendukung.schedule.application.port.out.LoadMasterPlanPort;
import len.silvue.webpendukung.schedule.application.port.out.LoadTypeMasterPlanPort;
import len.silvue.webpendukung.domains.MasterPlan;
import len.silvue.webpendukung.todays.adapter.out.web.TodayRunningScheduleDto;
import len.silvue.webpendukung.todays.adapter.out.web.mapper.TodayRunningScheduleMapper;
import len.silvue.webpendukung.todays.application.port.in.AddTodayDetailUseCase;
import len.silvue.webpendukung.todays.application.port.out.DeleteTodayPort;
import len.silvue.webpendukung.todays.application.port.out.SaveTodayDetailPort;
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

public class AddTodayDetailService implements AddTodayDetailUseCase {

    private final LoadTypeMasterPlanPort loadTypeMasterPlanPort;
    private final SaveTodayDetailPort saveTodayDetailPort;
    private final TodayRunningScheduleMapper todayRunningScheduleMapper;
    private final DeleteTodayPort deleteTodayPort;
    private final LoadMasterPlanPort loadMasterPlanPort;

    @Override
    public List<TodayRunningScheduleDto> setTodayRunningScheduleFromMasterPlan(String commaSeperatedTypeMasterPlanID) throws Exception {
        try {
            String[] arrayStringTypeMasterPlanId = commaSeperatedTypeMasterPlanID.split(",");
            List<TodayRunningSchedule> todayList = new ArrayList<>();

            for(String strTypeMasterPlanId : arrayStringTypeMasterPlanId) {
                Optional<List<MasterPlan>> optionalMasterPlans = loadMasterPlanPort.loadAllMasterPlanByTypeMasterPlan(Integer.parseInt(strTypeMasterPlanId.trim()));
                List<MasterPlan> masterPlanList = optionalMasterPlans.orElseThrow(DataNotFoundException::new);
                masterPlanList.forEach(masterPlan -> {
                    TodayRunningSchedule todayRunningSchedule = TodayRunningSchedule.builder()
                            .typeMasterPlan(masterPlan.getTypeMasterPlan())
                            .numberTrain(masterPlan.getNumberTrain())
                            .train(masterPlan.getTrain())
                            .peronFrom(masterPlan.getPeronFrom())
                            .peronTo(masterPlan.getPeronTo())
                            .flagMasterPlan(masterPlan.getFlagMasterPlan())
                            .ruteRole(masterPlan.getRuteRole())
                            .flagCheckConflict(masterPlan.getFlagCheckConflict())
                            .depart(masterPlan.getDepart())
                            .arrival(masterPlan.getArrival())
                            .dwellingTime(masterPlan.getDwellingTime())
                            .actualCode(new Date())
                            .build();
                    todayList.add(todayRunningSchedule);
                });
            }
            if(!todayList.isEmpty()) {
                deleteTodayPort.eraseAllTodayDetail();
                saveTodayDetailPort.storeTodayDetailScheduleList(todayList);
            }
            return todayRunningScheduleMapper.toTodayRunningScheduleDtoList(todayList);
        } catch(Exception e) {
            throw new Exception("Gagal import data masterplan", e);
        }
    }
}
