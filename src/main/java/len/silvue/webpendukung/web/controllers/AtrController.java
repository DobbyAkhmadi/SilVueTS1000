package len.silvue.webpendukung.web.controllers;

import len.silvue.webpendukung.ars.adapter.in.web.ModifyArsCommand;
import len.silvue.webpendukung.ars.adapter.out.web.ArsConflictDto;
import len.silvue.webpendukung.ars.adapter.out.web.ArsScheduleDto;
import len.silvue.webpendukung.ars.application.port.in.*;
import len.silvue.webpendukung.ars.application.port.out.LoadConflictArsPort;
import len.silvue.webpendukung.domains.RouteStick;
import len.silvue.webpendukung.schedule.adapter.out.web.RouteStickDto;
import len.silvue.webpendukung.schedule.application.port.in.FindRouteStickUseCase;
import len.silvue.webpendukung.tmconfig.application.port.in.FindConfigurationUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/atr")
@RequiredArgsConstructor
@Slf4j
public class AtrController {
    private final ArsScheduleUseCase arsScheduleUseCase;
    private final DeleteArsScheduleUseCase deleteArsScheduleUseCase;
    private final FindConfigurationUseCase findConfigurationUseCase;
    private final AddArsDetailUseCase addArsDetailUseCase;
    private final FindArsConflictUseCase findArsConflictUseCase;
    private final ModifyArsUseCase modifyArsUseCase;
    private final LoadConflictArsPort loadConflictArsPort;
    private final FindRouteStickUseCase findRouteStickUseCase;

    @GetMapping
    public ModelAndView viewAtrPage() {
        try {
            ModelAndView atr = new ModelAndView("atr/atr");
            List<ArsConflictDto> AtrSchedules = findArsConflictUseCase.getAllArsConflict();
            atr.addObject("AtrSchedule", AtrSchedules);
            System.out.println(atr);
            return atr;
        } catch(Exception e){
            throw new RuntimeException("Gagal masuk view ATR", e);
        }
    }

    //    @PostMapping("/modify-peron-ars")
//    public ModelAndView modifyDetailMasterPlan(@ModelAttribute ModifyArsCommand modifyArsCommand) {
//        try {
//            MasterPlanDto masterPlanDto=modifyMasterPlanUseCase.modifyMasterPlan(modifyArsCommand);
//            return new ModelAndView("redirect:/schedule/masterschedule/view-modify-schedule-rute/"
//                    +masterPlanDto.getTrain().getIdTrain()+"/"
//                    +masterPlanDto.getTypeMasterPlan().getIdTypeMasterPlan());
//        }catch (Exception e) {
//            throw new RuntimeException("failed to modify detail master plan",e);
//        }
//    }
    private ModelAndView modelAndViewAtr() throws Exception {
        try {
            ModelAndView mav = new ModelAndView();
            List<ArsConflictDto> AtrSchedules = findArsConflictUseCase.getAllArsConflict();
            mav.addObject("AtrSchedule", AtrSchedules);
            return mav;
        } catch(Exception e) {
            throw new Exception("failed to get data schedule", e);
        }
    }

    @GetMapping("/checkArsConflict/{id1}/{id2}")
    private ModelAndView viewModalModifyAtr(@PathVariable String id1 ,@PathVariable String id2) throws Exception{
        try {
            int idArsSchedule = Integer.parseInt(id1);
            int idRouteStick = Integer.parseInt(id2);
            ModelAndView mav = new ModelAndView("atr/view-modify-atr");
            ArsScheduleDto arsScheduleDto = findArsConflictUseCase.getArsScheduleById(idArsSchedule);
            RouteStickDto routeStickDto = findArsConflictUseCase.getRoutestickById(idRouteStick);
            mav.addObject("getAtr",arsScheduleDto);
            mav.addObject("getRouteStick",routeStickDto);
            System.out.println(mav);
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("failed to get modify ars schedule",e);
        }

    }

    @PostMapping("/modify-atr-conflict")
    public ModelAndView modifyAtrConflict(@ModelAttribute ModifyArsCommand modifyArsCommand) {
        try {

            if (modifyArsCommand.getChoice().equals("platformMod")){
                modifyArsUseCase.modifyArsConflict(modifyArsCommand);
            }
            else
            {
                modifyArsUseCase.modifyArsConflictList(modifyArsCommand);
            }
            return new ModelAndView("redirect:/atr");
        }catch (Exception e) {
            throw new RuntimeException("failed to modify detail master plan",e);
        }
    }


}
