package len.silvue.webpendukung.actual.application.port.out;

import len.silvue.webpendukung.domains.Problem;

import java.util.List;
import java.util.Optional;

public interface SaveProblemPort {
    void saveProblem(Problem problem) throws Exception;
    Optional<Problem> storeProblem(Problem problem) throws Exception;
    void  storeProblemList(List<Problem> problem) throws Exception;
}
