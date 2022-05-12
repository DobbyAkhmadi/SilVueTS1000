package len.silvue.webpendukung.schedule.adapter.out.persistence;

import len.silvue.webpendukung.schedule.adapter.out.persistence.repositories.ColorTrainRepository;
import len.silvue.webpendukung.schedule.application.port.out.*;

import len.silvue.webpendukung.domains.ColorTrain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ColorTrainPersistenceAdapter implements SaveColorTrainPort,LoadColorTrainPort, DeleteColorTrainPort {
    private final ColorTrainRepository colorTrainRepository;
    @Override
    public Optional<List<ColorTrain>> loadAllColorTrain() throws Exception {
        try {
            return Optional.of(colorTrainRepository.findAll());
        } catch(Exception e) {
            throw new Exception("Gagal mengambil semua color train", e);
        }
    }
    @Override
    public Optional<ColorTrain> loadColorTrainById(int id) throws Exception {
        try {
            return colorTrainRepository.findById(id);
        } catch(Exception e) {
            throw new Exception("Gagal load color train by id", e);
        }
    }
    @Override
    public ColorTrain storeColorTrain(ColorTrain colorTrain) throws Exception {
        try {
            return colorTrainRepository.save(colorTrain);
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan color store", e);
        }
    }

    @Override
    public List<ColorTrain> saveAllColorTrain(List<ColorTrain> colorTrainList) throws Exception {
        try {
            return colorTrainRepository.saveAll(colorTrainList);
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan semua train", e);
        }
    }

    @Override
    public void eraseAllColorTrain() throws Exception {
        try {
            colorTrainRepository.deleteAll();
        } catch(Exception e) {
            throw new Exception("Gagal mengahpus semua color train", e);
        }
    }
}
