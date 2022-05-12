package len.silvue.webpendukung.gapeka.application.port.out;

import len.silvue.webpendukung.domains.Line;
import len.silvue.webpendukung.domains.ListLineDetail;

import java.util.List;
import java.util.Optional;

public interface LoadListLineDetailPort {
    Optional<List<ListLineDetail>> loadAllListLineDetail() throws Exception;
    Optional<List<ListLineDetail>> loadListLineDetailByLineId(int idLine) throws Exception;
    Optional<ListLineDetail> loadListLineDetailById (int idLine) throws Exception;
    Optional<List<Line>> loadAllListLineDetailByDistinctLine() throws Exception;
    Optional<List<ListLineDetail>> loadAllListByNameLine(String nameLine) throws Exception;
}
