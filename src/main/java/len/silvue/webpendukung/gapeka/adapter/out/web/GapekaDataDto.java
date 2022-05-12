package len.silvue.webpendukung.gapeka.adapter.out.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GapekaDataDto {
    private long datePoint;
    private int stationPoint;
}
