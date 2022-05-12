package len.silvue.webpendukung.trainlocation.adapter.out.web;

import len.silvue.webpendukung.schedule.adapter.out.web.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RouteStickTrainDTO {
    private TrainDto train;
    private RouteStickDto trackLoc;
    private String statusTedo;
    private String statusRedo;
    private String statusRequest;
}
