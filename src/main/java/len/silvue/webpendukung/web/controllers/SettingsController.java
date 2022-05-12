package len.silvue.webpendukung.web.controllers;

import len.silvue.webpendukung.domains.Configuration;
import len.silvue.webpendukung.gapeka.adapter.in.web.AddListLineDetailCommand;
import len.silvue.webpendukung.gapeka.adapter.in.web.AddListRuteDetailCommand;
import len.silvue.webpendukung.gapeka.adapter.in.web.ModifyListLineDetailCommand;
import len.silvue.webpendukung.gapeka.adapter.in.web.ModifyListRuteDetailCommand;
import len.silvue.webpendukung.gapeka.adapter.out.web.LineDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.ListLineDetailDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.ListRuteDetailDto;
import len.silvue.webpendukung.gapeka.application.port.in.*;
import len.silvue.webpendukung.schedule.adapter.in.web.*;
import len.silvue.webpendukung.schedule.adapter.out.web.RuteRoleDto;
import len.silvue.webpendukung.setting.adapter.out.web.SettingListLineDetailDto;
import len.silvue.webpendukung.setting.adapter.out.web.SettingListRuteDetailDto;
import len.silvue.webpendukung.schedule.adapter.out.web.StationDto;
import len.silvue.webpendukung.schedule.application.port.in.*;
import len.silvue.webpendukung.setting.application.port.in.ImportSettingUseCase;
import len.silvue.webpendukung.tmconfig.adapter.in.web.ModifyConfigurationCommand;
import len.silvue.webpendukung.tmconfig.adapter.out.web.ConfigurationDto;
import len.silvue.webpendukung.tmconfig.application.port.in.FindConfigurationUseCase;
import len.silvue.webpendukung.tmconfig.application.port.in.FindSettingPrintUseCase;
import len.silvue.webpendukung.tmconfig.application.port.in.ModifyConfigurationUseCase;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/setting/settings")
@RequiredArgsConstructor
@Slf4j
public class SettingsController {
    private final FindTypeMasterPlanUseCase findTypeMasterPlanUseCase;
    private final AddScheduleTypeUseCase addScheduleTypeUseCase;
    private final ModifyScheduleTypeUseCase modifyScheduleTypeUseCase;
    private final DeleteScheduleTypeUseCase deleteScheduleTypeUseCase;
    private final AddScheduleUseCase addScheduleUseCase;
    private final FindTrainUseCase findTrainUseCase;
    private final FindStationUseCase findStationUseCase;
    private final FindColorTrainUseCase findColorTrainUseCase;
    private final ModifyColorTrainUseCase modifyTrainColorUseCase;
    private final FindLineUseCase findLineUseCase;
    private final FindRuteRoleUseCase findRuteRoleUseCase;
    private final FindListRuteDetailUseCase findListRuteDetailUseCase;
    private final FindListLineDetailUseCase findListLineDetailUseCase;
    private final AddListRouteDetailUseCase addListRouteUseCase;
    private final AddListLineDetailUseCase addListLineDetailUseCase;
    private final DeleteListRuteDetailUseCase deleteListRouteUseCase;
    private final DeleteListLineDetailUseCase deleteListLineDetailUseCase;
    private final ModifyListRouteUseCase modifyListRouteUseCase;
    private final ModifyListLineUseCase modifyListLineUseCase;
    private final ModifyConfigurationUseCase modifyConfigurationUseCase;
    private final FindConfigurationUseCase findConfigurationUseCase;
    private final FindSettingPrintUseCase findSettingPrintUseCase;
    private final ImportSettingUseCase importSettingUseCase;
    // main class
    @GetMapping
    private ModelAndView viewMainPageSetting() {
        try {
            return modelViewForScheduleSetting();
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal mengambil konfigurasi",e);
        }
    }
    ArrayList<Integer> arrayList = new ArrayList<>();
    ArrayList<String> arrayPlanBase = new ArrayList<>();
    ArrayList<Integer> arrayHourList = new ArrayList<>();
    // default get all type master plan
    public ModelAndView modelViewForScheduleSetting() throws Exception {
        try {
            ModelAndView mav = new ModelAndView("setting/settings");
            mav.addObject("typeMasterPlans", findTypeMasterPlanUseCase.getAllTypeMasterPlan());
            mav.addObject("colorTrains", findColorTrainUseCase.getAllTrainColor());
            mav.addObject("Trains", findTrainUseCase.getAllTrain());
            mav.addObject("Stations", findStationUseCase.getAllStation());
            ConfigurationDto configurationDto = findConfigurationUseCase.getConfiguration().orElseGet(ConfigurationDto::new);
            mav.addObject("configurationLists", configurationDto);
            mav.addObject("lineList", findListLineDetailUseCase.getAllListLineDetailByDistinctLine());
            mav.addObject("routeRoleList", findListRuteDetailUseCase.getAllListRuteDetailByDistinctRuteRole());
            mav.addObject("printSettingLists",findSettingPrintUseCase.getSettingPrint());

            arrayList.clear();
            arrayPlanBase.clear();
            arrayHourList.clear();
            arrayHourList.add(1);
            arrayHourList.add(4);
            arrayHourList.add(8);

            for(int x=1;x<25;x++)
            {
                arrayList.add(x);
            }
            arrayPlanBase.add("rute");
            arrayPlanBase.add("line");
            mav.addObject("arrayPlanBaseLists", arrayPlanBase);
            mav.addObject("arrayLists", arrayList);
            mav.addObject("arrayHourLists", arrayHourList);

            // set rute detail
            List<ListRuteDetailDto> ruteDetailList = findListRuteDetailUseCase.getAllListRuteDetail();
            int currentIdRuteRole = 0;
            List<SettingListRuteDetailDto> settingRuteDetailList = new ArrayList<>();
            SettingListRuteDetailDto settingListRuteDetailDto = null;
            for(ListRuteDetailDto listRuteDetailDto : ruteDetailList) {
                if(listRuteDetailDto.getStation() != null) {
                    if (listRuteDetailDto.getRuteRole().getIdRuteRole() != currentIdRuteRole) {
                        settingListRuteDetailDto = new SettingListRuteDetailDto();
                        settingRuteDetailList.add(settingListRuteDetailDto);
                        settingListRuteDetailDto.setIdRuteRole(listRuteDetailDto.getRuteRole().getIdRuteRole());
                        settingListRuteDetailDto.setNamaRuteRole(listRuteDetailDto.getRuteRole().getNameRoute());
                        settingListRuteDetailDto.setStations(listRuteDetailDto.getStation().getMnemonic());
                        currentIdRuteRole = listRuteDetailDto.getRuteRole().getIdRuteRole();
                    } else {
                        String station = settingListRuteDetailDto.getStations() + " > " +
                                listRuteDetailDto.getStation().getMnemonic();
                        settingListRuteDetailDto.setStations(station);
                    }
                }
            }
            mav.addObject("settingRouteDetailList", settingRuteDetailList);

            // set line detail
            List<ListLineDetailDto> listLineDetailDtoList = findListLineDetailUseCase.getAllListLineDetail();
            int currentIdLine = 0;
            List<SettingListLineDetailDto> settingLineDetailList = new ArrayList<>();
            SettingListLineDetailDto settingListLineDetailDto = null;
            for(ListLineDetailDto listLineDetailDto : listLineDetailDtoList) {
                if(listLineDetailDto.getLine().getIdLine() != currentIdLine) {
                    settingListLineDetailDto = new SettingListLineDetailDto();
                    settingLineDetailList.add(settingListLineDetailDto);
                    settingListLineDetailDto.setIdLine(listLineDetailDto.getLine().getIdLine());
                    settingListLineDetailDto.setNamaLine(listLineDetailDto.getLine().getNameLine());
                    settingListLineDetailDto.setStations(listLineDetailDto.getStation().getMnemonic());
                    currentIdLine = listLineDetailDto.getLine().getIdLine();
                }
                else {
                    String station = settingListLineDetailDto.getStations() + " > " +
                            listLineDetailDto.getStation().getMnemonic();
                    settingListLineDetailDto.setStations(station);
                }
            }
            mav.addObject("settingLineDetailList", settingLineDetailList);
            return mav;
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }
    // View For edit Modify Schedule Type Show The Modal
    @GetMapping("/view-modify-schedule-type/{id}")
    public ModelAndView viewModifyScheduleType(@PathVariable String id) {
        try {
            int typeMasterId = Integer.parseInt(id);
            ModelAndView mav = new ModelAndView("setting/view-modify-schedule-type");
            mav.addObject("typeMasterPlans", findTypeMasterPlanUseCase.getTypeMasterPlanById(typeMasterId));
            return mav;
        } catch (Exception e) {
            throw new RuntimeException("Gagal mengambil modify schedule type");
        }
    }
    //==================================================================================================================
    // SHOW MODIFY DETAIL ROUTE
    //==================================================================================================================
    @GetMapping("/view-modify-setting-route/{id}")
    public ModelAndView viewModifySettingRoute(@PathVariable String id) {
        try {
            int routeId = Integer.parseInt(id);
            ModelAndView mav = new ModelAndView("setting/view-modify-setting-route");
            List<ListRuteDetailDto> ruteDetailDtoList = findListRuteDetailUseCase.getAllListRuteDetailByRuteRoleId(routeId);
            List<StationDto> stationDtoList = findStationUseCase.getAllStationsNotInRouteDetail(routeId);
            ListRuteDetailDto ruteDetailDto = null;
            if(!ruteDetailDtoList.isEmpty())
            {
                ruteDetailDto=ruteDetailDtoList.get(0);
            }
            mav.addObject("routeRoleLists", ruteDetailDtoList);
            mav.addObject("ruteRoles",ruteDetailDto);
            mav.addObject("stations",stationDtoList);
            return mav;
        } catch (Exception e) {
            throw new RuntimeException("failed to get modify setting route",e);
        }
    }
    //==================================================================================================================
    // SHOW MODIFY DETAIL LINE
    //==================================================================================================================
    @GetMapping("/view-modify-setting-line/{id}")
    public ModelAndView viewModifySettingLine(@PathVariable String id) {
        try {
            int idLine = Integer.parseInt(id);
            ModelAndView mav = new ModelAndView("setting/view-modify-setting-line");
            List<ListLineDetailDto> listLineDetailDtoList = findListLineDetailUseCase.getAllListLineDetailByLineId(idLine);
            List<StationDto> stationDtoList = findStationUseCase.getAllStationsNotInLineDetail(idLine);
            ListLineDetailDto lineDetailDto = null;
            if(!listLineDetailDtoList.isEmpty())
            {
                lineDetailDto=listLineDetailDtoList.get(0);
            }
            mav.addObject("listDetailLines", listLineDetailDtoList);
            mav.addObject("lines",lineDetailDto);
            mav.addObject("stations",stationDtoList);
            return mav;
        } catch (Exception e) {
            throw new RuntimeException("failed to get modify setting line",e);
        }
    }
    //==================================================================================================================
    // SHOW MODIFY DATA LINE STATION DETAIL
    //==================================================================================================================
    @GetMapping("/view-modify-setting-line-detail/{id}")
    public ModelAndView viewModifyDataLineDetail(@PathVariable String id) {
        try {
            int idLine = Integer.parseInt(id);
            ModelAndView mav = new ModelAndView("setting/view-modify-setting-line-detail");
            ListLineDetailDto lineDetailDto = findListLineDetailUseCase.getListLineDetailById(idLine);
            ArrayList<String> arrayHourList = new ArrayList<>();

            arrayHourList.add("Meter");
            arrayHourList.add("Kilometer");
            mav.addObject("units",arrayHourList);
            mav.addObject("lines",lineDetailDto);
            mav.addObject("detailStation", findStationUseCase.getAllStationsNotInLineDetailById(lineDetailDto.getStation().getIdStation()));
            return mav;
        } catch (Exception e) {
            throw new RuntimeException("failed to get modify setting line detail ",e);
        }
    }
    //==================================================================================================================
    // SHOW MODIFY DATA ROUTE STATION DETAIL
    //==================================================================================================================
    @GetMapping("/view-modify-setting-route-detail/{id}")
    public ModelAndView viewModifyDataRouteDetail(@PathVariable String id) {
        try {
            int idRoute = Integer.parseInt(id);
            ModelAndView mav = new ModelAndView("setting/view-modify-setting-route-detail");
            ListRuteDetailDto ruteDetailDto = findListRuteDetailUseCase.getListRuteDetailById(idRoute);
            ArrayList<String> arrayHourList = new ArrayList<>();

            arrayHourList.add("Meter");
            arrayHourList.add("Kilometer");
            mav.addObject("units",arrayHourList);
            mav.addObject("ruteRoles",ruteDetailDto);
            mav.addObject("detailStation", findStationUseCase.getAllStationsNotInRouteDetailById(ruteDetailDto.getStation().getIdStation()));
            return mav;
        } catch (Exception e) {
            throw new RuntimeException("failed to get modify setting route",e);
        }
    }
    //==================================================================================================================
    // SHOW ADD ROUTE DETAIL COMMAND
    //==================================================================================================================
    @PostMapping("/add-route-detail")
    private ModelAndView addDataRouteDetail(@ModelAttribute AddListRuteDetailCommand addListRuteDetailCommand, BindingResult bindingResult) {
        ModelAndView mav=null;
        try {
             ListRuteDetailDto listRuteDetailDto = addListRouteUseCase.saveListRouteDetail(addListRuteDetailCommand);
             return new ModelAndView("redirect:/setting/settings/view-modify-setting-route/" + listRuteDetailDto.getRuteRole().getIdRuteRole());
            } catch(Exception e) {
                throw new RuntimeException("failed to save route detail",e);
            }
    }
    //==================================================================================================================
    // SHOW ADD LINE DETAIL COMMAND
    //==================================================================================================================
    @PostMapping("/add-line-detail")
    private ModelAndView addDataLineDetail(@ModelAttribute AddListLineDetailCommand addListLineDetailCommand, BindingResult bindingResult) {
        ModelAndView mav=null;
        try {
            ListLineDetailDto listLineDetailDto = addListLineDetailUseCase.saveListLineDetail(addListLineDetailCommand);
            return new ModelAndView("redirect:/setting/settings/view-modify-setting-line/" + listLineDetailDto.getLine().getIdLine());
        } catch(Exception e) {
            throw new RuntimeException("failed to save line detail",e);
        }
    }
    //==================================================================================================================
    // SHOW ADD DATA TYPE SCHEDULE
    //==================================================================================================================
    @GetMapping("/view-add-data-schedule-type")
    private ModelAndView viewPageAddDataSchedules() {
        try {
            ModelAndView mav = modelViewForScheduleSetting();
            mav.setViewName("setting/view-add-data-schedule-type");
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("Error load add schedule page",e);
        }
    }
    //==================================================================================================================
    // SHOW ADD DATA ROUTE RELATION
    //==================================================================================================================
    @GetMapping("/view-add-data-route-relation")
    private ModelAndView viewAddDataRouteRelation() {
        try {
            ModelAndView mav = modelViewForScheduleSetting();
            mav.setViewName("setting/view-add-data-route-relation");
            List<RuteRoleDto> listRuteDetailDtoList = findRuteRoleUseCase.getAllRuteRole();
            mav.addObject("ruteDetailList",listRuteDetailDtoList);
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("Error load add schedule page",e);
        }
    }
    //==================================================================================================================
    // SHOW ADD DATA LINE RELATION
    //==================================================================================================================
    @GetMapping("/view-add-data-line-relation")
    private ModelAndView viewAddDataLineRelation() {
        try {
            ModelAndView mav = modelViewForScheduleSetting();
            mav.setViewName("setting/view-add-data-line-relation");
            List<LineDto> lineDtoList = findLineUseCase.getAllLine();
            mav.addObject("lineDetailList",lineDtoList);
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("Error load add schedule page",e);
        }
    }
    //==================================================================================================================
    // SHOW ADD DATA LINE RELATION
    //==================================================================================================================
    @GetMapping("/view-import-line-route")
    private ModelAndView viewImportLineRoute() {
        try {
            ModelAndView mav = modelViewForScheduleSetting();
            mav.setViewName("setting/view-import-line-route");
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("Error load add schedule page",e);
        }
    }
    // add data schedule type
    @PostMapping("/add-schedule-type")
    public ModelAndView addScheduleType(@ModelAttribute AddScheduleTypeCommand addScheduleTypeCommand, BindingResult bindingResult) {
        try {
            addScheduleTypeUseCase.savaScheduleType(addScheduleTypeCommand);
            return modelViewForScheduleSetting();
        } catch(Exception e) {
            throw new RuntimeException("Gagal menyimpan schedule type");
        }
    }
    // get data from configuration to view modify schedule
    @PostMapping("/modify-schedule-type")
    public ModelAndView modifyScheduleType(@ModelAttribute ModifyScheduleTypeCommand modifyScheduleTypeCommand) {
        try {
            modifyScheduleTypeUseCase.modifyScheduleType(modifyScheduleTypeCommand);
            return modelViewForScheduleSetting();
        }catch (Exception e) {
            throw new RuntimeException("Gagal mengambil modify schedule type command");
        }
    }
    // get data from configuration to view modify schedule
    @PostMapping("/modify-setting-route-detail")
    public ModelAndView modifyRuteListDetail(@ModelAttribute ModifyListRuteDetailCommand modifyListRuteDetailCommand) {
        try {
            ListRuteDetailDto listRuteDetailDto = modifyListRouteUseCase.modifyListRoute(modifyListRuteDetailCommand);
            return new ModelAndView("redirect:/setting/settings/view-modify-setting-route/" + listRuteDetailDto.getRuteRole().getIdRuteRole());
        }catch (Exception e) {
            throw new RuntimeException("Gagal mengambil modify setting route command",e);
        }
    }
    // get data from configuration to view modify schedule
    @PostMapping("/modify-setting-line-detail")
    public ModelAndView modifyLineListDetail(@ModelAttribute ModifyListLineDetailCommand modifyListLineDetailCommand) {
        try {
            ListLineDetailDto listLineDetailDto = modifyListLineUseCase.modifyListLine(modifyListLineDetailCommand);
            return new ModelAndView("redirect:/setting/settings/view-modify-setting-line/" + listLineDetailDto.getLine().getIdLine());
        }catch (Exception e) {
            throw new RuntimeException("Gagal mengambil modify setting line command",e);
        }
    }

    // delete schedule type from settings
    @GetMapping("/delete-schedule-type/{id}")
    public ModelAndView deleteScheduleType(@PathVariable String id) {
        try {
            int scheduleTypeId = Integer.parseInt(id);
            deleteScheduleTypeUseCase.deleteScheduleType(scheduleTypeId);
            return modelViewForScheduleSetting();
        } catch (Exception e) {
            throw new RuntimeException("Gagal menghapus schedule type");
        }
    }
    // delete route relation detail from settings
    @GetMapping("/delete-route-relation-detail/{id}")
    public ModelAndView deleteRouteRelationDetail(@PathVariable String id) {
        try {
            int idRoute = Integer.parseInt(id);
            ListRuteDetailDto listRuteDetailDto = deleteListRouteUseCase.deleteListRouteById(idRoute);
            return new ModelAndView("redirect:/setting/settings/view-modify-setting-route/" + listRuteDetailDto.getRuteRole().getIdRuteRole());
        } catch (Exception e) {
            throw new RuntimeException("failed to delete route relation",e);
        }
    }
    // delete route relation detail from settings
    @GetMapping("/delete-line-relation-detail/{id}")
    public ModelAndView deleteLineRelationDetail(@PathVariable String id) {
        try {
            int idLine = Integer.parseInt(id);
            ListLineDetailDto listLineDetailDto = deleteListLineDetailUseCase.deleteListLineById(idLine);
            return new ModelAndView("redirect:/setting/settings/view-modify-setting-line/" + listLineDetailDto.getLine().getIdLine());
        } catch (Exception e) {
            throw new RuntimeException("failed to delete line relation",e);
        }
    }
    // delete route relation from settings
    @GetMapping("/delete-route-relation/{id}")
    public ModelAndView deleteRouteRelationByRoute(@PathVariable String id) {
        try {
            int idRoute = Integer.parseInt(id);
            deleteListRouteUseCase.deleteAllListRouteById(idRoute);
            return new ModelAndView("redirect:/setting/settings");
        } catch (Exception e) {
            throw new RuntimeException("failed to delete route relation",e);
        }
    }
    // delete route relation from settings
    @GetMapping("/delete-line-relation/{id}")
    public ModelAndView deleteLineRelationByRoute(@PathVariable String id) {
        try {
            int idLine = Integer.parseInt(id);
            deleteListLineDetailUseCase.deleteAllListLineById(idLine);
            return new ModelAndView("redirect:/setting/settings");
        } catch (Exception e) {
            throw new RuntimeException("failed to delete Line relation",e);
        }
    }
    // add schedule route
    @PostMapping("/add-schedule-route")
    private ModelAndView addScheduleRoute(@ModelAttribute AddScheduleRouteCommand addScheduleRouteCommand, BindingResult bindingResult) {
        ModelAndView modelAndView = null;
        try {
            addScheduleUseCase.saveScheduleRoute(addScheduleRouteCommand);
            modelAndView = new ModelAndView("redirect:/settings/view-add-schedule-rute/" + addScheduleRouteCommand.getScheduleId());
        } catch(DataNotFoundException dfe) {

        } catch(Exception e) {
            throw new RuntimeException("Gagal add schedule route");
        }
        return modelAndView;
    }

    // get data from configuration to view modify schedule
    @PostMapping("/modify-color-train")
    public ModelAndView modifyColorTrain(@ModelAttribute ModifyColorTrainCommand modifyColorTrainCommand) {
        try {
            modifyTrainColorUseCase.modifyColorTrain(modifyColorTrainCommand);
            return modelViewForScheduleSetting();
        }catch (Exception e) {
            throw new RuntimeException("Gagal mengambil modify color train command");
        }
    }
    // View For edit Modify Color Train Type Show The Modal
    @GetMapping("/view-modify-color-train/{id}")
    public ModelAndView viewModifyColorTrain(@PathVariable String id) {
        try {
            int colorTrainId = Integer.parseInt(id);
            ModelAndView mav = new ModelAndView("setting/view-modify-color-train");
            mav.addObject("colorTrains", findColorTrainUseCase.getColorTrainById(colorTrainId));
            return mav;
        } catch (Exception e) {
            throw new RuntimeException("Gagal mengambil modify schedule type");
        }
    }
    //==================================================================================================================
    // CONFIGURATION DETAIL COMMAND
    //==================================================================================================================
    @PostMapping("/modify-auto-update")
    private ModelAndView updateConfiguration(@ModelAttribute ModifyConfigurationCommand modifyConfigurationCommand, BindingResult bindingResult) {
        try {
            modifyConfigurationUseCase.modifyUpdateActual(modifyConfigurationCommand);
            return new ModelAndView("redirect:/setting/settings");
        } catch(Exception e) {
            throw new RuntimeException("Gagal menyimpan auto update",e);
        }
    }//==================================================================================================================
    // IMPORT SETTING
    //==================================================================================================================
    @PostMapping("/import-setting")
    public ModelAndView importSetting(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        try {
            importSettingUseCase.setMultipartFile(file);
            importSettingUseCase.importData();
            return modelViewForScheduleSetting();
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal import",e);
        }
    }
}
