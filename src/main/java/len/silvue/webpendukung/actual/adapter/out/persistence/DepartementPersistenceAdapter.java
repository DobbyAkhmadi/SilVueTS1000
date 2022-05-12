package len.silvue.webpendukung.actual.adapter.out.persistence;


import len.silvue.webpendukung.actual.application.port.out.LoadDepartementPort;
import len.silvue.webpendukung.actual.application.port.out.SaveDepartementPort;
import len.silvue.webpendukung.gapeka.adapter.out.persistence.repositories.DepartementRepository;

import len.silvue.webpendukung.domains.Departement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DepartementPersistenceAdapter implements LoadDepartementPort, SaveDepartementPort {
    private final DepartementRepository repository;

    @Override
    public Optional<List<Departement>> loadAllDepartement() throws Exception {
        try {
            return Optional.of(repository.findAll());
        } catch(Exception e) {
            throw new Exception("Gagal mengambil semua data departement", e);
        }
    }

    @Override
    public Optional<Departement> loadDepartementById(int idDepartement) throws Exception {
        try {
            return repository.findById(idDepartement);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil departement by id", e);
        }
    }

    @Override
    public void saveDepartement(Departement departement) throws Exception {

    }

    @Override
    public Optional<Departement> storeDepartement(Departement departement) throws Exception {
        try {
            Departement saveResultDepartement = repository.save(departement);
            return Optional.of(saveResultDepartement);
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan departement", e);
        }
    }

    @Override
    public void storeDepartementList(List<Departement> departement) throws Exception {

    }
}
