package len.silvue.webpendukung.schedule.adapter.in.web.controllers;

import len.silvue.webpendukung.schedule.adapter.out.web.ParamDto;
import len.silvue.webpendukung.schedule.application.port.in.FindParamMasterUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ts1000/schedule")
@RequiredArgsConstructor
@Slf4j
public class ScheduleApiController {
    private final FindParamMasterUseCase findParamMasterUseCase;

    @GetMapping("/param")
    public ResponseEntity<ParamDto> getAllParam() {
        try {
            ParamDto paramDto = findParamMasterUseCase.getAllParam();
            return ResponseEntity.ok().body(paramDto);
        } catch(Exception e) {
            throw new RuntimeException("Gagal mengambil param");
        }
    }
}
