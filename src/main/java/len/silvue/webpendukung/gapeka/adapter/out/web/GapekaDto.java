package len.silvue.webpendukung.gapeka.adapter.out.web;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GapekaDto {
    private List<GapekaStationDto> gapekaStationDtos;
    private List<GapekaTrainDto> gapekaTrainDtos;
    private long minDate;
    private long maxDate;
    private String title;
}
