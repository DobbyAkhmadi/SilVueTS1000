package len.silvue.webpendukung.schedule.adapter.out.web;

import len.silvue.webpendukung.schedule.application.port.in.FindMasterPlanUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/masterplan")
@RequiredArgsConstructor
public class TestMasterPlanApi {
    private final FindMasterPlanUseCase findMasterPlanUseCase;

    @GetMapping
    public ResponseEntity<List<MasterPlanDto>> findMasterPlanByTrainAndTypeMasterPlanService(@RequestParam(value = "typeMasterId") int typeMasterid,
                                                                                             @RequestParam(value = "trainId") int trainId) throws RuntimeException {
        try {
            List<MasterPlanDto> masterPlanDtoList = findMasterPlanUseCase.getMasterPlanByTrainIdAndTypeMasterPlanId(trainId, typeMasterid);
            return ResponseEntity.ok().body(masterPlanDtoList);
        } catch(Exception e) {
            throw new RuntimeException("Gagal mengambil service");
        }
    }
}
