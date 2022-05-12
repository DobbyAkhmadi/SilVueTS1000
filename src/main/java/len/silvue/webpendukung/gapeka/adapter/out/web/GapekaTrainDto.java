package len.silvue.webpendukung.gapeka.adapter.out.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GapekaTrainDto {
    private String trainName;
    private List<GapekaDataDto> gapekaDataDtos;
    private String trainColor;
}
