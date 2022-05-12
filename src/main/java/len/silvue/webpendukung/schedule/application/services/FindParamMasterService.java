package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.gapeka.adapter.out.web.mapper.LineMapper;
import len.silvue.webpendukung.gapeka.application.port.out.LoadLinePort;
import len.silvue.webpendukung.domains.Line;
import len.silvue.webpendukung.schedule.adapter.out.web.ParamDto;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.RuteRoleMapper;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.TypeMasterPlanMapper;
import len.silvue.webpendukung.schedule.application.port.in.FindParamMasterUseCase;
import len.silvue.webpendukung.schedule.application.port.out.LoadRuteRolePort;
import len.silvue.webpendukung.schedule.application.port.out.LoadTypeMasterPlanPort;
import len.silvue.webpendukung.domains.RuteRole;
import len.silvue.webpendukung.domains.TypeMasterPlan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindParamMasterService implements FindParamMasterUseCase {
    private final LoadRuteRolePort loadRuteRolePort;
    private final LoadLinePort loadLinePort;
    private final LineMapper lineMapper;
    private final RuteRoleMapper ruteRoleMapper;
    private final LoadTypeMasterPlanPort loadTypeMasterPlanPort;
    private final TypeMasterPlanMapper typeMasterPlanMapper;

    private List<Line> lineList;
    private List<RuteRole> ruteRoleList;
    private List<TypeMasterPlan> typeMasterPlanList;
    private ParamDto paramDto;

    @Override
    public ParamDto getAllParam() throws Exception {
        try {
            fetchLines();
            fetchRuteRoles();
            fetchTypeMasterPlans();
            makeParam();
            return paramDto;
        } catch(Exception e) {
            throw new Exception("Gagal mengambil all param", e);
        }
    }

    private void fetchLines() throws Exception {
        Optional<List<Line>> optionalLines = loadLinePort.loadAllLine();
        lineList = optionalLines.orElse(new ArrayList<>());
    }

    private void fetchRuteRoles() throws Exception {
        Optional<List<RuteRole>> optionalRuteRoles = loadRuteRolePort.loadAllRuteRole();
        ruteRoleList = optionalRuteRoles.orElse(new ArrayList<>());
    }

    private void fetchTypeMasterPlans() throws Exception {
        Optional<List<TypeMasterPlan>> optionalTypeMasterPlans = loadTypeMasterPlanPort.loadAllTypeMasterPlan();
        typeMasterPlanList = optionalTypeMasterPlans.orElse(new ArrayList<>());
    }

    private void makeParam() throws Exception {
        paramDto = ParamDto.builder()
                .lineList(lineMapper.toLineDtoList(lineList))
                .ruteRoleList(ruteRoleMapper.toRuteRoleDtoList(ruteRoleList))
                .typeMasterPlanList(typeMasterPlanMapper.toTypeMasterPlanDtoList(typeMasterPlanList))
                .build();
    }
}
