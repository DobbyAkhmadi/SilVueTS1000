package len.silvue.webpendukung.actual.application.port.out;


import len.silvue.webpendukung.domains.Departement;


import java.util.List;
import java.util.Optional;

public interface LoadDepartementPort {
    Optional<List<Departement>> loadAllDepartement() throws Exception;
    Optional<Departement> loadDepartementById(int idDepartement) throws Exception;
}
