package len.silvue.webpendukung.ars.adapter.in.web.controller;

import len.silvue.webpendukung.ars.adapter.out.web.ArsScheduleDto;
import len.silvue.webpendukung.ars.application.port.in.ArsScheduleUseCase;
import len.silvue.webpendukung.ars.application.port.in.StatusArsUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ts1000/ars")
@Slf4j
public class ArsApiController {
    private final StatusArsUseCase statusArs;
    private final ArsScheduleUseCase arsScheduleUseCase;

    @PostMapping("/enable")
    public ResponseEntity<String> enableArs() {
        try {
            statusArs.enableArs();
            return ResponseEntity.ok().body("SUCCESS");
        } catch(Exception e) {
            log.error("Gagal enable ARS", e);
            return ResponseEntity.unprocessableEntity().body("FAILED");
        }
    }

    @PostMapping("/disable")
    public ResponseEntity<String> disableArs() {
        try {
           statusArs.disableArs();
           return ResponseEntity.ok().body("SUCCESS");
        } catch(Exception e) {
            log.error("Gagal disable ARS", e);
            return ResponseEntity.unprocessableEntity().body("FAILED");
        }
    }

    @GetMapping("/getalldata")
    public ResponseEntity<List<ArsScheduleDto>> getAllData() {
        try {
            List<ArsScheduleDto> arsSchedules = arsScheduleUseCase.getAllArsSchedule();
            return ResponseEntity.ok().body(arsSchedules);
        } catch(Exception e) {
            log.error("Gagal mengambil ars schedules", e);
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}
