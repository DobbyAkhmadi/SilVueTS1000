package len.silvue.webpendukung.gapeka.adapter.out.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GapekaStationDto {
    private int stationId;
    private String stationName;
    private int stationPoint;
}
