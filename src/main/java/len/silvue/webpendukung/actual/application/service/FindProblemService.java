package len.silvue.webpendukung.actual.application.service;


import len.silvue.webpendukung.actual.adapter.out.web.ProblemDto;
import len.silvue.webpendukung.actual.adapter.out.web.mapper.ProblemMapper;
import len.silvue.webpendukung.actual.application.port.in.FindProblemUseCase;
import len.silvue.webpendukung.actual.application.port.out.LoadProblemPort;
import len.silvue.webpendukung.domains.Problem;

import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindProblemService implements FindProblemUseCase {
    private final LoadProblemPort loadProblemPort;
    private final ProblemMapper problemMapper;


    @Override
    public List<ProblemDto> getAllProblem() throws Exception {
        try {
            Optional<List<Problem>> optionalProblems = loadProblemPort.loadAllProblem();
            return problemMapper.toProblemDtoList(optionalProblems.orElse(new ArrayList<>()));
        } catch(Exception e) {
            throw new Exception("Gagal mengeksekusi service ambil semua problem", e);
        }
    }

    @Override
    public ProblemDto getProblemById(int id) throws Exception {
        try {
            Optional<Problem> optionalProblem = loadProblemPort.loadProblemById(id);
            return problemMapper.toProblemDto(optionalProblem.orElseThrow(DataNotFoundException::new));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil data problem by id", e);
        }
    }
}
