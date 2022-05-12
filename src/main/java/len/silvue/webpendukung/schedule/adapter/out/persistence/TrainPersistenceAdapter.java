package len.silvue.webpendukung.schedule.adapter.out.persistence;

import len.silvue.webpendukung.schedule.adapter.out.persistence.repositories.TrainRepository;
import len.silvue.webpendukung.schedule.application.port.out.DeleteTrainPort;
import len.silvue.webpendukung.schedule.application.port.out.LoadTrainPort;
import len.silvue.webpendukung.schedule.application.port.out.SaveTrainPort;
import len.silvue.webpendukung.domains.Train;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TrainPersistenceAdapter implements LoadTrainPort, SaveTrainPort, DeleteTrainPort {
    private final TrainRepository trainRepository;

    @Override
    public Optional<Train> loadTrainByNoka(String noka) throws Exception {
        try {
            return trainRepository.findTrainByNoka(noka);
        } catch (Exception e) {
            throw new Exception("Gagal mengambil train berdasarkan noka", e);
        }
    }

    @Override
    public Optional<List<Train>> loadAllTrain() throws Exception {
        try {
            return Optional.of(trainRepository.findAll());
        } catch(Exception e) {
            throw new Exception("Gagal mengambil semua train", e);
        }
    }

    @Override
    public Optional<Train> loadTrainById(int idTrain) throws Exception {
        try {
            return trainRepository.findById(idTrain);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil train berdasarkan id", e);
        }
    }

    @Override
    public void saveTrain(Train train) throws Exception {
        try {
            trainRepository.save(train);
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan train", e);
        }

    }

    @Override
    public List<Train> saveAllTrain(List<Train> trainList) throws Exception {
        try {
            return trainRepository.saveAll(trainList);
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan semua train", e);
        }
    }

    @Override
    public void eraseAllTrain() throws Exception {
        try {
            trainRepository.deleteAll();
        } catch(Exception e) {
            throw new Exception("Gagal menghapus semua train", e);
        }
    }
}
