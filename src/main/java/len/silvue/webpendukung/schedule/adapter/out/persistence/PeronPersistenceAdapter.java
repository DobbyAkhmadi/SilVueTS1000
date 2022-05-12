package len.silvue.webpendukung.schedule.adapter.out.persistence;

import len.silvue.webpendukung.schedule.adapter.out.persistence.repositories.PeronRepository;
import len.silvue.webpendukung.schedule.adapter.out.persistence.repositories.StationRepository;
import len.silvue.webpendukung.schedule.application.port.out.DeletePeronPort;
import len.silvue.webpendukung.schedule.application.port.out.LoadPeronPort;
import len.silvue.webpendukung.schedule.application.port.out.SavePeronPort;
import len.silvue.webpendukung.domains.Peron;
import len.silvue.webpendukung.domains.Station;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PeronPersistenceAdapter implements LoadPeronPort, DeletePeronPort, SavePeronPort {
    private final PeronRepository peronRepository;
    private final StationRepository stationRepository;

    @Override
    public Optional<List<Peron>> loadPeronsByStation(Integer idStation) throws Exception {
        try {
            Optional<Station> optionalStation = stationRepository.findById(idStation);
            return peronRepository.findPeronsByStation(optionalStation.orElseThrow());
        } catch(Exception e) {
            throw new Exception("Gagal mengambil data perons", e);
        }
    }

    @Override
    public Optional<Peron> loadPeronById(int idPeron) throws Exception {
        try {
            return peronRepository.findById(idPeron);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil data peron by id", e);
        }
    }

    @Override
    public void eraseAllPeron() throws Exception {
        try {
            peronRepository.deleteAll();
        } catch(Exception e) {
            throw new Exception("Gagal menghapus semua peron", e);
        }
    }

    @Override
    public Optional<List<Peron>> loadAllPeron() throws Exception {
        try {
            return Optional.of(peronRepository.findAll());
        } catch(Exception e) {
            throw new Exception("Gagal mengambil semua data peron", e);
        }
    }

    @Override
    public Peron savePeron(Peron peron) throws Exception {
        try {
            return peronRepository.save(peron);
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan peron", e);
        }
    }
}
