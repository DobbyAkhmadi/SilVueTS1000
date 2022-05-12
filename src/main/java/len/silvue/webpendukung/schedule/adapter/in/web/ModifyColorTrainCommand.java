package len.silvue.webpendukung.schedule.adapter.in.web;

import len.silvue.webpendukung.domains.Train;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyColorTrainCommand {
    private int idColorTrain;
    private Train train;
    private String colorTrain;
}
