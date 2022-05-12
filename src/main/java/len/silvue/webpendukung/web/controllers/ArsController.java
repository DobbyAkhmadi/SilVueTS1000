package len.silvue.webpendukung.web.controllers;

import len.silvue.webpendukung.ars.adapter.in.web.DeleteArsScheduleCommand;
import len.silvue.webpendukung.ars.adapter.out.persistence.repositories.ArsScheduleRepository;
import len.silvue.webpendukung.ars.adapter.out.web.ArsConflictDto;
import len.silvue.webpendukung.ars.application.port.in.*;
import len.silvue.webpendukung.ars.adapter.out.web.ArsScheduleDto;
import len.silvue.webpendukung.tmconfig.adapter.out.web.ConfigurationDto;
import len.silvue.webpendukung.tmconfig.application.port.in.FindConfigurationUseCase;
import len.silvue.webpendukung.tmconfig.application.port.in.FindSystemStatusUseCase;
import len.silvue.webpendukung.tmconfig.application.port.out.LoadConfigurationPort;
import len.silvue.webpendukung.tmconfig.application.port.out.SaveConfigurationPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ars")
@RequiredArgsConstructor
@Slf4j
public class ArsController {
    private final ArsScheduleUseCase arsScheduleUseCase;
    private final DeleteArsScheduleUseCase deleteArsScheduleUseCase;
    private final FindConfigurationUseCase findConfigurationUseCase;
    private final FindSystemStatusUseCase findSystemStatus;
    private final AddArsDetailUseCase addArsDetailUseCase;
    private final FindArsConflictUseCase findArsConflictUseCase;

    @GetMapping
    public ModelAndView viewArsPage() {
        try {
            ModelAndView ars = new ModelAndView("ars/ars");
            List<ArsScheduleDto> ArsSchedules = arsScheduleUseCase.getAllArsSchedule();
            ars.addObject("ArsSchedule", ArsSchedules);
            Optional<ConfigurationDto> optionalConfigurationDto = findConfigurationUseCase.getConfiguration();
            ConfigurationDto configurationDto = optionalConfigurationDto.orElse(new ConfigurationDto());
            Boolean arsEnabled = false;
            String arsEnableStr = configurationDto.getArsStatusEnable();
            if(configurationDto.getArsStatusEnable() != null && arsEnableStr.compareTo("1") == 0)
                arsEnabled = true;
            ars.addObject("arsEnabled", arsEnabled);
            ars.addObject("systemstatusLists", findSystemStatus.getSystemStatus());
            return ars;
        } catch(Exception e){
            throw new RuntimeException("Gagal masuk view ARS", e);
        }
    }

    @GetMapping("/insert-schedule-command")
    public ModelAndView insertScheduleCommand() {
        try {
            addArsDetailUseCase.setArsScheduleFromActualPlan();
            return new ModelAndView("redirect:/ars");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("",e);
        }
    }

    // delete all schedule
    @GetMapping("/update-ars-data")
    public ModelAndView deleteAllArsShedule(@ModelAttribute DeleteArsScheduleCommand deleteArsScheduleCommand) {
        try {
            deleteArsScheduleUseCase.deleteAllArsSchedule();
            viewArsPage();
            return new ModelAndView("redirect:/ars" );
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal menghapus ars command",e);
        }
    }

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;


    @GetMapping("/send-ars-enable")
    public ModelAndView startConnectionEnable() throws IOException {
        try(Socket clientSocket = new Socket("192.168.100.100", 12330)) {
            try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
                bw.write("Web~Enable%&");
            } catch(Exception e) {
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/ars");
    }

    @GetMapping("/send-ars-disable")
    public ModelAndView startConnectionDisable() throws IOException {
        try{
            Socket clientSocket = new Socket("192.168.100.100", 12330);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.write("Web~Disable%&");
            out.close();
            clientSocket.close();

        }catch(Exception e){
            //throw new RuntimeException("Gagal mengirim data disable ars", e);
        }
        return new ModelAndView("redirect:/ars");
    }

    @PostMapping("/checkArsConflict")
    public ResponseEntity<List<ArsConflictDto>> getArsConflict() throws Exception
    {
        try {
            List<ArsConflictDto> arsConflictDtoList= findArsConflictUseCase.getAllArsConflict();
            return ResponseEntity.ok().body(arsConflictDtoList);
        } catch(Exception e) {
            throw new RuntimeException("Gagal mengambil data conflict ARS",e);
        }
    }
}
