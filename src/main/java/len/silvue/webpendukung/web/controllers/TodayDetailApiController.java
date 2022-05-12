package len.silvue.webpendukung.web.controllers;

import len.silvue.webpendukung.todays.adapter.out.web.ConflictTodayDto;
import len.silvue.webpendukung.todays.application.port.in.FindConflictTableTodayUseCase;
import len.silvue.webpendukung.domains.SuccessBrowseConflict;
import len.silvue.webpendukung.todays.application.port.out.LoadSuccessPort;
import len.silvue.webpendukung.todays.application.port.out.SaveSuccessPort;
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
@RequestMapping(value="/api/v2/ts1000")
@RequiredArgsConstructor
@Slf4j
public class TodayDetailApiController {
    private final FindConflictTableTodayUseCase findConflictTableTodayUseCase;
    private final LoadSuccessPort LoadSucess;
    private final SaveSuccessPort SavePort;

    /*@GetMapping("/peron/{idStation}")
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
    }*/

    @RequestMapping(value = "/checkConflict")
    public void checkConflict (@RequestParam (value = "typeSchedule") String typeSchedule)
    {
        try
        {
            //set default values
            Optional<SuccessBrowseConflict> successBrowseOptional = LoadSucess.loadSuccessBrowse(1);
            SuccessBrowseConflict successBrowse = successBrowseOptional.orElseThrow(DataNotFoundException::new);
            successBrowse.setSuccessNotification(1);
            SavePort.saveBrowse(successBrowse);
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
    public ResponseEntity<List<ConflictTodayDto>> getConflictToday() {
        try {
            List<ConflictTodayDto> conflictTodayDtoList = findConflictTableTodayUseCase.loadAllConflict();
            return ResponseEntity.ok().body(conflictTodayDtoList);
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal mengambil data check Conflict",e);
        }
    }

}
