package len.silvue.webpendukung.trainlocation.adapter.out.persistance;

import len.silvue.webpendukung.domains.RouteStickTrain;
import len.silvue.webpendukung.trainlocation.adapter.out.persistance.repositories.RouteStickTrainRepository;
import len.silvue.webpendukung.trainlocation.application.port.out.FindTrainLocationRunningPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TrainLocationRunningAdapter implements FindTrainLocationRunningPort {

    private final RouteStickTrainRepository repo;

    @Override
    public List<RouteStickTrain> loadAllRouteStickTrain() {
        return repo.findAll();
    }

    @Override
    public Optional<RouteStickTrain> loadRouteStickTrainById(int id) {
        return repo.findById(id);
    }
}
