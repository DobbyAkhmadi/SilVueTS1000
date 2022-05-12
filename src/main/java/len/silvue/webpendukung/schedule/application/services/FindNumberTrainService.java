package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.schedule.adapter.out.web.NumberTrainDto;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.NumberTrainMapper;
import len.silvue.webpendukung.schedule.application.port.in.FindNumberTrainUseCase;
import len.silvue.webpendukung.schedule.application.port.out.LoadNumberTrainPort;
import len.silvue.webpendukung.domains.NumberTrain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindNumberTrainService implements FindNumberTrainUseCase {
    private final LoadNumberTrainPort loadNumberTrainPort;
    private final NumberTrainMapper numberTrainMapper;

    @Override
    public List<NumberTrainDto> getAllNumberTrain() throws Exception {
        try {
            Optional<List<NumberTrain>> optionalNumberTrains = loadNumberTrainPort.loadAllNumberTrain();
            return numberTrainMapper.toNumberTrainDtoList(optionalNumberTrains.orElse(new ArrayList<>()));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil number train", e);
        }
    }
}
