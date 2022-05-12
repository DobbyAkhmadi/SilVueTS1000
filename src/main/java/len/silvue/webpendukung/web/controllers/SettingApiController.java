package len.silvue.webpendukung.web.controllers;

import com.opencsv.CSVWriter;
import len.silvue.webpendukung.gapeka.adapter.out.persistence.repositories.LineRepository;
import len.silvue.webpendukung.gapeka.adapter.out.persistence.repositories.ListLineDetailRepository;
import len.silvue.webpendukung.gapeka.adapter.out.persistence.repositories.ListRuteDetailRepository;
import len.silvue.webpendukung.gapeka.adapter.out.web.ListLineDetailDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.ListRuteDetailDto;
import len.silvue.webpendukung.gapeka.application.port.in.FindListLineDetailUseCase;
import len.silvue.webpendukung.gapeka.application.port.in.FindListRuteDetailUseCase;
import len.silvue.webpendukung.gapeka.application.port.out.LoadLinePort;
import len.silvue.webpendukung.domains.Line;
import len.silvue.webpendukung.domains.ListLineDetail;
import len.silvue.webpendukung.domains.ListRuteDetail;
import len.silvue.webpendukung.schedule.adapter.out.persistence.repositories.RuteRoleRepository;
import len.silvue.webpendukung.schedule.application.port.out.LoadRuteRolePort;
import len.silvue.webpendukung.schedule.application.port.out.LoadStationPort;
import len.silvue.webpendukung.domains.RuteRole;
import len.silvue.webpendukung.domains.Station;
import len.silvue.webpendukung.tmconfig.application.port.out.LoadConfigurationPort;
import len.silvue.webpendukung.tmconfig.application.port.out.LoadSettingPrintPort;
import len.silvue.webpendukung.tmconfig.application.port.out.SaveConfigurationPort;
import len.silvue.webpendukung.tmconfig.application.port.out.SaveSettingPrintPort;
import len.silvue.webpendukung.domains.Configuration;
import len.silvue.webpendukung.domains.SettingPrint;
import len.silvue.webpendukung.ars.application.port.out.SaveArsConflictPort;
import len.silvue.webpendukung.ars.application.port.out.LoadConflictArsPort;
import len.silvue.webpendukung.domains.ArsConflict;
import len.silvue.webpendukung.domains.ArsSchedule;
import len.silvue.webpendukung.domains.RouteStick;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/api/v1/ts1000/auto")
@RequiredArgsConstructor
@Slf4j
public class SettingApiController {
    private final SaveConfigurationPort saveConfigurationPort;
    private final LoadConfigurationPort loadConfigurationPort;
    private final LoadSettingPrintPort loadSettingPrintPort;
    private final SaveSettingPrintPort saveSettingPrintPort;
    private final SaveArsConflictPort saveArsConflictPort;
    private final LoadStationPort loadStationPort;
    private final LoadRuteRolePort loadRuteRolePort;
    private final LoadLinePort loadLinePort;
    private final LoadConflictArsPort loadConflictArsPort;
    private final RuteRoleRepository ruteRoleRepository;
    private final LineRepository lineRepository;
    private final ListRuteDetailRepository listRuteDetailRepository;
    private final ListLineDetailRepository listLineDetailRepository;
    private final FindListRuteDetailUseCase findListRuteDetailUseCase;
    private final FindListLineDetailUseCase findListLineDetailUseCase;
    @RequestMapping(value = "/export-Route")
    public void exportRouteCSV(HttpServletResponse response) throws Exception {
        response.setContentType("text/csv; charset=utf-8");
        DateFormat dateFormatter = new SimpleDateFormat("HH-mm-ss dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());
        // format Write ALl CSV
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=TS1000-Export-Setting-Route | " + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
        CSVWriter csvWriter = new CSVWriter(response.getWriter());
        // HEADER
        csvWriter.writeNext(new String[]{"Station","Relation Type","Relation Name","Unit","Location"});
        // DETAIL
        List<ListRuteDetailDto> listRuteDetailDtoList = findListRuteDetailUseCase.getAllListRuteDetail();
        String[] dataNextLine = new String[2+listRuteDetailDtoList.size()*4];
        for(ListRuteDetailDto listRuteDetailDto: listRuteDetailDtoList)
        {
            int incVar=0;
            dataNextLine[incVar++] = listRuteDetailDto.getStation().getNameStation();
            dataNextLine[incVar++] = "Route";
            dataNextLine[incVar++] = listRuteDetailDto.getRuteRole().getNameRoute();
            dataNextLine[incVar++] = listRuteDetailDto.getLocUnitRute();
            dataNextLine[incVar++] = String.valueOf(listRuteDetailDto.getIndexListRuteDetail());
            csvWriter.writeNext(dataNextLine);
        }
    }

    @RequestMapping(value = "/export-Line")
    public void exportLineCSV(HttpServletResponse response) throws Exception {
        response.setContentType("text/csv; charset=utf-8");
        DateFormat dateFormatter = new SimpleDateFormat("HH-mm-ss dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());
        // format Write ALl CSV
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=TS1000-Export-Setting-Line | " + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
        CSVWriter csvWriter = new CSVWriter(response.getWriter());
        // HEADER
        csvWriter.writeNext(new String[]{"Station","Relation Type","Relation Name","Unit","Location"});
        // DETAIL
        List<ListLineDetailDto> listLineDetailDtoList = findListLineDetailUseCase.getAllListLineDetail();
        String[] dataNextLine = new String[2+listLineDetailDtoList.size()*4];
        for(ListLineDetailDto listLineDetailDto: listLineDetailDtoList)
        {
            int incVar=0;
            dataNextLine[incVar++] = listLineDetailDto.getStation().getNameStation();
            dataNextLine[incVar++] = "Line";
            dataNextLine[incVar++] = listLineDetailDto.getLine().getNameLine();
            dataNextLine[incVar++] = listLineDetailDto.getLocUnitLine();
            dataNextLine[incVar++] = String.valueOf(listLineDetailDto.getIndexListLineDetail());
            csvWriter.writeNext(dataNextLine);
        }
    }
    @RequestMapping(value = "/import-All-Route-Line")
    public void importAllCSV (@RequestParam (value = "pathVar") String pathVariable)
    {


    }

    @PostMapping(value = "/insertRouteAuto")
    public void setArrayRouteAuto(@RequestParam(value = "ArrayRouteTrainId[]") List<Integer> ArrayTrainId,
                              @RequestParam(value = "ArrayRouteDirection[]") List<String> ArrayDirection,
                              @RequestParam(value = "ArrayRouteLocation[]") List<Integer> ArrayLocation)
    {
        try
        {
            List<String> stringList = new ArrayList<>();
            for (int i = 0; i < ArrayTrainId.size(); i++) {
                Optional<Station> optionalStation = loadStationPort.loadStationById(ArrayTrainId.get(i));
                Station station1 = optionalStation.orElseThrow(DataNotFoundException::new);
                log.info(station1.getMnemonic());
                stringList.add(station1.getMnemonic());
            }
            String [] strArray= stringList.stream().toArray(size -> new String[size]);
            var autoRuteName= Stream.of(strArray)
                   .collect(Collectors.joining("-"));

           RuteRole ruteRole = RuteRole.builder()
                    .nameRoute(autoRuteName)
                    .build();
            ruteRoleRepository.save(ruteRole);

            List<ListRuteDetail> listRuteDetailList = new ArrayList<>();
            for (int i = 0; i < ArrayTrainId.size(); i++)
            {
                Optional<Station> optionalStation = loadStationPort.loadStationById(ArrayTrainId.get(i));
                Station station1 = optionalStation.orElseThrow(DataNotFoundException::new);

                ListRuteDetail ruteRow  = new ListRuteDetail ();
                ruteRow.setStation(station1);
                ruteRow.setRuteRole(ruteRole);
                ruteRow.setLocUnitRute(ArrayDirection.get(i));
                ruteRow.setIndexListRuteDetail(ArrayLocation.get(i));
                listRuteDetailList.add(ruteRow);
            }
            listRuteDetailRepository.saveAll(listRuteDetailList);
        } catch (Exception e) {
            throw new RuntimeException("failed to save data route Detail API",e);
        }
    }

    @PostMapping(value = "/insertLineAuto")
    public void setArrayLineAuto(@RequestParam(value = "ArrayLineTrainId[]") List<Integer> arrayLineTrainId,
                                  @RequestParam(value = "ArrayLineDirection[]") List<String> arrayLineDirection,
                                  @RequestParam(value = "ArrayLineLocation[]") List<Integer> arrayLineLocation)
    {
        try
        {
            List<String> stringList = new ArrayList<>();
            for (int i = 0; i < arrayLineTrainId.size(); i++) {
                Optional<Station> optionalStation = loadStationPort.loadStationById(arrayLineTrainId.get(i));
                Station station1 = optionalStation.orElseThrow(DataNotFoundException::new);
                log.info(station1.getMnemonic());
                stringList.add(station1.getMnemonic());
            }
            String [] strArray= stringList.stream().toArray(size -> new String[size]);
            var autoLineName= Stream.of(strArray)
                    .collect(Collectors.joining("-"));

            Line lineVar = Line.builder()
                    .nameLine(autoLineName)
                    .build();
            lineRepository.save(lineVar);

            List<ListLineDetail> listLineDetailList = new ArrayList<>();
            for (int i = 0; i < arrayLineTrainId.size(); i++)
            {
                Optional<Station> optionalStation = loadStationPort.loadStationById(arrayLineTrainId.get(i));
                Station station1 = optionalStation.orElseThrow(DataNotFoundException::new);

                ListLineDetail lineRow  = new ListLineDetail ();
                lineRow.setStation(station1);
                lineRow.setLine(lineVar);
                lineRow.setLocUnitLine(arrayLineDirection.get(i));
                lineRow.setIndexListLineDetail(arrayLineLocation.get(i));
                listLineDetailList.add(lineRow);
            }
            listLineDetailRepository.saveAll(listLineDetailList);
        } catch (Exception e) {
            throw new RuntimeException("failed to save data line Detail API",e);
        }
    }

    @PostMapping(value = "/insertRouteManual")
    public void setArrayRouteManual(@RequestParam(value = "routeName") String routeName,
                              @RequestParam(value = "ArrayRouteTrainId[]") List<Integer> ArrayTrainId,
                              @RequestParam(value = "ArrayRouteDirection[]") List<String> ArrayDirection,
                              @RequestParam(value = "ArrayRouteLocation[]") List<Integer> ArrayLocation)
    {
        try
        {
            RuteRole ruteRole = RuteRole.builder()
                    .nameRoute(routeName)
                    .build();
            ruteRoleRepository.save(ruteRole);

            List<ListRuteDetail> listRuteDetailList = new ArrayList<>();
            for (int i = 0; i < ArrayTrainId.size(); i++)
            {
                log.info("Data " + ArrayTrainId.get(i));
                Optional<Station> optionalStation = loadStationPort.loadStationById(ArrayTrainId.get(i));
                Station station1 = optionalStation.orElseThrow(DataNotFoundException::new);

                ListRuteDetail ruteRow  = new ListRuteDetail ();
                ruteRow.setStation(station1);
                ruteRow.setRuteRole(ruteRole);
                ruteRow.setLocUnitRute(ArrayDirection.get(i));
                ruteRow.setIndexListRuteDetail(ArrayLocation.get(i));
                listRuteDetailList.add(ruteRow);
            }
            listRuteDetailRepository.saveAll(listRuteDetailList);
        } catch (Exception e) {
            throw new RuntimeException("failed to save data route Detail API",e);
       }
    }

    @PostMapping(value = "/insertRouteById")
    public void setArrayRouteById(@RequestParam(value = "routeId") Integer routeId,
                                    @RequestParam(value = "ArrayRouteTrainId[]") List<Integer> ArrayTrainId,
                                    @RequestParam(value = "ArrayRouteDirection[]") List<String> ArrayDirection,
                                    @RequestParam(value = "ArrayRouteLocation[]") List<Integer> ArrayLocation)
    {
        try
        {
            Optional<RuteRole> optionalRuteRole = loadRuteRolePort.loadRuteRoleById(routeId);
            RuteRole ruteRole1 = optionalRuteRole.orElseThrow(DataNotFoundException::new);
            List<ListRuteDetail> listRuteDetailList = new ArrayList<>();
            for (int i = 0; i < ArrayTrainId.size(); i++)
            {
                Optional<Station> optionalStation = loadStationPort.loadStationById(ArrayTrainId.get(i));
                Station station1 = optionalStation.orElseThrow(DataNotFoundException::new);

                ListRuteDetail ruteRow  = new ListRuteDetail ();
                ruteRow.setStation(station1);
                ruteRow.setRuteRole(ruteRole1);
                ruteRow.setLocUnitRute(ArrayDirection.get(i));
                ruteRow.setIndexListRuteDetail(ArrayLocation.get(i));
                listRuteDetailList.add(ruteRow);
            }
            listRuteDetailRepository.saveAll(listRuteDetailList);
        } catch (Exception e) {
            throw new RuntimeException("failed to save data route Detail By Id API",e);
        }
    }


    @PostMapping(value = "/insertLineById")
    public void setArrayLineById(@RequestParam(value = "lineId") Integer lineId,
                                  @RequestParam(value = "ArrayLineTrainId[]") List<Integer> ArrayTrainId,
                                  @RequestParam(value = "ArrayLineDirection[]") List<String> ArrayDirection,
                                  @RequestParam(value = "ArrayLineLocation[]") List<Integer> ArrayLocation)
    {
        try
        {
            Optional<Line> optionalLine = loadLinePort.loadLineById(lineId);
            Line line1 = optionalLine.orElseThrow(DataNotFoundException::new);
            List<ListLineDetail> listLineDetailList = new ArrayList<>();
            for (int i = 0; i < ArrayTrainId.size(); i++)
            {
                Optional<Station> optionalStation = loadStationPort.loadStationById(ArrayTrainId.get(i));
                Station station1 = optionalStation.orElseThrow(DataNotFoundException::new);

                ListLineDetail ruteRow  = new ListLineDetail();
                ruteRow.setStation(station1);
                ruteRow.setLine(line1);
                ruteRow.setLocUnitLine(ArrayDirection.get(i));
                ruteRow.setIndexListLineDetail(ArrayLocation.get(i));
                listLineDetailList.add(ruteRow);
            }
            listLineDetailRepository.saveAll(listLineDetailList);
        } catch (Exception e) {
            throw new RuntimeException("failed to save data Line Detail By Id API",e);
        }
    }

    @PostMapping(value = "/insertLineManual")
    public void setArrayLineManual(@RequestParam(value = "lineName") String lineName,
                                    @RequestParam(value = "ArrayLineTrainId[]") List<Integer> arrayLineTrainId,
                                    @RequestParam(value = "ArrayLineDirection[]") List<String> arrayLineDirection,
                                    @RequestParam(value = "ArrayLineLocation[]") List<Integer> arrayLineLocation)
    {
        try
        {
            Line lineVar = Line.builder()
                    .nameLine(lineName)
                    .build();
            lineRepository.save(lineVar);

            List<ListLineDetail> listLineDetailList = new ArrayList<>();
            for (int i = 0; i < arrayLineTrainId.size(); i++)
            {
                Optional<Station> optionalStation = loadStationPort.loadStationById(arrayLineTrainId.get(i));
                Station stationData = optionalStation.orElseThrow(DataNotFoundException::new);

                ListLineDetail lineRow  = new ListLineDetail ();
                lineRow.setStation(stationData);
                lineRow.setLine(lineVar);
                lineRow.setLocUnitLine(arrayLineDirection.get(i));
                lineRow.setIndexListLineDetail(arrayLineLocation.get(i));
                listLineDetailList.add(lineRow);
            }
            listLineDetailRepository.saveAll(listLineDetailList);
        } catch (Exception e) {
            throw new RuntimeException("failed to save data Line Detail API",e);
        }
    }

    @PostMapping("/autoUpdate")
    public ResponseEntity<Configuration> getAutoUpdate(@ModelAttribute Configuration configurationDtoList) throws Exception {
        try {
            Optional<Configuration> optionalConfiguration = loadConfigurationPort.loadDataConfiguration();
            Configuration configuration = optionalConfiguration.orElseThrow(DataNotFoundException::new);

            configuration.setAutoUpdateEnable(configurationDtoList.getAutoUpdateEnable());
            configuration.setAutoUpdateActual(configurationDtoList.getAutoUpdateActual());

            Configuration config = saveConfigurationPort.storeDataConfiguration(configuration);
            return ResponseEntity.ok().body(config);
        } catch(Exception e) {
            throw new RuntimeException("failed to get data update actual");
        }
    }

    @PostMapping("/updateBase")
    public ResponseEntity<Configuration> updatePlanBase(@ModelAttribute Configuration configurationDtoList) throws Exception {
        try {
            Optional<Configuration> optionalConfigurationDto = loadConfigurationPort.loadDataConfiguration();
            Configuration configuration = optionalConfigurationDto.orElseThrow(DataNotFoundException::new);

            configuration.setTdgBase(configurationDtoList.getTdgBase());

            Configuration config = saveConfigurationPort.storeDataConfiguration(configuration);
            return ResponseEntity.ok().body(config);
        } catch(Exception e) {
            log.debug(e.getMessage());
            throw new RuntimeException("failed to update Plan Base Line",e);
        }
    }

    @PostMapping("/updateLine")
    public ResponseEntity<Configuration> updatePlanBaseLine(@ModelAttribute Configuration configurationDtoList) throws Exception {
        try {
            Optional<Configuration> optionalConfigurationDto = loadConfigurationPort.loadDataConfiguration();
            Configuration configuration = optionalConfigurationDto.orElseThrow(DataNotFoundException::new);

            configuration.setTdgLine(configurationDtoList.getTdgLine());

            Configuration config = saveConfigurationPort.storeDataConfiguration(configuration);
            return ResponseEntity.ok().body(config);
        } catch(Exception e) {
            log.debug(e.getMessage());
            throw new RuntimeException("failed to update Plan Base Line",e);
        }
    }

    @PostMapping("/updateRute")
    public ResponseEntity<Configuration> updatePlanBaseRute(@ModelAttribute Configuration configurationDtoList) throws Exception {
        try {
            Optional<Configuration> optionalConfigurationDto = loadConfigurationPort.loadDataConfiguration();
            Configuration configuration = optionalConfigurationDto.orElseThrow(DataNotFoundException::new);

            configuration.setTdgRute(configurationDtoList.getTdgRute());

            Configuration config = saveConfigurationPort.storeDataConfiguration(configuration);
            return ResponseEntity.ok().body(config);
        } catch(Exception e) {
            log.debug(e.getMessage());
            throw new RuntimeException("failed to update Plan Base Line",e);
        }
    }

    @PostMapping("/updateEnable")
    public ResponseEntity<Configuration> updateEnableToogle(@ModelAttribute Configuration configurationDtoList) throws Exception {
        try {
            Optional<Configuration> optionalConfigurationDto = loadConfigurationPort.loadDataConfiguration();
            Configuration configuration = optionalConfigurationDto.orElseThrow(DataNotFoundException::new);

            configuration.setAutoUpdateEnable(configurationDtoList.getAutoUpdateEnable());

            Configuration config = saveConfigurationPort.storeDataConfiguration(configuration);
            return ResponseEntity.ok().body(config);
        } catch(Exception e) {
            log.debug(e.getMessage());
            throw new RuntimeException("failed to update enable toggle",e);
        }
    }

    @PostMapping("/updateRangeA")
    public ResponseEntity<Configuration> updateRangeA(@ModelAttribute Configuration configurationDtoList) throws Exception {
        try {
            Optional<Configuration> optionalConfigurationDto = loadConfigurationPort.loadDataConfiguration();
            Configuration configuration = optionalConfigurationDto.orElseThrow(DataNotFoundException::new);

            configuration.setTdgRangeALive(configurationDtoList.getTdgRangeALive());

            Configuration config = saveConfigurationPort.storeDataConfiguration(configuration);
            return ResponseEntity.ok().body(config);
        } catch(Exception e) {
            log.debug(e.getMessage());
            throw new RuntimeException("failed to update range A",e);
        }
    }

    @PostMapping("/updateRangeB")
    public ResponseEntity<Configuration> updateRangeB(@ModelAttribute Configuration configurationDtoList) throws Exception {
        try {
            Optional<Configuration> optionalConfigurationDto = loadConfigurationPort.loadDataConfiguration();
            Configuration configuration = optionalConfigurationDto.orElseThrow(DataNotFoundException::new);

            configuration.setTdgRangeBLive(configurationDtoList.getTdgRangeBLive());

            Configuration config = saveConfigurationPort.storeDataConfiguration(configuration);
            return ResponseEntity.ok().body(config);
        } catch(Exception e) {
            log.debug(e.getMessage());
            throw new RuntimeException("failed to update range B",e);
        }
    }

    @PostMapping("/updateIntervalPrintHours")
    public ResponseEntity<SettingPrint> updateIntervalPrintHours(@ModelAttribute SettingPrint settingPrintList) throws Exception {
        try {
            Optional<SettingPrint> optionalSettingPrint = loadSettingPrintPort.loadDataSettingPort();
            SettingPrint settingPrint = optionalSettingPrint.orElseThrow(DataNotFoundException::new);

            settingPrint.setPrintHours(settingPrintList.getPrintHours());

            SettingPrint storeDataConfiguration = saveSettingPrintPort.storeDataPrint(settingPrint);
            return ResponseEntity.ok().body(storeDataConfiguration);
        } catch(Exception e) {
            log.debug(e.getMessage());
            throw new RuntimeException("failed to update interval print hours",e);
        }
    }

    @PostMapping("/updateArsEnable")
    public ResponseEntity<Configuration> updateArsEnableToogle(@ModelAttribute Configuration configurationDtoList) throws Exception {
        try {
            Optional<Configuration> optionalConfigurationDto = loadConfigurationPort.loadDataConfiguration();
            Configuration configuration = optionalConfigurationDto.orElseThrow(DataNotFoundException::new);
            configuration.setArsStatusEnable(configurationDtoList.getArsStatusEnable());
            Configuration config = saveConfigurationPort.storeDataConfiguration(configuration);
            // System.out.println(configurationDtoList.getArsStatusEnable());
            return ResponseEntity.ok().body(config);
        } catch(Exception e) {
            log.debug(e.getMessage());
            throw new RuntimeException("failed to update enable toggle",e);
        }
    }

//    @PostMapping("/updateArsBtn")
//    public ResponseEntity<SystemStatus> updateArsBtn(@ModelAttribute SystemStatus systemStatusDtoList) throws Exception {
//        try {
//            System.out.println(systemStatusDtoList.getLastUpdateArs());
//            Optional<SystemStatus> optionalSystemStatusDto = loadSystemStatusPort.loadSystemStatus();
//            SystemStatus systemStatus = optionalSystemStatusDto.orElseThrow(DataNotFoundException::new);
//            systemStatus.setLastUpdateArs(systemStatusDtoList.getLastUpdateArs());
//            SystemStatus systemStat = saveSystemStatusPort.storeDataSystemStatus(systemStatus);
//            return ResponseEntity.ok().body(systemStat);
//        } catch(Exception e) {
//            log.debug(e.getMessage());
//            throw new RuntimeException("failed to update ars",e);
//        }
//    }

    @GetMapping(value = "/updateConflictArs")
    public ResponseEntity<String> setConflict(@RequestParam(value = "idArsScheduleNow") int idArsScheduleNow,
                            @RequestParam(value = "idArsScheduleNext") int idArsScheduleNext,
                            @RequestParam(value = "idRouteStick") int idRouteStick)
    {
        try
        {

            Optional<ArsSchedule> optionalArsSchedule1 = loadConflictArsPort.loadArsScheduleById(idArsScheduleNow);
            ArsSchedule arsSchedule1 = optionalArsSchedule1.orElseThrow();

            Optional<ArsSchedule> optionalArsSchedule2 = loadConflictArsPort.loadArsScheduleById(idArsScheduleNext);
            ArsSchedule arsSchedule2 = optionalArsSchedule2.orElseThrow();

            Optional<RouteStick>optionalRouteStick = loadConflictArsPort.loadRouteStickById(idRouteStick);
            RouteStick routeStick = optionalRouteStick.orElseThrow();

            ArsConflict arsConflict = new ArsConflict();
            arsConflict.setArsScheduleNow(arsSchedule1);
            arsConflict.setArsScheduleNext(arsSchedule2);
            arsConflict.setRouteStick(routeStick);

            saveArsConflictPort.storeDataConflictArs(arsConflict);
            return ResponseEntity.ok().body("check conflict success");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("check conflict failed");
        }
    }


    @GetMapping(value = "/enableArs")
    public void enableArs()
    {
        try
        {
            try(Socket clientSocket = new Socket("127.0.0.1", 12330)) {
                try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
                    bw.write("Web~Enable%");
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            throw new RuntimeException("failed to save data Line Detail API",e);
        }
    }
}


