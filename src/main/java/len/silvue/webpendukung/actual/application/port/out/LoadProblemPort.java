package len.silvue.webpendukung.actual.application.port.out;


import len.silvue.webpendukung.domains.Problem;

import java.util.List;
import java.util.Optional;

public interface LoadProblemPort {
    Optional<List<Problem>> loadAllProblem() throws Exception;
    Optional<Problem> loadProblemById(int idProblem) throws Exception;
}
