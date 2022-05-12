package len.silvue.webpendukung.schedule.application.port.out;

import len.silvue.webpendukung.domains.Peron;

import java.util.List;
import java.util.Optional;

public interface LoadPeronPort {
    Optional<List<Peron>> loadPeronsByStation(Integer idStation) throws Exception;
    Optional<Peron> loadPeronById(int idPeron) throws Exception;
    Optional<List<Peron>> loadAllPeron() throws Exception;
}
