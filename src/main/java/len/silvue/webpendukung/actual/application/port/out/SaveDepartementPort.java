package len.silvue.webpendukung.actual.application.port.out;
import len.silvue.webpendukung.domains.Departement;


import java.util.List;
import java.util.Optional;

public interface SaveDepartementPort {
    void saveDepartement(Departement departement) throws Exception;
    Optional<Departement> storeDepartement(Departement departement) throws Exception;
    void  storeDepartementList(List<Departement> departement) throws Exception;
}
