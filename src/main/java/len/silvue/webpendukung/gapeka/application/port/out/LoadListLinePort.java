package len.silvue.webpendukung.gapeka.application.port.out;

import len.silvue.webpendukung.domains.ListLine;

import java.util.List;
import java.util.Optional;

public interface LoadListLinePort {
    Optional<ListLine> loadListLineById(int listLineId) throws Exception;
    Optional<List<ListLine>> loadListLineByLineId(int lineId) throws Exception;
}
