package len.silvue.webpendukung.ars.application.services;

import len.silvue.webpendukung.ars.adapter.in.web.ModifyArsCommand;
import len.silvue.webpendukung.ars.adapter.out.web.ArsScheduleDto;
import len.silvue.webpendukung.ars.adapter.out.web.mapper.ArsScheduleMapper;
import len.silvue.webpendukung.ars.adapter.out.web.mapper.ConflictArsMapper;
import len.silvue.webpendukung.ars.application.port.in.ModifyArsUseCase;
import len.silvue.webpendukung.ars.application.port.out.LoadConflictArsPort;
import len.silvue.webpendukung.ars.application.port.out.SaveArsDetailPort;
import len.silvue.webpendukung.domains.ArsSchedule;
import len.silvue.webpendukung.domains.Peron;
import len.silvue.webpendukung.schedule.application.port.out.LoadPeronPort;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ModifyArsConflictService implements ModifyArsUseCase {
    private final LoadConflictArsPort loadConflictArsPort;
    private final LoadPeronPort loadPeronPort;
    private final SaveArsDetailPort saveArsDetailPort;
    private final ArsScheduleMapper arsScheduleMapper;
    private final ConflictArsMapper conflictArsMapper;

    @Override
    public ArsScheduleDto modifyArsConflict(ModifyArsCommand modifyArsCommand) throws Exception {
        try {
            Optional<Peron> optionalPeron =
                    loadPeronPort.loadPeronById(modifyArsCommand.getIdPeron());
            Peron peron = optionalPeron.orElseThrow(DataNotFoundException::new);
            Optional<ArsSchedule> optionalArsAtr =
                    loadConflictArsPort.loadArsScheduleById(modifyArsCommand.getIdArsSchedule());
            ArsSchedule arsSchedule = optionalArsAtr.orElseThrow(DataNotFoundException::new);

            arsSchedule.setPeronFromArs(peron);

            saveArsDetailPort.storeArsConflict(arsSchedule);
            feedbackToTDS(modifyArsCommand.getIdTrain(), modifyArsCommand.getIdRouteStick());
            return arsScheduleMapper.toArsScheduleDto(arsSchedule);
        } catch (Exception e) {
            throw new Exception("failed to modify ars", e);
        }
    }

    @Override
    public List<ArsScheduleDto> modifyArsConflictList(ModifyArsCommand modifyArsCommand) throws Exception {
        try {
            List<ArsSchedule> arsScheduleList = new ArrayList<>();
            Optional<List<ArsSchedule>> optionalArsAtr = loadConflictArsPort.loadArsScheduleByTrain(modifyArsCommand.getIdTrain());
            List<ArsSchedule> arsScheduleLists = optionalArsAtr.orElseThrow(DataNotFoundException::new);
            for (ArsSchedule arsLists : arsScheduleLists) {
                //  int addDelay = modifyArsCommand.getDelayArs();
                //  Date myTime = arsLists.getDepartArs();
                //   SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

                Calendar cal = Calendar.getInstance();
                cal.setTime(arsLists.getDepartArs());
                cal.add(Calendar.MINUTE, modifyArsCommand.getDelayArs());

                arsLists.setDepartArs(cal.getTime());
                arsScheduleList.add(arsLists);
            }
            List<ArsSchedule> arsScheduleDtoList = saveArsDetailPort.storeArsList(arsScheduleList);
            feedbackToTDS(modifyArsCommand.getIdTrain(), modifyArsCommand.getIdRouteStick());
            return arsScheduleMapper.toArsScheduleDtoList(arsScheduleDtoList);
        } catch (Exception e) {
            throw new Exception("failed to modify ars", e);
        }
    }

    @Override
    public void feedbackToTDS(int idTrain, int idRouteStick) throws Exception {
        try(Socket clientSocket = new Socket("localhost", 12330)) {
            try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
                bw.write("TDS~"+idTrain+"%"+idRouteStick+"&");
            } catch(Exception e) {
                log.error("Status send", e);
            }
        }catch(Exception e){
            log.error("Status send", e);
        }
    }

//    private void feedbackToTDS(int idTrain, int idArsSchedule) {
//        try(Socket clientSocket = new Socket("localhost", 12330)) {
//            try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
//                bw.write("TDS~"+idTrain+"%"+idArsSchedule+"&");
//            } catch(Exception e) {
//                log.error("Status send", e);
//            }
//        }catch(Exception e){
//            log.error("Status send", e);
//        }
//    }

}
