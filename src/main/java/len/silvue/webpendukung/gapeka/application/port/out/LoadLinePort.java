package len.silvue.webpendukung.gapeka.application.port.out;

import len.silvue.webpendukung.domains.Line;

import java.util.List;
import java.util.Optional;

public interface LoadLinePort {
    Optional<List<Line>> loadAllLine() throws Exception;
    Optional <Line> loadLineById(int idLine) throws Exception;
}
