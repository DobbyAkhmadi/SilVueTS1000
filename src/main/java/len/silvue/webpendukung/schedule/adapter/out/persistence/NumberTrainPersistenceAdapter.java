package len.silvue.webpendukung.schedule.adapter.out.persistence;

import len.silvue.webpendukung.schedule.adapter.out.persistence.repositories.NumberTrainRepository;
import len.silvue.webpendukung.schedule.application.port.out.LoadNumberTrainPort;
import len.silvue.webpendukung.domains.NumberTrain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class NumberTrainPersistenceAdapter implements LoadNumberTrainPort {
    private final NumberTrainRepository numberTrainRepository;

    @Override
    public Optional<List<NumberTrain>> loadAllNumberTrain() throws Exception {
        try {
            List<NumberTrain> numberTrainList = numberTrainRepository.findAll();
            return Optional.of(numberTrainList);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil data number train", e);
        }
    }
}
