package len.silvue.webpendukung.web.controllers;

import com.opencsv.CSVWriter;
import len.silvue.webpendukung.actual.adapter.in.*;
import len.silvue.webpendukung.actual.adapter.in.web.DeleteActualScheduleCommand;
import len.silvue.webpendukung.actual.adapter.in.web.DeleteActualSpesificCommand;
import len.silvue.webpendukung.actual.adapter.in.web.ModifyActualCommand;
import len.silvue.webpendukung.actual.adapter.out.web.ActualModifyDto;
import len.silvue.webpendukung.actual.adapter.out.web.ActualRunningScheduleDto;
import len.silvue.webpendukung.actual.application.port.in.*;
import len.silvue.webpendukung.schedule.application.port.in.ExportUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/actual")
@RequiredArgsConstructor
@Slf4j
public class ActualScheduleController {

    private final ActualRunningScheduleUseCase actualRunningScheduleUseCase;
    private final DeleteActualScheduleUseCase deleteActualScheduleUseCase;
    private final ModifyActualTypeUseCase modifyActualTypeUseCase;
    private final FindDepartementUseCase findDepartementUseCase;
    private final FindProblemUseCase findProblemUseCase;
    private final ModifyProblemLogUseCase modifyProblemLogUseCase;
    private final ExportUseCase exportUseCase;
    private final ImportActualUseCase importUseCase;
    private final CombineActualScheduleUseCase combineActualCommand;


    @GetMapping("/actualschedule")
    private ModelAndView viewPageActualSchedule() {
        try {
            return modelViewForActualSchedule();
        } catch(Exception e) {
            throw new RuntimeException("Error load actual plan main page");
        }
    }

    // GET DATA FOR INSERT ACTUAL PLAN
    //==================================================================================================================
    private ModelAndView modelAndViewActualSchedule() throws Exception {
        try {
            ModelAndView mav = new ModelAndView();
            mav.addObject("departements", findDepartementUseCase.getAllDepartement());
            mav.addObject("problems", findProblemUseCase.getAllProblem());
            return mav;
        } catch(Exception e) {
            throw new Exception("failed to get data actual plan", e);
        }
    }

    //==================================================================================================================
    // VIEW ALL DATA TO ACTUAL PLAN
    //==================================================================================================================
    private ModelAndView modelViewForActualSchedule() throws Exception {
        try {
            ModelAndView mav = new ModelAndView("actual/actualschedule");
            List<ActualRunningScheduleDto> actualScheduleDtoList = actualRunningScheduleUseCase.getAllActualByMaxIndex();
            List<Integer> actualIndexDtoList = actualRunningScheduleUseCase.getindexActual();
            int maxIndex = actualIndexDtoList.stream()
                    .mapToInt(v -> v)
                    .max()
                    .orElse(0);
            mav.addObject("getactualSchedules", actualScheduleDtoList);
            mav.addObject("indexActuals" , actualIndexDtoList);
            mav.addObject("indexs", maxIndex);
            return mav;
        } catch(Exception e) {
            throw new Exception(e);
        }
    }

    //==================================================================================================================
    // VIEW EXPORT ACTUAL
    //==================================================================================================================
    @GetMapping("/view-export-actualplan")
    private ModelAndView viewExportActual() {
        try {
            ModelAndView mav = new ModelAndView("actual/view-export-actualplan");
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("failed to get export actual",e);
        }
    }
    //==================================================================================================================
    // VIEW ACTUAL DATE
    //==================================================================================================================
    @GetMapping("/view-select-actual")
    private ModelAndView viewSelectActual() {
        try {
            ModelAndView mav = new ModelAndView("actual/view-select-actual");
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("failed to select actual");
        }
    }

    //==================================================================================================================
    // VIEW SELECT SPESIFIC DATE
    //==================================================================================================================
    @GetMapping("/view-spesific-date")
    public ModelAndView viewSpesificDate() {
        try {
            ModelAndView mav = new ModelAndView("actual/view-delete-spesific-date");
          //  List<ActualRunningScheduleDto> actualScheduleDtoList = actualRunningScheduleUseCase.getAllActualRunningSchedule();
           // mav.addObject("getSpesificDate", actualScheduleDtoList);
           // log.info("get All Data Actual Schedule :" + actualScheduleDtoList.size());
          //  log.info("typeMasterPlans", findTypeMasterPlanUseCase.getAllTypeMasterPlan());
            return mav;
        } catch (Exception e) {
            throw new RuntimeException("failed to view spesific date",e);
        }
    }

    //==================================================================================================================
    // DELETE ALL ACTUAL PLAN
    //==================================================================================================================
    @GetMapping("/delete-all-actualschedule/")
    public ModelAndView deleteAllActualSchedule(@ModelAttribute DeleteActualScheduleCommand deleteactualScheduleCommand) {
        try {
            deleteActualScheduleUseCase.deleteAllActualSchedule();
            viewPageActualSchedule();
            return new ModelAndView("redirect:/actual/actualschedule/" );
        }catch (Exception e) {
            throw new RuntimeException("failed to delete all actualplan",e);
        }
    }

    //==================================================================================================================
    // DELETE ONE DATA ACTUAL BY ID
    //==================================================================================================================
    @GetMapping("/delete-actualSchedule-by-idActualPlan/{id}")
    public ModelAndView deleteActualScheduleById(@PathVariable String id) {
        // ModelAndView mav =null;
        try {
            int idActualPlan = Integer.parseInt(id);
            ActualModifyDto actualRunningScheduleDto = deleteActualScheduleUseCase.deleteActualScheduleById(idActualPlan);
            return new ModelAndView("redirect:/actual/actualschedule/");
                   // +actualRunningScheduleDto.getIdActualPlan()+"/");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("failed to delete actual plan by id train",e);
        }
    }

    //==================================================================================================================
    // DELETE ALL SPESIFIC DATE
    //==================================================================================================================
    @PostMapping("/delete-all-actual-spesific-date")
    public ModelAndView deleteAllSpesificDate(@ModelAttribute DeleteActualSpesificCommand deleteActualSpesificCommand) {
        try {

            deleteActualScheduleUseCase.deleteAllActualScheduleSpesificDate(deleteActualSpesificCommand.getActualCode());
            viewPageActualSchedule();
            return new ModelAndView("redirect:/actual/actualschedule/" );
        }catch (Exception e) {
            throw new RuntimeException("failed to delete all actualplan",e);
        }
    }
    //==================================================================================================================
    // VIEW MODIFY ACTUAL PLAN BY ID
    //==================================================================================================================
    @GetMapping("/view-modify-actual-schedule/{id}")
    private ModelAndView viewModifyActualSchedule(@PathVariable String id) {
        try {
            ModelAndView mav = modelAndViewActualSchedule();
            mav.setViewName("actual/view-modify-actual-schedule");
            int idActualPlan = Integer.parseInt(id);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            mav.addObject(dateFormat);
            mav.addObject("getDetailActualSchedule",actualRunningScheduleUseCase.getActualRunningScheduleById(idActualPlan));
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("failed to get modify actual plan",e);
        }
    }

    //==================================================================================================================
    // MODIFY DETAIL ACTUAL SCHEDULE
    //==================================================================================================================
    @PostMapping("/modify-detail-actual-schedule")
    public ModelAndView modifyActualDetail(@ModelAttribute ModifyActualCommand modifyActualCommand) {
        try {
            //ActualRunningScheduleDto actualRunningScheduleDtoList =
                    modifyActualTypeUseCase.modifyActualScheduleType(modifyActualCommand);
            return new ModelAndView("redirect:/actual/actualschedule/");
                  //  +actualRunningScheduleDtoList.getIdActualPlan();
        }catch (Exception e) {
            throw new RuntimeException("failed to modify actual plan",e);
        }
    }

    //==================================================================================================================
    // MODIFY ALL ACTUAL PLAN
    //==================================================================================================================
    @PostMapping("/modify-all-actualPlan")
    public ModelAndView modifyAllActualPlan(@ModelAttribute ModifyActualCommand modifyActualCommand) {
        try {
            List<ActualRunningScheduleDto> actualRunningScheduleDtoList =
                    modifyActualTypeUseCase.modifyAllActualScheduleType(modifyActualCommand);
            return new ModelAndView("redirect:/actual/actualschedule/");
        }catch (Exception e) {
            throw new RuntimeException("failed to modify all train",e);
        }
    }

    //==================================================================================================================
    // VIEW MODIFY PROBLEM LOG
    //==================================================================================================================
    @GetMapping("/view-modify-problem-log/{id}")
    private ModelAndView viewModifyProblemLog(@PathVariable String id) {
        try {
            ModelAndView mav = modelAndViewActualSchedule();
            mav.setViewName("actual/view-modify-problem-log");
            int idActualPlan = Integer.parseInt(id);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            mav.addObject(dateFormat);
            mav.addObject("getDetailProblemLog",actualRunningScheduleUseCase.getActualRunningScheduleById(idActualPlan));
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("failed to get modify actual plan",e);
        }
    }

    //==================================================================================================================
    // MODIFY DETAIL PROBLEM LOG
    //==================================================================================================================
    @PostMapping("/modify-detail-problem-log")
    public ModelAndView modifyProblem(@ModelAttribute ModifyActualCommand modifyActualCommand) {
        try {
            //ActualRunningScheduleDto actualRunningScheduleDtoList =
            modifyProblemLogUseCase.modifyProblemLogType(modifyActualCommand);
            return new ModelAndView("redirect:/actual/actualschedule/");
            //  +actualRunningScheduleDtoList.getIdActualPlan();
        }catch (Exception e) {
            throw new RuntimeException("failed to modify problem log",e);
        }
    }

    //==================================================================================================================
    // VIEW MODIFY ALL TRAIN
    //==================================================================================================================
    @GetMapping("/view-modify-all-train/{id}")
    private ModelAndView viewModifyAllTrain(@PathVariable String id) {
        try {
            ModelAndView mav = modelAndViewActualSchedule();
            mav.setViewName("actual/view-modify-all-train");
            int idActualPlans = Integer.parseInt(id);
            mav.addObject("getDetailActualSchedules",actualRunningScheduleUseCase.getActualRunningScheduleById(idActualPlans));
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("failed to view all train",e);
        }
    }

    @PostMapping("/exportActual")
    public ModelAndView exportActualCSV(HttpServletResponse response,
                                        @ModelAttribute ExportActualCommand exportActualCommand, BindingResult bindingResult) throws Exception {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("HH-mm-ss dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());
        // format Write ALl CSV
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=TS1000-Export-Actual From "+
                exportActualCommand.getFromActualCode()+" To "+
                exportActualCommand.getToActualCode()+".csv";
        response.setHeader(headerKey, headerValue);
        CSVWriter csvWriter = new CSVWriter(response.getWriter());
        // HEADER
        csvWriter.writeNext(new String[]{"Train","Route","Type Schedule","Station","Arrival","Actual Arrival","Depart","Actual Depart","Actual Delay"});
        // DETAIL
        List<ActualRunningScheduleDto> listActualPlanDtoList = actualRunningScheduleUseCase.getAllActualRunningByActualCode(exportActualCommand.getFromActualCode(),exportActualCommand.getToActualCode());
        String[] dataNextLine = new String[2+listActualPlanDtoList.size()*4];
        for(ActualRunningScheduleDto listActualPlanDetailDto: listActualPlanDtoList)
        {
            int incVar=0;
            dataNextLine[incVar++] = listActualPlanDetailDto.getTrainActualPlan();
            dataNextLine[incVar++] = listActualPlanDetailDto.getRuteRoleActualPlan();
            dataNextLine[incVar++] = listActualPlanDetailDto.getTypePlan();
            dataNextLine[incVar++] = listActualPlanDetailDto.getStatiunActualPlan();
            dataNextLine[incVar++] = String.valueOf(listActualPlanDetailDto.getArriveSchedule());
            dataNextLine[incVar++] = String.valueOf(listActualPlanDetailDto.getArriveActualPlan());
            dataNextLine[incVar++] = String.valueOf(listActualPlanDetailDto.getDepartSchedule());
            dataNextLine[incVar++] = String.valueOf(listActualPlanDetailDto.getDepartActualPlan());
            dataNextLine[incVar++] = String.valueOf(listActualPlanDetailDto.getDelayActualPlan());

            csvWriter.writeNext(dataNextLine);
        }
        return null;
    }

    //==================================================================================================================
    // VIEW SPESIFIC DATE
    //==================================================================================================================
    @GetMapping("/select-actual")
    public ModelAndView selectActualPlan(@ModelAttribute SelectActualCommand selectActualCommand) {
        try {

            ModelAndView mav = new ModelAndView("actual/actualschedule");
            List<ActualRunningScheduleDto> actualScheduleDtoList = actualRunningScheduleUseCase.getActualPlanByActualCode(selectActualCommand.getActualCode());
            mav.addObject("getactualSchedules", actualScheduleDtoList);
            log.info("get All Data Actual Schedule :" + actualScheduleDtoList.size());
            return mav;
        }catch (Exception e) {
            throw new RuntimeException("failed to select actualplan",e);
        }
    }

   /* //==================================================================================================================
    // VIEW INDEX ACTUAL
    //==================================================================================================================
    @GetMapping("/index-actual")
    public ModelAndView indexActual(@ModelAttribute SelectIndexCommand selectIndexCommand) {
        try {

            ModelAndView mav = new ModelAndView("actual/actualschedule");
            List<ActualRunningScheduleDto> actualScheduleDtoList = actualRunningScheduleUseCase.getindexActual(selectIndexCommand.getIndexActual());
            mav.addObject("getactualSchedules", actualScheduleDtoList);
            log.info("get All Data Actual Schedule :" + actualScheduleDtoList.size());
            return mav;
        }catch (Exception e) {
            throw new RuntimeException("failed to select select index actualplan",e);
        }
    }
*/
    // SHOW ADD DATA IMPORT
    //==================================================================================================================
    @GetMapping("/view-import-actual-schedule")
    private ModelAndView viewImportActualSdheule() {
        try {
            ModelAndView mav = new ModelAndView("actual/view-import-actual-schedule");
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("Error load import actual schedule page",e);
        }
    }

    /*//==================================================================================================================
    // IMPORT COMMAND QUERY MASTER PLAN
    //==================================================================================================================*/

    @PostMapping("/import-actual-schedule")
    public ModelAndView importMasterScheduleAndView(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        try {
            importUseCase.setMultipartFile(file);
            importUseCase.importData();
            importUseCase.saveToDatabase();
            return new ModelAndView("redirect:/actual/actualschedule/");
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal import");
        }
    }

    //==================================================================================================================
    // COMBINE SCHEDULE COMMAND
    //==================================================================================================================
    @GetMapping("/combine-index-actual")
    public ModelAndView combineActualScheduleCommand(@ModelAttribute CombineActualPlanCommand combineIndexActualPlanCommand, BindingResult bindingResult) {
        try {
            combineActualCommand.combineActualByIndex(combineIndexActualPlanCommand);
            log.info(combineIndexActualPlanCommand.getIndexActual());
            return new ModelAndView("redirect:/actual/actualschedule/");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("failed to combine actual schedule by id type schedule",e);
        }
    }
    //==================================================================================================================
    // VIEW SELECTED INDEX
    //==================================================================================================================
    @GetMapping("/index/{id}")
    private ModelAndView viewSelectedIndex(@PathVariable String id) {
        try {
            ModelAndView mav = modelAndViewActualSchedule();
            mav.setViewName("actual/actualschedule");
            int idIndex = Integer.parseInt(id);
            log.info("xx"+idIndex);
            List<Integer> actualIndexDtoList = actualRunningScheduleUseCase.getindexActual();
            List<ActualRunningScheduleDto> actualRunningScheduleDtoList = actualRunningScheduleUseCase.getAllActualRunningScheduleByIndex(idIndex);
            mav.addObject("getactualSchedules",actualRunningScheduleDtoList);
            mav.addObject("indexActuals" , actualIndexDtoList);
            mav.addObject("indexs",idIndex);
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("failed to view all index",e);
        }
    }

    //==================================================================================================================
    // DELETE ONE DATA PROBLEM LOG BY ID
    //==================================================================================================================
    @GetMapping("/delete-problemLog-by-idActualPlan/{id}")
    public ModelAndView deleteProblemLogById(@PathVariable String id) {
        // ModelAndView mav =null;
        try {
            int idActualPlan = Integer.parseInt(id);
            modifyProblemLogUseCase.modifyAllProblemLogType(idActualPlan);
            return new ModelAndView("redirect:/actual/actualschedule/");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("failed to delete actual plan by id train",e);
        }
    }

}
