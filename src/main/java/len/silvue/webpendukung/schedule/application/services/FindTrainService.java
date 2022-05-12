package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.schedule.adapter.out.web.TrainDto;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.TrainMapper;
import len.silvue.webpendukung.schedule.application.port.in.FindTrainUseCase;
import len.silvue.webpendukung.schedule.application.port.out.LoadTrainPort;
import len.silvue.webpendukung.domains.Train;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindTrainService implements FindTrainUseCase {
    private final LoadTrainPort loadTrainPort;
    private final TrainMapper trainMapper;

    @Override
    public List<TrainDto> getAllTrain() throws Exception {
        try {
            Optional<List<Train>> optionalTrainList = loadTrainPort.loadAllTrain();
            return trainMapper.toTrainDtoList(optionalTrainList.orElse(new ArrayList<>()));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil semua kereta api", e);
        }
    }

    @Override
    public TrainDto getTrainById(int idTrain) throws Exception {
        try {
            Optional<Train> optionalTrain = loadTrainPort.loadTrainById(idTrain);
            return trainMapper.toTrainDto(optionalTrain.orElseThrow(DataNotFoundException::new));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil semua kereta api", e);
        }
    }
}
