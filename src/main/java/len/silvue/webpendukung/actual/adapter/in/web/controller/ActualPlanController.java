package len.silvue.webpendukung.actual.adapter.in.web.controller;

import len.silvue.webpendukung.actual.adapter.out.web.ParamActualPlanDto;
import len.silvue.webpendukung.actual.application.port.in.FindParamActualPlanUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/v1/ts1000/actual")
@RequiredArgsConstructor
public class ActualPlanController {
    private final FindParamActualPlanUseCase findParamActualPlanUseCase;

    @GetMapping("/param")
    public ResponseEntity<ParamActualPlanDto> getParamActualPlanFromActualDate(@RequestParam String actualCode) {
        try {
            ParamActualPlanDto paramActualPlanDto = findParamActualPlanUseCase.getParamByActualCode(actualCode);
            return ResponseEntity.ok().body(paramActualPlanDto);
        } catch(Exception e) {
            throw new RuntimeException("Gagal mengambil data param dari actualplan");
        }
    }

    @GetMapping("/all-param")
    public ResponseEntity<ParamActualPlanDto> getAllParamActualPlan() {
        try {
           ParamActualPlanDto paramActualPlanDto = findParamActualPlanUseCase.getAllParam();
           return ResponseEntity.ok().body(paramActualPlanDto);
        } catch(Exception e) {
            throw new RuntimeException("Gagal mengambil data semua param dari actualplan");
        }
    }
}
