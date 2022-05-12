package len.silvue.webpendukung.actual.adapter.out.persistence;


import len.silvue.webpendukung.actual.adapter.out.persistence.repositories.ProblemRepository;

import len.silvue.webpendukung.actual.application.port.out.LoadProblemPort;

import len.silvue.webpendukung.actual.application.port.out.SaveProblemPort;
import len.silvue.webpendukung.domains.Problem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProblemPersistenceAdapter implements LoadProblemPort, SaveProblemPort {
    private final ProblemRepository repository;


    @Override
    public Optional<List<Problem>> loadAllProblem() throws Exception {
        try {
            return Optional.of(repository.findAll());
        } catch(Exception e) {
            throw new Exception("Gagal mengambil semua data problem", e);
        }
    }

    @Override
    public Optional<Problem> loadProblemById(int idProblem) throws Exception {
        try {
            return repository.findById(idProblem);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil problem by id", e);
        }
    }

    @Override
    public void saveProblem(Problem problem) throws Exception {

    }

    @Override
    public Optional<Problem> storeProblem(Problem problem) throws Exception {
        try {
            Problem saveResultProblem = repository.save(problem);
            return Optional.of(saveResultProblem);
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan problem", e);
        }
    }

    @Override
    public void storeProblemList(List<Problem> problem) throws Exception {

    }
}
