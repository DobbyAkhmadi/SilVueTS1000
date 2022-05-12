package len.silvue.webpendukung.gapeka.application.port.in;

import len.silvue.webpendukung.gapeka.adapter.out.web.GapekaDto;

import java.util.Date;

public interface GapekaDiagramUseCase {
    GapekaDto makeAndViewGapekaMasterPlanByTypeMasterPlanAndRuteType(int typeMasterPlanId, int ruteTypeId) throws Exception;
    GapekaDto makeAndViewGapekaMasterPlanByTypeMasterPlanAndLineType(int typeMasterPlanId, int lineTypeId) throws Exception;
    GapekaDto makeAndViewGapekaTodayByRuteType(int typeMasterPlanId, int ruteTypeId) throws Exception;
    GapekaDto makeAndViewGapekaTodayByLineType(int typeMasterPlanId, int lineTypeId) throws Exception;
    GapekaDto makeAndViewGapekaActualByTypePlanAndRuteRoleAndAActualDate(String typePlan, String ruteRole,
                                                                         String actualDate) throws Exception;
}
