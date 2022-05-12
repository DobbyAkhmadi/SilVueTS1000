package len.silvue.webpendukung.gapeka.adapter.out.persistence;

import len.silvue.webpendukung.gapeka.adapter.out.persistence.repositories.LineRepository;
import len.silvue.webpendukung.gapeka.application.port.out.LoadLinePort;
import len.silvue.webpendukung.gapeka.application.port.out.SaveLinePort;
import len.silvue.webpendukung.domains.Line;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LinePersistenceAdapter implements LoadLinePort, SaveLinePort {
    private final LineRepository lineRepository;

    @Override
    public Optional<List<Line>> loadAllLine() throws Exception {
        try {
            List<Line> lineList = lineRepository.findAll();
            return Optional.of(lineList);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil load all line", e);
        }
    }

    @Override
    public Optional<Line> loadLineById(int idLine) throws Exception {
        try {
            Optional<Line> lineList = lineRepository.findById(idLine);
            return lineList;
        } catch(Exception e) {
            throw new Exception("Gagal mengambil load all line", e);
        }
    }

    @Override
    public void saveLineAll(List<Line> lineList) throws Exception {
        try {
            lineRepository.saveAll(lineList);
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan banyak line", e);
        }
    }

    @Override
    public Line save(Line line) throws Exception {
        try {
            return lineRepository.save(line);
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan line", e);
        }
    }
}
