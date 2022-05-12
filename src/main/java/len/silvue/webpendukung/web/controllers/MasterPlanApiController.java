package len.silvue.webpendukung.web.controllers;

import len.silvue.webpendukung.schedule.adapter.out.web.ConflictMasterPlanDto;
import len.silvue.webpendukung.schedule.adapter.out.web.PeronDto;
import len.silvue.webpendukung.schedule.adapter.out.web.StationDto;
import len.silvue.webpendukung.schedule.application.port.in.FindConflictTableMasterUseCase;
import len.silvue.webpendukung.schedule.application.port.in.FindPeronUseCase;
import len.silvue.webpendukung.schedule.application.port.in.FindStationUseCase;
import len.silvue.webpendukung.schedule.application.port.out.LoadSuccessBrowsePort;
import len.silvue.webpendukung.schedule.application.port.out.SaveSuccessBrowsePort;
import len.silvue.webpendukung.domains.SuccessBrowse;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/v1/ts1000")
@RequiredArgsConstructor
@Slf4j
public class MasterPlanApiController {
    private final FindPeronUseCase findPeronUseCase;
    private final FindStationUseCase findStationUseCase;
    private final FindConflictTableMasterUseCase findConflictTableMasterUseCase;
    private final LoadSuccessBrowsePort loadSuccessBrowsePort;
    private final SaveSuccessBrowsePort saveSuccessBrowsePort;
    @GetMapping("/peron/{idStation}")
    public ResponseEntity<List<PeronDto>> getPeronByStation(@PathVariable(name = "idStation") int idStation) {
        try {
            return ResponseEntity.ok().body(findPeronUseCase.getPeronListByStation(Integer.valueOf(idStation)));
        } catch(Exception e) {
            throw new RuntimeException("Gagal mengambil data peron",e);
        }
    }
    @GetMapping("/station/{idTrain}/{idTypeMasterPlan}/{idRuteRole}")
    public ResponseEntity<List<StationDto>> getStationByTrain(@PathVariable(name = "idTrain") int idTrain,
                                                              @PathVariable(name = "idTypeMasterPlan") int idTypeSchedule,
                                                              @PathVariable(name = "idRuteRole") int idRuteRole) {
        try {
             return ResponseEntity.ok().body(findStationUseCase.getAvailableStationMasterPlanByTrainAndTypeMasterPlanAndRuteRole(
                     Integer.valueOf(idTrain),
                     Integer.valueOf(idTypeSchedule),
                     Integer.valueOf(idRuteRole)));
        } catch(Exception e) {
            throw new RuntimeException("Gagal mengambil data combination",e);
        }
    }
    @GetMapping("/station")
    public ResponseEntity<List<StationDto>> getStationByFilter() throws Exception
    {
        try {
            List<StationDto> stationDtoList1 = findStationUseCase.getAllStation();
            return ResponseEntity.ok().body(stationDtoList1);
        } catch(Exception e) {
            throw new RuntimeException("Gagal mengambil data combination",e);
        }
    }

    @RequestMapping(value = "/checkConflict")
    public void checkConflict (@RequestParam (value = "typeSchedule") String typeSchedule)
    {
        try
        {
            //set default values
            Optional<SuccessBrowse> successBrowseOptional = loadSuccessBrowsePort.loadSuccessBrowse(1);
            SuccessBrowse successBrowse = successBrowseOptional.orElseThrow(DataNotFoundException::new);
            successBrowse.setSuccessNotification(0);
            saveSuccessBrowsePort.saveBrowse(successBrowse);
            //send value to ADA
            Socket socket = new Socket("localhost", 12331);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF("M~"+typeSchedule+"$ &");
            dataOutputStream.flush();
            dataOutputStream.close();
            socket.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping("/getAllConflict")
    public ResponseEntity<List<ConflictMasterPlanDto>> getConflictMasterPlan() {
        try {
            List<ConflictMasterPlanDto> conflictMasterPlanDtoList = findConflictTableMasterUseCase.loadAllConflict();
            return ResponseEntity.ok().body(conflictMasterPlanDtoList);
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal mengambil data check Conflict",e);
        }
    }

}
