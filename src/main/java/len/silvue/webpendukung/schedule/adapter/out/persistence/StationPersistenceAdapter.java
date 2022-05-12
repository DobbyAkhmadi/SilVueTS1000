package len.silvue.webpendukung.schedule.adapter.out.persistence;

import len.silvue.webpendukung.schedule.adapter.out.persistence.repositories.StationRepository;
import len.silvue.webpendukung.schedule.application.port.out.DeleteStationPort;
import len.silvue.webpendukung.schedule.application.port.out.LoadStationPort;
import len.silvue.webpendukung.schedule.application.port.out.SaveStationPort;
import len.silvue.webpendukung.domains.Station;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class StationPersistenceAdapter implements LoadStationPort, SaveStationPort, DeleteStationPort {
    private final StationRepository stationRepository;

    @Override
    public Optional<List<Station>> loadAllStation() throws Exception {
        try {
            List<Station> stationList = stationRepository.findAll();
            return Optional.of(stationList);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil data stasiun", e);
        }
    }

    @Override
    public Optional<Station> loadStationById(int idStation) throws Exception {
        try {
            return stationRepository.findById(idStation);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil station by id", e);
        }
    }

    @Override
    public Optional<Station> loadStationByName(String nameStation) throws Exception {
        try {
            return stationRepository.findStationByNameStation(nameStation);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil station by id", e);
        }
    }

    @Override
    public Optional<List<Station>> LoadAllStationsNotInMasterPlan() throws Exception {
        try {
            List<Station> stationList = stationRepository.findAll();
            return Optional.of(stationList);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil station by id", e);
        }
    }

    @Override
    public Optional<List<Station>> loadAllStationsNotInRouteDetail(int idRoute) throws Exception {
        try {
            List<Station> stationList = stationRepository.findAll();
            return Optional.of(stationList);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil station by id", e);
        }
    }

    @Override
    public List<Station> saveAllStation(List<Station> stationList) throws Exception {
        return stationRepository.saveAll(stationList);
    }

    @Override
    public void eraseAllStation() throws Exception {
        try {
            stationRepository.deleteAll();;
        } catch(Exception e) {
            throw new Exception("Gagal menghapus semua stasiun", e);
        }
    }
}
