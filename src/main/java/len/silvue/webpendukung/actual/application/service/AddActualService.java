package len.silvue.webpendukung.actual.application.service;


import len.silvue.webpendukung.actual.adapter.out.web.ActualRunningScheduleDto;
import len.silvue.webpendukung.actual.adapter.out.web.mapper.ActualRunningScheduleMapper;
import len.silvue.webpendukung.actual.application.port.in.AddActualScheduleUseCase;
import len.silvue.webpendukung.actual.application.port.out.SaveActualSchedulePort;
import len.silvue.webpendukung.schedule.application.port.out.LoadMasterPlanPort;
import len.silvue.webpendukung.schedule.application.port.out.LoadTypeMasterPlanPort;
import len.silvue.webpendukung.domains.MasterPlan;

import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j

public class AddActualService implements AddActualScheduleUseCase {

    private final LoadTypeMasterPlanPort loadTypeMasterPlanPort;
    private final SaveActualSchedulePort saveActualSchedulePort;
    private final ActualRunningScheduleMapper actualRunningScheduleMapper;
    private final LoadMasterPlanPort loadMasterPlanPort;

    @Override
    public List<ActualRunningScheduleDto> setActualScheduleFromMasterPlan(int typeMasterPlan) throws Exception {
        Optional<List<MasterPlan>> optionalMasterPlans = loadMasterPlanPort.loadAllMasterPlanByTypeMasterPlan(typeMasterPlan);
        List<MasterPlan> masterPlan = optionalMasterPlans.orElseThrow(DataNotFoundException::new);

        return null;
    }
}
