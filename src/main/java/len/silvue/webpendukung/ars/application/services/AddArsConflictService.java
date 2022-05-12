package len.silvue.webpendukung.ars.application.services;


import len.silvue.webpendukung.ars.adapter.out.persistence.repositories.ConflictArsRepository;
import len.silvue.webpendukung.ars.adapter.out.web.ArsConflictDto;
import len.silvue.webpendukung.ars.application.port.in.AddArsConflictUseCase;
import len.silvue.webpendukung.domains.ArsConflict;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class AddArsConflictService implements AddArsConflictUseCase {
    private  final ConflictArsRepository conflictArsRepository;

    @Override
    public ArsConflictDto saveArsConflict(ArsConflict arsConflict) throws Exception {

        return null;
    }
}
