package len.silvue.webpendukung.actual.application.service;

import len.silvue.webpendukung.actual.adapter.out.web.ParamActualPlanDto;
import len.silvue.webpendukung.actual.application.port.in.FindParamActualPlanUseCase;
import len.silvue.webpendukung.actual.application.port.out.FindActualRunningSchedulePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindParamActualPlanService implements FindParamActualPlanUseCase {
    private final FindActualRunningSchedulePort findActualRunningSchedulePort;
    private Date actualCodeDate;
    private String actualCodeStr;
    private List<String> typePlans;
    private List<String> ruteRoles;
    private ParamActualPlanDto paramActualPlanDto;

    @Override
    public ParamActualPlanDto getParamByActualCode(String actualCode) throws Exception {
        try {
            actualCodeStr = actualCode;
            convertActualCodeStringToDate();
            fetchDataParamFromDb();
            makeParam();
            return paramActualPlanDto;
        } catch(Exception e) {
            throw new Exception("Gagal mengambil parameter actualplan", e);
        }
    }

    private void convertActualCodeStringToDate() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        actualCodeDate = simpleDateFormat.parse(actualCodeStr);
    }

    private void fetchDataParamFromDb() throws Exception {
        Optional<List<String>> optionalTypePlans = findActualRunningSchedulePort.loadTypePlanFromActualPlanByActualCode(actualCodeDate);
        typePlans = optionalTypePlans.orElse(new ArrayList<>());

        Optional<List<String>> optionalRuteRoles = findActualRunningSchedulePort.loadRuteFromActualPlanByActualCode(actualCodeDate);
        ruteRoles = optionalRuteRoles.orElse(new ArrayList<>());
    }

    private void makeParam() throws Exception {
        paramActualPlanDto = ParamActualPlanDto.builder()
                .ruteRoles(ruteRoles)
                .typePlans(typePlans)
                .build();
    }

    @Override
    public ParamActualPlanDto getAllParam() throws Exception {
        try {
            fetchAllDataParamFromDb();
            makeAllParam();
            return paramActualPlanDto;
        } catch(Exception e) {
            throw new Exception("Gagal mengambil semua actual param");
        }
    }

    private void fetchAllDataParamFromDb() throws Exception {
        Optional<List<String>> optionalTypePlans = findActualRunningSchedulePort.loadAllTypePlanFromActualPlan();
        typePlans = optionalTypePlans.orElse(new ArrayList<>());

        Optional<List<String>> optionalRuteRoles = findActualRunningSchedulePort.loadAllRuteRoleFromActualPlan();
        ruteRoles = optionalRuteRoles.orElse(new ArrayList<>());
    }

    private void makeAllParam() throws Exception {
        paramActualPlanDto = ParamActualPlanDto.builder()
                .ruteRoles(ruteRoles)
                .typePlans(typePlans)
                .build();
    }
}
