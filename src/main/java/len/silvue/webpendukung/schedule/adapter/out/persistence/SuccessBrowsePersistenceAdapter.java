package len.silvue.webpendukung.schedule.adapter.out.persistence;

import len.silvue.webpendukung.schedule.adapter.out.persistence.repositories.SuccessBrowseRepository;
import len.silvue.webpendukung.schedule.application.port.out.LoadSuccessBrowsePort;
import len.silvue.webpendukung.schedule.application.port.out.SaveSuccessBrowsePort;
import len.silvue.webpendukung.domains.SuccessBrowse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class SuccessBrowsePersistenceAdapter implements LoadSuccessBrowsePort , SaveSuccessBrowsePort {
    private final SuccessBrowseRepository successBrowseRepository;
    @Override
    public Optional<SuccessBrowse> loadSuccessBrowse(int idStatus) throws Exception {
        try {
            return successBrowseRepository.findById(idStatus);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil browse id", e);
        }
    }

    @Override
    public SuccessBrowse saveBrowse(SuccessBrowse successBrowse) throws Exception {
        try {
            return successBrowseRepository.save(successBrowse);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil browse id", e);
        }
    }
}
