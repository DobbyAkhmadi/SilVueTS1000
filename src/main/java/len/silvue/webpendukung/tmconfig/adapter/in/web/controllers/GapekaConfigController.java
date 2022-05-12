package len.silvue.webpendukung.tmconfig.adapter.in.web.controllers;

import len.silvue.webpendukung.tmconfig.application.port.in.SaveGapekaConfigUseCase;
import len.silvue.webpendukung.utility.SaveGapekaConfigFailedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ts1000/setting")
@Slf4j
public class GapekaConfigController {
    private final SaveGapekaConfigUseCase saveGapekaConfigUseCase;

    @PostMapping
    public ResponseEntity<String> saveGapekaConfig() {
        try {
            saveGapekaConfigUseCase.store(null);
            return ResponseEntity.ok().body("berhasil");
        } catch(SaveGapekaConfigFailedException se) {
            log.error("Error: " + se.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
