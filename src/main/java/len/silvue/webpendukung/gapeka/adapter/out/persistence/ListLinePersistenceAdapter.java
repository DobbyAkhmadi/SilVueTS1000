package len.silvue.webpendukung.gapeka.adapter.out.persistence;

import len.silvue.webpendukung.gapeka.adapter.out.persistence.repositories.ListLineRepository;
import len.silvue.webpendukung.gapeka.application.port.out.DeleteListLinePort;
import len.silvue.webpendukung.gapeka.application.port.out.LoadListLinePort;
import len.silvue.webpendukung.domains.ListLine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ListLinePersistenceAdapter implements LoadListLinePort, DeleteListLinePort {
    private final ListLineRepository listLineRepository;

    @Override
    public Optional<ListLine> loadListLineById(int listLineId) throws Exception {
        try {
            return listLineRepository.findById(listLineId);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil listline berdasarkan id", e);
        }
    }

    @Override
    public Optional<List<ListLine>> loadListLineByLineId(int lineId) throws Exception {
        try {
            List<ListLine> listLines = listLineRepository.findListLinesByLineId(lineId);
            return Optional.of(listLines);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil listline berdasarkan line id", e);
        }
    }

    @Override
    public void eraseAllListLine() throws Exception {
        try {
            listLineRepository.deleteAll();
        } catch(Exception e) {
            throw new Exception("Gagal menghapus list line", e);
        }
    }
}
