package len.silvue.webpendukung.web.controllers;

import len.silvue.webpendukung.schedule.application.port.in.*;
import len.silvue.webpendukung.todays.adapter.in.AddTodayCommand;

import len.silvue.webpendukung.todays.adapter.in.DeleteTodayDetailCommand;
import len.silvue.webpendukung.todays.adapter.in.ModifyTodayCommand;
import len.silvue.webpendukung.todays.adapter.out.web.TodayRunningScheduleDto;
import len.silvue.webpendukung.todays.application.port.in.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/today")
@RequiredArgsConstructor
@Slf4j
public class TodaysDetailController {
    private final TodayRunningScheduleUseCase todayRunningScheduleUseCase;
    private final FindTypeMasterPlanUseCase findTypeMasterPlanUseCase;
    private final FindTrainUseCase findTrainUseCase;
    private final FindRuteRoleUseCase findRuteRoleUseCase;
    private final FindStationUseCase findStationUseCase;
    private final ModifyTodayDetailUseCase modifyTodayTypeUseCase;
    private final DeleteTodayDetailUseCase deleteTodayDetailUseCase;
    private final AddTodayDetailUseCase addTodayDetailUseCase;
    private final AddTodayToActualUseCase addTodayToActualUseCase;
    private final FindMasterPlanUseCase findMasterPlanUseCase;
    @GetMapping("/todaysDetail")
    private ModelAndView viewPageTodaysDetail() {
        try {
            return modelViewForTodaySchedule();
        } catch(Exception e) {
            throw new RuntimeException("Error load schedule main page");
        }
    }

    //==================================================================================================================
    // VIEW ALL DATA TO TODAYS DETAIL
    //==================================================================================================================
    private ModelAndView modelViewForTodaySchedule() throws Exception {
        try {
            ModelAndView mav = new ModelAndView("today/todaysDetail");
            List<TodayRunningScheduleDto> todayRunningScheduleDtoList = todayRunningScheduleUseCase.getAllTodayRunningSchedule();
            mav.addObject("getTodayDetails", todayRunningScheduleDtoList);
            mav.addObject("typeMasterPlans", findTypeMasterPlanUseCase.getAllTypeMasterPlan());
            log.info("get All Data TodayDetail Schedule :" + todayRunningScheduleDtoList.size());
            return mav;
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    //==================================================================================================================
    // VIEW SELECT TYPE SCHEDULE
    //==================================================================================================================
    @GetMapping("/view-type-schedule")
    public ModelAndView viewTypeSchedule() {
        try {
            ModelAndView mav = new ModelAndView("today/view-type-schedule");
            mav.addObject("typeMasterPlans", findTypeMasterPlanUseCase.getAllTypeMasterPlan());
            log.info("typeMasterPlans", findTypeMasterPlanUseCase.getAllTypeMasterPlan());
            return mav;
        } catch (Exception e) {
            throw new RuntimeException("failed to view select type schedule",e);
        }
    }


    //==================================================================================================================
    // GET DATA FOR INSERT MASTER PLAN
    //==================================================================================================================
    private ModelAndView modelAndViewTodayDetail() throws Exception {
        try {
            ModelAndView mav = new ModelAndView();
            mav.addObject("stations", findStationUseCase.getAllStation());
            mav.addObject("trains", findTrainUseCase.getAllTrain());
            mav.addObject("routeRoles", findRuteRoleUseCase.getAllRuteRole());
            mav.addObject("typeMasterPlans", findTypeMasterPlanUseCase.getAllTypeMasterPlan());
            return mav;
        } catch(Exception e) {
            throw new Exception("failed to get datatodaysdetailschedule", e);
        }
    }


    //==================================================================================================================
    // VIEW MODIFY TODAY DETAIL MAIN PAGE
    //==================================================================================================================
    @GetMapping("/view-modify-all-today-schedule-rute/{id1}/{id2}")
    private ModelAndView viewModifyTodaySchedule(@PathVariable String id1 ,@PathVariable String id2) {
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("today/view-modify-all-today-schedule");
            int idTrain = Integer.parseInt(id1);
            int idTypeMasterPlan = Integer.parseInt(id2);
            List<TodayRunningScheduleDto> todayRunningScheduleDtoList = todayRunningScheduleUseCase.
                    getTodayRunningScheduleByTrainIdAndTypeMasterPlanId(idTrain, idTypeMasterPlan);
            mav.addObject("getTodayDetails", todayRunningScheduleDtoList);
            log.info("get Data Modify TodayDetailSchedule ById :" + idTrain + idTypeMasterPlan + todayRunningScheduleDtoList.size());
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("failed to get modify today running schedule",e);
        }
    }

    //==================================================================================================================
    // VIEW MODIFY DETAIL TODAY
    //==================================================================================================================
    @GetMapping("/view-modify-todayDetil-schedule/{id}")
    private ModelAndView viewModifyTodayDetailSchedule(@PathVariable String id) {
        try {
            ModelAndView mav = modelAndViewTodayDetail();
            mav.setViewName("today/view-modify-todayDetail-schedule");
            int idTrain = Integer.parseInt(id);
            mav.addObject("getDetailTodayDetail",todayRunningScheduleUseCase.getTodayRunningScheduleById(idTrain));
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("failed to get modify detail todayDetail",e);
        }
    }

    //==================================================================================================================
    // VIEW MODIFY TODAY DETAIL ALL
    //==================================================================================================================
    @GetMapping("/view-modify-todayDetail-all/{id}")
    private ModelAndView viewModifyDetailTodaySchedule(@PathVariable String id) {
        try {
            ModelAndView mav = modelAndViewTodayDetail();
            mav.setViewName("today/view-modify-today-schedule");
            int idTrain = Integer.parseInt(id);
            mav.addObject("getDetailTodayDetail",todayRunningScheduleUseCase.getTodayRunningScheduleById(idTrain));
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("failed to get modify master plan");
        }
    }

    //==================================================================================================================
    // MODIFY ALL TODAY DETAIL
    //==================================================================================================================
    @PostMapping("/modify-all-todaysDetail")
    public ModelAndView modifyAllTodayDetail(@ModelAttribute ModifyTodayCommand modifyTodayCommand) {
        try {
            List<TodayRunningScheduleDto> todayRunningScheduleDtoList =
                    modifyTodayTypeUseCase.modifyAllTodayDetailScheduleType(modifyTodayCommand);
            if (todayRunningScheduleDtoList.size()>0)
            {
                return new ModelAndView("redirect:/today/view-modify-all-today-schedule-rute/"
                        +todayRunningScheduleDtoList.get(0).getTrain().getIdTrain()+"/"
                        +todayRunningScheduleDtoList.get(0).getTypeMasterPlan().getIdTypeMasterPlan());
            }
            else
            {
                return new ModelAndView("redirect:/today/todaysDetail/");
            }
        }catch (Exception e) {
            throw new RuntimeException("failed to modify all todaydetail",e);
        }
    }

    //==================================================================================================================
    // MODIFY DETAIL TODAY DETAIL
    //==================================================================================================================
    @PostMapping("/modify-detail-todayDetail")
    public ModelAndView modifyDetailTodayDetail(@ModelAttribute ModifyTodayCommand modifyTodayCommand) {
        try {
            TodayRunningScheduleDto todayRunningScheduleDtoList =
                    modifyTodayTypeUseCase.modifyTodayRunningScheduleType(modifyTodayCommand);
            return new ModelAndView("redirect:/today/view-modify-all-today-schedule-rute/"
                    +todayRunningScheduleDtoList.getTrain().getIdTrain()+"/"
                    +todayRunningScheduleDtoList.getTypeMasterPlan().getIdTypeMasterPlan());
        }catch (Exception e) {
            throw new RuntimeException("failed to modify today detail",e);
        }
    }

    //==================================================================================================================
    // DELETE ALL TodayDetail
    //==================================================================================================================
    @GetMapping("/delete-all-todayDetail/")
    public ModelAndView deleteAllTodayDetail(@ModelAttribute DeleteTodayDetailCommand deletetodayScheduleCommand) {
        try {
            deleteTodayDetailUseCase.deleteAllTodayDetail();
            viewPageTodaysDetail();
            return new ModelAndView("redirect:/today/todaysDetail/" );
        }catch (Exception e) {
            throw new RuntimeException("failed to delete all todaydetail",e);
        }
    }

    //==================================================================================================================
    // DELETE ALL TODAY DETAIL BY ID TRAIN
    //==================================================================================================================
    @GetMapping("/delete-all-todayDetail-by-idtrain/{id}")
    public ModelAndView deleteAllTodayDetailByTrainId(@PathVariable String id) {
        try {
            int trainId = Integer.parseInt(id);
            deleteTodayDetailUseCase.deleteAllTodayDetailByTrainId(trainId);
            viewPageTodaysDetail();
            return new ModelAndView("redirect:/today/todaysDetail/" );
        } catch (Exception e) {
            throw new RuntimeException("failed to delete all detail today detail by id train",e);
        }
    }
    //==================================================================================================================
    // DELETE ONE DATA TODAY DETAIL DETAIL BY ID TRAIN
    //==================================================================================================================
    @GetMapping("/delete-todayDetail-by-idTodayRunningSchedule/{id}")
    public ModelAndView deleteTodayDetailByIdTrain(@PathVariable String id) {
        // ModelAndView mav =null;
        try {
            int idTodayRunningSchedule = Integer.parseInt(id);
            TodayRunningScheduleDto todayRunningScheduleDto = deleteTodayDetailUseCase.deleteTodayRunningScheduleById(idTodayRunningSchedule);
            return new ModelAndView("redirect:/today/view-modify-all-today-schedule-rute/"
                    +todayRunningScheduleDto.getTrain().getIdTrain()+"/"
                    +todayRunningScheduleDto.getTypeMasterPlan().getIdTypeMasterPlan());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("failed to delete today detail by id train",e);
        }
    }

    //==================================================================================================================
    // TYPE SCHEDULE COMMAND
    //==================================================================================================================
    @PostMapping("/type-schedule-command")
    public ModelAndView typeScheduleCommand(@ModelAttribute AddTodayCommand addTodayCommand, BindingResult bindingResult) {
        try {
            addTodayDetailUseCase.setTodayRunningScheduleFromMasterPlan(addTodayCommand.getIdTypeMasterPlan());
            return new ModelAndView("redirect:/today/todaysDetail");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("failed select type schedule",e);
        }
    }

    //==================================================================================================================
    // TODAY TO ACTUAL
    //==================================================================================================================
    @GetMapping("/today-to-actual")
    public ModelAndView todaytoactual() {
        try {
            addTodayToActualUseCase.setActualPlanFromToday();
            return new ModelAndView("redirect:/today/todaysDetail");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("failed insert to actual",e);
        }
    }

    //==================================================================================================================
    // TODAY TO ACTUAL
    //==================================================================================================================
    @GetMapping("/today-to-actual-with-conflict")
    public ModelAndView todaytoactualwithconflict() {
        try {
            addTodayToActualUseCase.setActualPlanFromTodayWithConflict();
            return new ModelAndView("redirect:/today/todaysDetail");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("failed insert to actual",e);
        }
    }


}
