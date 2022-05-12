package len.silvue.webpendukung.web.controllers;

import len.silvue.webpendukung.schedule.adapter.in.web.*;
import len.silvue.webpendukung.schedule.adapter.out.web.MasterPlanDto;
import len.silvue.webpendukung.schedule.adapter.out.web.TypeMasterPlanDto;
import len.silvue.webpendukung.schedule.application.port.in.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/schedule/masterschedule")
@RequiredArgsConstructor
@Slf4j
public class MasterPlanController {
    private final FindTrainUseCase findTrainUseCase;
    private final FindRuteRoleUseCase findRuteRoleUseCase;
    private final FindMasterPlanUseCase findMasterPlanUseCase;
    private final FindTypeMasterPlanUseCase findTypeMasterPlanUseCase;
    private final FindStationUseCase findStationUseCase;

    private final AddMasterPlanUseCase addMasterPlanUseCase;
    private final DeleteMasterPlanUseCase deleteMasterPlanUseCase;
    private final ModifyMasterPlanUseCase modifyMasterPlanUseCase;

    private final ExportUseCase exportUseCase;
    private final ImportUseCase importUseCase;

    //==================================================================================================================
    // DEFAULT MAIN PAGE MASTER PLAN
    //==================================================================================================================
    @GetMapping
    private ModelAndView viewPageMasterPlan() {
        try {
            return modelViewForSchedule();
        } catch(Exception e) {
            throw new RuntimeException("Error load schedule main page");
        }
    }
    //==================================================================================================================
    // VIEW ALL DATA TO MASTER PLAN
    //==================================================================================================================
    private ModelAndView modelViewForSchedule() throws Exception {
        try {
            ModelAndView mav = new ModelAndView("schedule/masterschedule");
            List<MasterPlanDto> masterPlanDtoList = findMasterPlanUseCase.getAllMasterPlan();
        //    List<ConflictMasterPlanDto> conflictMasterPlanDtoList = findConflictTableMasterUseCase.loadAllConflict();
            mav.addObject("masterPlans", masterPlanDtoList);
            mav.addObject("typeMasterPlans", findTypeMasterPlanUseCase.getAllTypeMasterPlan());
           // mav.addObject("getConflicts",conflictMasterPlanDtoList);
            log.info("get All Data Schedule :" + masterPlanDtoList.size());
            return mav;
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }
    //==================================================================================================================
    // SHOW ADD DATA MASTER PLAN
    //==================================================================================================================
    @GetMapping("/view-add-data-schedule")
    private ModelAndView viewPageAddDataSchedules() {
        try {
            ModelAndView mav = modelAndViewMasterPlan();
            mav.setViewName("schedule/view-add-data-schedule");
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("Error load add schedule page",e);
        }
    }
    //==================================================================================================================
    // VIEW MODIFY MASTER PLAN
    //==================================================================================================================
    @GetMapping("view-modify-schedule-rute/{id1}/{id2}")
    private ModelAndView viewModifyScheduleRute(@PathVariable String id1 ,@PathVariable String id2) {
        try {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("schedule/view-modify-data-schedule-rute");
            int idTrain = Integer.parseInt(id1);
            int idTypeMasterPlan = Integer.parseInt(id2);
            List<MasterPlanDto> masterPlanDtoList = findMasterPlanUseCase.getMasterPlanByTrainIdAndTypeMasterPlanId(idTrain, idTypeMasterPlan);
            mav.addObject("getMasterPlans", masterPlanDtoList);
            log.info("get Data Modify Schedule ById :" + idTrain + idTypeMasterPlan + masterPlanDtoList.size());
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("failed to get modify master plan",e);
        }
    }
    //==================================================================================================================
    // VIEW MODIFY DETAIL MASTER PLAN
    //==================================================================================================================
    @GetMapping("/view-modify-schedule-rute-detail/{id}")
    private ModelAndView viewModifyDetailMasterPlan(@PathVariable String id) {
        try {
            ModelAndView mav = modelAndViewMasterPlan();
            mav.setViewName("schedule/view-modify-schedule-detail");
            int idTrain = Integer.parseInt(id);
            mav.addObject("getDetailMasterPlans",findMasterPlanUseCase.getMasterPlanById(idTrain));
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("failed to get modify detail master plan",e);
        }
    }
    //==================================================================================================================
    // VIEW MODIFY MASTER PLAN
    //==================================================================================================================
    @GetMapping("/view-modify-schedule-rute/{id}")
    private ModelAndView viewModifyMasterPlan(@PathVariable String id) {
        try {
            ModelAndView mav = modelAndViewMasterPlan();
            mav.setViewName("schedule/view-modify-schedule");
            int idTrain = Integer.parseInt(id);
            mav.addObject("getDetailMasterPlans",findMasterPlanUseCase.getMasterPlanById(idTrain));
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("failed to get modify master plan");
        }
    }
    //==================================================================================================================
    // INSERT COMMAND QUERY MASTER PLAN
    //==================================================================================================================
    @PostMapping("/add-command-master-plan")
    private ModelAndView addMasterPlanCommands(@ModelAttribute AddMasterPlanCommand addMasterPlanCommand, BindingResult bindingResult) {
        try {
            addMasterPlanUseCase.saveMasterPlan(addMasterPlanCommand);
            return new ModelAndView("redirect:/schedule/masterschedule/" );
        } catch (Exception e) {
            throw new RuntimeException("Error add schedule", e);
        }
    }
    //==================================================================================================================
    // GET DATA FOR INSERT MASTER PLAN
    //==================================================================================================================
    private ModelAndView modelAndViewMasterPlan() throws Exception {
        try {
            ModelAndView mav = new ModelAndView();
            mav.addObject("stations", findStationUseCase.getAllStation());
            mav.addObject("trains", findTrainUseCase.getAllTrain());
            mav.addObject("routeRoles", findRuteRoleUseCase.getAllRuteRole());
            mav.addObject("typeMasterPlans", findTypeMasterPlanUseCase.getAllTypeMasterPlan());
            return mav;
        } catch(Exception e) {
            throw new Exception("failed to get data schedule", e);
        }
    }
    //==================================================================================================================
    // DELETE ALL MASTER PLAN
    //==================================================================================================================
    @GetMapping("/delete-all-masterplan/")
    public ModelAndView deleteAllMasterPlans(@ModelAttribute DeleteMasterPlanCommand deleteScheduleCommand) {
        try {
            deleteMasterPlanUseCase.deleteAllMasterPlan();
            modelViewForSchedule();
            return new ModelAndView("redirect:/schedule/masterschedule/" );
        }catch (Exception e) {
            throw new RuntimeException("failed to delete all master plan",e);
        }
    }
    //==================================================================================================================
    // MODIFY ALL MASTER PLAN
    //==================================================================================================================
    @PostMapping("/modify-all-masterplan")
    public ModelAndView modifyAllMasterPlan(@ModelAttribute ModifyMasterPlanCommand modifyMasterPlanCommand) {
        try {
            List<MasterPlanDto> masterPlanDtoList =  modifyMasterPlanUseCase.modifyAllMasterPlan(modifyMasterPlanCommand);
            if (masterPlanDtoList.size()>0)
            {
                return new ModelAndView("redirect:/schedule/masterschedule/view-modify-schedule-rute/"
                        +masterPlanDtoList.get(0).getTrain().getIdTrain()+"/"
                        +masterPlanDtoList.get(0).getTypeMasterPlan().getIdTypeMasterPlan());
            }
            else
            {
                return new ModelAndView("redirect:/schedule/masterschedule/");
            }
        }catch (Exception e) {
            throw new RuntimeException("failed to modify all master plan",e);
        }
    }
    //==================================================================================================================
    // MODIFY DETAIL MASTER PLAN
    //==================================================================================================================
    @PostMapping("/modify-detail-masterplan")
    public ModelAndView modifyDetailMasterPlan(@ModelAttribute ModifyMasterPlanCommand modifyMasterPlanCommand) {
        try {
            MasterPlanDto masterPlanDto=modifyMasterPlanUseCase.modifyMasterPlan(modifyMasterPlanCommand);
            return new ModelAndView("redirect:/schedule/masterschedule/view-modify-schedule-rute/"
                   +masterPlanDto.getTrain().getIdTrain()+"/"
                    +masterPlanDto.getTypeMasterPlan().getIdTypeMasterPlan());
        }catch (Exception e) {
            throw new RuntimeException("failed to modify detail master plan",e);
        }
    }
    //==================================================================================================================
    // DELETE ALL MASTER PLAN BY ID TRAIN
    //==================================================================================================================
    @GetMapping("/delete-all-masterplan-by-idtrain/{id}")
    public ModelAndView deleteAllMasterPlanByTrainId(@PathVariable String id) {
        try {
            int trainId = Integer.parseInt(id);
            deleteMasterPlanUseCase.deleteAllMasterPlanByTrainId(trainId);
            modelViewForSchedule();
            return new ModelAndView("redirect:/schedule/masterschedule/" );
        } catch (Exception e) {
            throw new RuntimeException("failed to delete all detail master plan by id train",e);
        }
    }
    //==================================================================================================================
    // DELETE ONE DATA MASTER PLAN DETAIL BY ID TRAIN
    //==================================================================================================================
    @GetMapping("/delete-masterplan-by-idtrain/{id}")
    public ModelAndView deleteMasterPlanByIdTrain(@PathVariable String id) {
       // ModelAndView mav =null;
        try {
            int trainId = Integer.parseInt(id);
            MasterPlanDto masterPlanDto = deleteMasterPlanUseCase.deleteMasterPlanByTrainId(trainId);
            modelViewForSchedule();
            return new ModelAndView("redirect:/schedule/masterschedule/view-modify-schedule-rute/"
                    +masterPlanDto.getTrain().getIdTrain()+"/"
                    +masterPlanDto.getTypeMasterPlan().getIdTypeMasterPlan());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("failed to delete master plan by id train",e);
        }
    }
    //==================================================================================================================
    // VIEW COMBINE SCHEDULE
    //==================================================================================================================
    @GetMapping("/view-combine-schedule/")
    public ModelAndView viewCombineSchedule() {
        try {
            ModelAndView mav = new ModelAndView("schedule/view-combine-schedule");
            mav.addObject("typeMasterPlans", findTypeMasterPlanUseCase.getAllTypeMasterPlan());
            return mav;
        } catch (Exception e) {
            throw new RuntimeException("failed to view combine schedule",e);
        }
    }
    //==================================================================================================================
    // COMBINE SCHEDULE COMMAND
    //==================================================================================================================
    @PostMapping("/combine-schedule-command")
    public ModelAndView combineScheduleCommand(@ModelAttribute CombineMasterPlanCommand combineMasterPlanCommand, BindingResult bindingResult) {
        try {
            modifyMasterPlanUseCase.combineMasterPlanByTypeMasterPlan(combineMasterPlanCommand);
            return new ModelAndView("redirect:/schedule/masterschedule/");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("failed to combine schedule by id type schedule",e);
        }
    }
    //==================================================================================================================
    // VIEW IMPORT SCHEDULE
    //==================================================================================================================
    @GetMapping("/view-import-schedule/")
    public ModelAndView viewImportSchedule() {
        try {
            ModelAndView mav = new ModelAndView("schedule/view-import-schedule");
            return mav;
        } catch (Exception e) {
            throw new RuntimeException("Gagal mengambil import schedule type",e);
        }
    }
    //==================================================================================================================
    // VIEW EXPORT SCHEDULE
    //==================================================================================================================
    @GetMapping("/view-export-schedule/")
    public ModelAndView viewExportSchedule() {
        try {
            ModelAndView mav = new ModelAndView("schedule/view-export-schedule");
            mav.addObject("typeMasterPlans", findTypeMasterPlanUseCase.getAllTypeMasterPlan());
            return mav;
        } catch (Exception e) {
            throw new RuntimeException("Gagal mengambil export schedule type",e);
        }
    }
    //==================================================================================================================
    // EXPORT COMMAND QUERY MASTER PLAN
    //==================================================================================================================
    @PostMapping("/export-schedule")
    public ModelAndView exportScheduleCSV(HttpServletResponse response, @ModelAttribute ExportScheduleCommand exportScheduleCommand, BindingResult bindingResult) throws Exception {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("HH-mm-ss dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());
        TypeMasterPlanDto typeMasterPlanDto = findTypeMasterPlanUseCase.getTypeMasterPlanById(exportScheduleCommand.getIdTypeMasterPlan());

        // format Write ALl CSV
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=TS1000-Master-Schedule_" + typeMasterPlanDto.getNameTypeMasterPlan() +  " | " + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        exportUseCase.generateMasterSchedule(exportScheduleCommand.getIdTypeMasterPlan());
        exportUseCase.writeCsv(response.getWriter());
        return modelViewForSchedule();
    }
    //==================================================================================================================
    // IMPORT COMMAND QUERY MASTER PLAN
    //==================================================================================================================
    @PostMapping("/import-schedule")
    public ModelAndView importMasterScheduleAndView(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        try {
            importUseCase.setMultipartFile(file);
            importUseCase.importDataPrep();
            importUseCase.saveToDatabase();
            return modelViewForSchedule();
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal import");
        }
    }
}
