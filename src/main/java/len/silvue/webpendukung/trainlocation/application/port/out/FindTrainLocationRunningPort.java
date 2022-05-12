package len.silvue.webpendukung.trainlocation.application.port.out;



import len.silvue.webpendukung.domains.RouteStickTrain;

import java.util.List;
import java.util.Optional;
public interface FindTrainLocationRunningPort {
    List<RouteStickTrain> loadAllRouteStickTrain();
    Optional <RouteStickTrain> loadRouteStickTrainById(int id);
}
