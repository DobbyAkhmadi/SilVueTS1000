package len.silvue.webpendukung.actual.application.port.in;


import len.silvue.webpendukung.actual.adapter.out.web.ProblemDto;

import java.util.List;

public interface FindProblemUseCase {
    List<ProblemDto> getAllProblem() throws Exception;
    ProblemDto getProblemById(int id) throws Exception;
}
