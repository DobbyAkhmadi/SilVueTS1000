package len.silvue.webpendukung.todays.adapter.out.persistence;

import len.silvue.webpendukung.todays.adapter.out.persistence.repositories.SuccessRepository;
import len.silvue.webpendukung.todays.application.port.out.LoadSuccessPort;
import len.silvue.webpendukung.todays.application.port.out.SaveSuccessPort;
import len.silvue.webpendukung.domains.SuccessBrowseConflict;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class SuccessBrowseAdapter implements LoadSuccessPort , SaveSuccessPort {
    private final SuccessRepository successRepository;
    @Override
    public Optional<SuccessBrowseConflict> loadSuccessBrowse(int idStatus) throws Exception {
        try {
            return successRepository.findById(idStatus);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil today browse id", e);
        }
    }

    @Override
    public SuccessBrowseConflict saveBrowse(SuccessBrowseConflict successBrowse) throws Exception {
        try {
            return successRepository.save(successBrowse);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil today browse id", e);
        }
    }
}
