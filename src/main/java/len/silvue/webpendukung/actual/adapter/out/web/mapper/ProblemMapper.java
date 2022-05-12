package len.silvue.webpendukung.actual.adapter.out.web.mapper;

import len.silvue.webpendukung.actual.adapter.out.web.ProblemDto;
import len.silvue.webpendukung.domains.Problem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProblemMapper {
    ProblemMapper MAPPER = Mappers.getMapper(ProblemMapper.class);

    Problem toProblem(ProblemDto dto);
    ProblemDto toProblemDto(Problem problem);
    List<Problem> toProblemList(List<ProblemDto> problemDtos);
    List<ProblemDto> toProblemDtoList(List<Problem> problems);
}
