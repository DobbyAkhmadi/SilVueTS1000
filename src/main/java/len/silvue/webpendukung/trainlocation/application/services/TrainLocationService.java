package len.silvue.webpendukung.trainlocation.application.services;

import len.silvue.webpendukung.domains.RouteStickTrain;
import len.silvue.webpendukung.trainlocation.adapter.out.web.RouteStickTrainDTO;
import len.silvue.webpendukung.trainlocation.adapter.out.web.mapper.RouteStickTrainMapper;
import len.silvue.webpendukung.trainlocation.application.port.in.TrainLocationRunningUseCase;
import len.silvue.webpendukung.trainlocation.application.port.out.FindTrainLocationRunningPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainLocationService implements TrainLocationRunningUseCase {
    private final FindTrainLocationRunningPort port;
    private final RouteStickTrainMapper mapper;

    @Override
    public List<RouteStickTrainDTO> getAllTrainLocationRunning() {
        return mapper.toRouteStickTrainDtoList(port.loadAllRouteStickTrain());
    }

    @Override
    public RouteStickTrainDTO getTrainLocationRunningById(int id) {
        Optional<RouteStickTrain> optionalTrainLocationRunning = port.loadRouteStickTrainById(id);
        return mapper.toRouteStickTrainDto(optionalTrainLocationRunning.orElse(null));
    }

}
