package len.silvue.webpendukung.schedule.adapter.out.web;

import len.silvue.webpendukung.domains.Train;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ColorTrainDto {
    private int idColorTrain;
    private Train train;
    private String colorTrain;
}
