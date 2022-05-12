package len.silvue.webpendukung.gapeka.application.services;

import len.silvue.webpendukung.gapeka.adapter.out.web.GapekaDto;
import len.silvue.webpendukung.gapeka.application.port.in.GapekaDiagramUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GapekaDiagramService implements GapekaDiagramUseCase {
    private final GapekaMasterPlanRuteService gapekaMasterPlanRuteService;
    private final GapekaMasterPlanLineService gapekaMasterPlanLineService;
    private final GapekaTodayRuteService gapekaTodayRuteService;
    private final GapekaTodayLineService gapekaTodayLineService;
    private final GapekaActualService gapekaActualService;

    @Override
    public GapekaDto makeAndViewGapekaMasterPlanByTypeMasterPlanAndRuteType(int typeMasterPlanId, int ruteTypeId) throws Exception {
        try {
            gapekaMasterPlanRuteService.setTypeMasterPlanId(typeMasterPlanId);
            gapekaMasterPlanRuteService.setRuteRoleId(ruteTypeId);
            gapekaMasterPlanRuteService.generateGapekaDto();
            return gapekaMasterPlanRuteService.getGapekaDto();
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception("Gagal membuat dan mengambil gapeka master by masterplantype dan rutetype", e);
        }
    }

    @Override
    public GapekaDto makeAndViewGapekaMasterPlanByTypeMasterPlanAndLineType(int typeMasterPlanId, int lineTypeId) throws Exception {
        try {
            gapekaMasterPlanLineService.setTypeMasterPlanId(typeMasterPlanId);
            gapekaMasterPlanLineService.setLineId(lineTypeId);
            gapekaMasterPlanLineService.generateGapekaDto();
            return gapekaMasterPlanLineService.getGapekaDto();
        } catch(Exception e) {
            throw new Exception("Gagal membuat dan mengambil gapeka master plan by masterplantype dan line type", e);
        }
    }

    @Override
    public GapekaDto makeAndViewGapekaTodayByRuteType(int typeMasterPlanId, int ruteTypeId) throws Exception {
        try {
            gapekaTodayRuteService.setTypeMasterPlanId(typeMasterPlanId);
            gapekaTodayRuteService.setRuteRoleId(ruteTypeId);
            gapekaTodayRuteService.generateGapekaDto();
            return gapekaTodayRuteService.getGapekaDto();
        } catch(Exception e) {
            throw new Exception("Gagal membuat dan mengambil gapeka today schedule by rute", e);
        }
    }

    @Override
    public GapekaDto makeAndViewGapekaTodayByLineType(int typeMasterPlanId, int lineTypeId) throws Exception {
        try {
            gapekaTodayLineService.setTypeMasterPlanId(typeMasterPlanId);
            gapekaTodayLineService.setLineId(lineTypeId);
            gapekaTodayLineService.generateGapekaDto();
            return gapekaTodayLineService.getGapekaDto();
        } catch(Exception e) {
            throw new Exception("Gagal membuat dan mengambil gapeka today schedule by line", e);
        }
    }

    @Override
    public GapekaDto makeAndViewGapekaActualByTypePlanAndRuteRoleAndAActualDate(String typePlan, String ruteRole,
                                                                                String actualDate) throws Exception {
        try {
            gapekaActualService.setActualCode(actualDate);
            gapekaActualService.setTypePlan(typePlan);
            gapekaActualService.setRuteRole(ruteRole);
            gapekaActualService.generateGapekaDto();
            return gapekaActualService.getGapekaDto();
        } catch(Exception e) {
            throw new Exception("Gagal membuat dan mengambil gapeka actual", e);
        }
    }
}
