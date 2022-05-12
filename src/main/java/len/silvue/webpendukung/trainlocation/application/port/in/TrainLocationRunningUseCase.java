package len.silvue.webpendukung.trainlocation.application.port.in;


import len.silvue.webpendukung.trainlocation.adapter.out.web.RouteStickTrainDTO;

import java.util.List;
public interface TrainLocationRunningUseCase {
    List<RouteStickTrainDTO> getAllTrainLocationRunning();
    RouteStickTrainDTO getTrainLocationRunningById(int id);
}
