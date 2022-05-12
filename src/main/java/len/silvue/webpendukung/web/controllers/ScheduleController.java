package len.silvue.webpendukung.web.controllers;

import len.silvue.webpendukung.schedule.adapter.in.web.AddScheduleCommand;
import len.silvue.webpendukung.schedule.adapter.in.web.AddScheduleRouteCommand;
import len.silvue.webpendukung.schedule.adapter.in.web.DeleteScheduleCommand;
import len.silvue.webpendukung.schedule.adapter.in.web.ModifyScheduleCommand;
import len.silvue.webpendukung.schedule.adapter.out.web.*;
import len.silvue.webpendukung.schedule.application.port.in.*;
import len.silvue.webpendukung.domains.Schedule;
import len.silvue.webpendukung.utility.DataNotFoundException;
import len.silvue.webpendukung.utility.SaveScheduleFailedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/masterschedulse")
@RequiredArgsConstructor
@Slf4j
public class ScheduleController {
    private final FindStationUseCase findStationUseCase;
    private final FindRuteRoleUseCase findRuteRoleUseCase;
    private final AddScheduleUseCase addScheduleUseCase;
    private final FindMasterScheduleUseCase findMasterScheduleUseCase;
    private final FindTrainUseCase findTrainUseCase;
    private final FindTypeMasterPlanUseCase findTypeMasterPlanUseCase;
    private final FindRouteUseCase findRouteUseCase;
    private final FindPeronUseCase findPeronUseCase;
    private final ModifyRouteUseCase modifyRouteUseCase;
    private final ModifyScheduleUseCase modifyScheduleUseCase;
    private final DeleteMasterPlanUseCase deleteMasterPlanUseCase;
    private final ImportUseCase importUseCase;
    // view Default Main Page
    @GetMapping
    private ModelAndView viewMainPageSchedule() {
        try {
            return modelViewForSchedule();
        } catch(Exception e) {
            throw new RuntimeException("Error load schedule main page");
        }
    }
    //==================================================================================================================
    // MASTER SCHEDULE
    //==================================================================================================================

    // add data schedule command query
    @PostMapping("/add-schedule")
    private ModelAndView addScheduleAndViewSchedule(@ModelAttribute AddScheduleCommand addScheduleCommand, BindingResult bindingResult) {
        ModelAndView modelAndView = null;
        try {
            Schedule schedule = addScheduleUseCase.saveSchedule(addScheduleCommand);
            return new ModelAndView("redirect:" + "/masterschedule/view-add-schedule-rute/" + schedule.getScheduleId());
        } catch(SaveScheduleFailedException se) {
        } catch (Exception e) {
            throw new RuntimeException("Error add schedule", e);
        }
        return modelAndView;
    }
    // delete schedule by id
    @GetMapping("/delete-schedule/{id}")
    public ModelAndView deleteMasterPlan(@PathVariable String id) {
        try {
            int trainId = Integer.parseInt(id);
            deleteMasterPlanUseCase.deleteMasterPlanByTrainId(trainId);
            modelViewForSchedule();
            return new ModelAndView("redirect:/masterschedule/" );
        } catch (Exception e) {
            throw new RuntimeException("Gagal menghapus masterplan by id");
        }
    }
    // delete all schedule
    @GetMapping("/delete-all-schedule/")
    public ModelAndView deleteAllMasterPlan(@ModelAttribute DeleteScheduleCommand deleteScheduleCommand) {
        try {
            deleteMasterPlanUseCase.deleteAllMasterPlan();
            modelViewForSchedule();
            return new ModelAndView("redirect:/masterschedule/" );
        }catch (Exception e) {
            throw new RuntimeException("Gagal menghapus schedule command");
        }
    }
    // show model add data schedule
    @GetMapping("/view-add-data-schedule")
    private ModelAndView viewPageAddDataSchedule() {
        try {
            ModelAndView mav = modelAndViewForDataSchedule();
            mav.setViewName("view-add-data-schedule");
           return mav;
        } catch(Exception e) {
            throw new RuntimeException("Error load add schedule page");
        }
    }
    // get all data schedule including Train Routes Type MasterPlan
    private ModelAndView modelAndViewForDataSchedule() throws Exception {
        try {
            ModelAndView mav = new ModelAndView();
            mav.addObject("trains", findTrainUseCase.getAllTrain());
            mav.addObject("routeRoles", findRuteRoleUseCase.getAllRuteRole());
            mav.addObject("typeMasterPlans", findTypeMasterPlanUseCase.getAllTypeMasterPlan());
            return mav;
        } catch(Exception e) {
            throw new Exception("Gagal mengambil data view for data schedule", e);
        }
    }
    // parsing Schedule ID after add data schedule modal
    @GetMapping("/view-add-schedule-rute/{id}")
    private ModelAndView viewAddScheduleRoute(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView("view-add-schedule-rute");
        try {
            int scheduleId = Integer.parseInt(id);
            ScheduleDto scheduleDto = findMasterScheduleUseCase.getScheduleById(scheduleId);
         //   List<StationDto> stationDtoList = findStationUseCase.getAllStationsNotInRouteDetailById(scheduleId);
            mav.addObject("schedule", scheduleDto);
          //  mav.addObject("stationList", stationDtoList);
        } catch(DataNotFoundException de) {

        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error add schedule route");
        }
        return mav;
    }
    // view master schedule and get all MasterPlan
    private ModelAndView modelViewForSchedule() throws Exception {
        try {
            ModelAndView mav = new ModelAndView("masterschedule");
            List<MasterPlanDto> masterPlanDtoList = findMasterScheduleUseCase.getAllMasterPlan();
            mav.addObject("masterPlans", masterPlanDtoList);
            return mav;
        } catch(Exception e) {
            throw new Exception(e);
        }
    }

    //==================================================================================================================
    // SCHEDULE RUTE
    //==================================================================================================================

    // add data schedule route command query
    @PostMapping("/add-schedule-route")
    private ModelAndView addScheduleRoute(@ModelAttribute AddScheduleRouteCommand addScheduleRouteCommand, BindingResult bindingResult) {
        ModelAndView modelAndView = null;
        try {
            addScheduleUseCase.saveScheduleRoute(addScheduleRouteCommand);
            return new ModelAndView("redirect:/masterschedule/view-add-schedule-rute/" + addScheduleRouteCommand.getScheduleId());
        } catch(DataNotFoundException dfe) {
            log.error("error add schedule route", dfe);
        } catch(Exception e) {
            log.error("error add schedule route", e);
        }
        return modelAndView;
    }
    // modify schedule command query
    @PostMapping("/modify-schedule")
    public ModelAndView modifySchedule(@ModelAttribute ModifyScheduleCommand modifyScheduleCommand, BindingResult bindingResult) {
        try {
            ModelAndView modelAndView = new ModelAndView("redirect:/masterschedule/view-add-schedule-rute/" + modifyScheduleCommand.getScheduleId());
            modifyScheduleUseCase.modifySchedule(modifyScheduleCommand);
            return modelAndView;
        } catch(Exception e) {
            throw new RuntimeException("Gagal modify schedule");
        }
    }
//    // modify data schedule route command query
//    @PostMapping("/modify-schedule-route")
//    private ModelAndView modifyScheduleRoute(@ModelAttribute ModifyRouteCommand modifyRouteCommand, BindingResult bindingResult) {
//        try {
//            RouteDto routeDto = modifyRouteUseCase.modifyRoute(modifyRouteCommand);
//            return new ModelAndView("redirect:/masterschedule/view-add-schedule-rute/" + routeDto.getScheduleId());
//        } catch(Exception e) {
//            throw new RuntimeException("Gagal modify schedule");
//        }
//    }
    // view modify rute schedule after add
    @GetMapping("/view-modify-schedule-rute/{id}")
    private ModelAndView viewModifyScheduleRute(@PathVariable String id) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            int routeId = Integer.parseInt(id);
            RouteDto routeDto = findRouteUseCase.getRouteById(routeId);
            List<PeronDto> peronDtoList = findPeronUseCase.getPeronListByStation(routeDto.getStation().getIdStation());

            List<StationDto> stationDtoList = findStationUseCase.getAllStation();

            ModelAndView mv = new ModelAndView("view-modify-schedule-rute");
            mv.addObject("route", routeDto);
            mv.addObject("stationList", stationDtoList);
            mv.addObject("peronList", peronDtoList);
            mv.addObject("arrivalStr", simpleDateFormat.format(routeDto.getArrival()));
            mv.addObject("departStr", simpleDateFormat.format(routeDto.getDepart()));
            return mv;
        } catch(Exception e) {
            throw new RuntimeException("Gagal view modify schedule rute");
        }
    }
    // view modify schedule detail after add
    @GetMapping("/view-modify-schedule-detail/{id}")
    private ModelAndView viewModifySchedule(@PathVariable String id) {
        try {
            ModelAndView mav = modelAndViewForDataSchedule();
            mav.setViewName("view-modify-schedule-detail");
            int scheduleId = Integer.parseInt(id);
            ScheduleDto scheduleDto = findMasterScheduleUseCase.getScheduleById(scheduleId);
            mav.addObject("schedule", scheduleDto);
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("Gagal mengambil view modify schedule");
        }
    }

    //==================================================================================================================
    // MODIFY SCHEDULE RUTE
    //==================================================================================================================

    // modify schedule command query
    @PostMapping("/modify-schedule-data")
    public ModelAndView modifyScheduleData(@ModelAttribute ModifyScheduleCommand modifyScheduleCommand, BindingResult bindingResult) throws Exception {
           try {
            ModelAndView mav = new ModelAndView(
                    "redirect:/masterschedule/view-modify-data-schedule-rute/"
                    + modifyScheduleCommand.getScheduleId());
            modifyScheduleUseCase.modifySchedule(modifyScheduleCommand);
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("Gagal modify schedule");
        }
    }
    // view modify data modal schedule
    @GetMapping("/view-modify-data-schedule-rute/{id}")
    private ModelAndView viewModifyDataScheduleRute(@PathVariable("id") String id) throws Exception {
        ModelAndView mav = modelAndViewForDataSchedule();
        try {
            mav.setViewName("view-modify-data-schedule");
            int scheduleId = Integer.parseInt(id);
            ScheduleDto scheduleDto = findMasterScheduleUseCase.getScheduleById(scheduleId);
          //  List<StationDto> stationDtoList = findStationUseCase.getAvailableStationByTrain(scheduleId);
            mav.addObject("schedule", scheduleDto);
        //    mav.addObject("stationList", stationDtoList);
        } catch(DataNotFoundException de) {

        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error modify schedule data");
        }
        return mav;
    }
    // View For edit Modify Schedule Type Show The Modal
    @GetMapping("/view-export-schedule/")
    public ModelAndView viewExportSchedule() {
        try {
            ModelAndView mav = new ModelAndView("/view-export-schedule");
            mav.addObject("typeMasterPlans", findTypeMasterPlanUseCase.getAllTypeMasterPlan());
            return mav;
        } catch (Exception e) {
            throw new RuntimeException("Gagal mengambil export schedule type");
        }
    }
    // export data schedule method
    @PostMapping("/export-schedule")
    private ModelAndView exportSchedule() {
        try {
            // code export
            return new ModelAndView("redirect:/masterschedule/");
        } catch(Exception e) {
            throw new RuntimeException("Gagal modify schedule");
        }
    }
    // View For combine Schedule
    @GetMapping("/view-combine-schedule/")
    public ModelAndView viewCombineSchedule() {
        try {
            return  new ModelAndView("/view-combine-schedule");
        } catch (Exception e) {
            throw new RuntimeException("Gagal mengambil export schedule type");
        }
    }

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
