package len.silvue.webpendukung.gapeka.adapter.in.web.controller;

import len.silvue.webpendukung.gapeka.adapter.out.web.GapekaDto;
import len.silvue.webpendukung.gapeka.application.services.GapekaDiagramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ts1000")
@RequiredArgsConstructor
@Slf4j
public class GapekaApiController {
    private final GapekaDiagramService gapekaDiagramService;

    @GetMapping("/gapeka_master_plan_rute_role")
    public ResponseEntity<GapekaDto> viewGapekaMasterPlanRouteRole(@RequestParam(value = "typeMasterPlanId") int typeMasterPlanId,
                                                                   @RequestParam(value = "ruteRoleId") int ruteRoleId) {
        try {
            GapekaDto gapekaDto = gapekaDiagramService.makeAndViewGapekaMasterPlanByTypeMasterPlanAndRuteType(typeMasterPlanId, ruteRoleId);
            return ResponseEntity.ok().body(gapekaDto);
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal mengambil gapeka master plan rute role");
        }
    }

    @GetMapping("/gapeka_master_plan_line")
    public ResponseEntity<GapekaDto> viewGapekaMasterPlanLine(@RequestParam(value = "typeMasterPlanId") int typeMasterPlanId,
                                                              @RequestParam(value = "lineTypeId") int lineTypeId) {
        try {
            GapekaDto gapekaDto = gapekaDiagramService.makeAndViewGapekaMasterPlanByTypeMasterPlanAndLineType(typeMasterPlanId, lineTypeId);
            return ResponseEntity.ok().body(gapekaDto);
        } catch(Exception e) {
            throw new RuntimeException("Gagal mengambil gapeka master plan line");
        }
    }

    @GetMapping("/gapeka_today_schedule_rute")
    public ResponseEntity<GapekaDto> viewGapekaTodayScheduleRute(@RequestParam(value = "typeMasterPlanId") int typeMasterPlanId,
                                                                 @RequestParam(value = "ruteRoleId") int ruteRoleId) {
        try {
            GapekaDto gapekaDto = gapekaDiagramService.makeAndViewGapekaTodayByRuteType(typeMasterPlanId, ruteRoleId);
            return ResponseEntity.ok().body(gapekaDto);
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal mengambil gapeka today schedule");
        }
    }

    @GetMapping("/gapeka_today_schedule_line")
    public ResponseEntity<GapekaDto> viewGapekaTodayLineRute(@RequestParam(value = "typeMasterPlanId") int typeMasterPlanId,
                                                             @RequestParam(value = "lineId") int lineId) {
        try {
            GapekaDto gapekaDto = gapekaDiagramService.makeAndViewGapekaTodayByLineType(typeMasterPlanId, lineId);
            return ResponseEntity.ok().body(gapekaDto);
        } catch(Exception e) {
            throw new RuntimeException("Gagal mengambil gapeka today line");
        }
    }

    @GetMapping("/gapeka_actual")
    public ResponseEntity<GapekaDto> viewGapekaActual(@RequestParam String typePlan,
                                                      @RequestParam String ruteRole,
                                                      @RequestParam String actualCode) {
       try {
            GapekaDto gapekaDto = gapekaDiagramService.makeAndViewGapekaActualByTypePlanAndRuteRoleAndAActualDate(
                    typePlan, ruteRole, actualCode);
            return ResponseEntity.ok().body(gapekaDto);
       } catch(Exception e) {
           e.printStackTrace();
           throw new RuntimeException("Gagal mengambil gapeka actual");
       }
    }
}
