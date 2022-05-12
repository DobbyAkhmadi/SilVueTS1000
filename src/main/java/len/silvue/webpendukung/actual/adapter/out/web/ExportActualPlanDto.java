package len.silvue.webpendukung.actual.adapter.out.web;

import len.silvue.webpendukung.schedule.adapter.out.web.MasterScheduleDto;
import len.silvue.webpendukung.schedule.adapter.out.web.StationDto;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExportActualPlanDto {
    private List<ActualModifyDto> actualPlanList;
    private String actualCode;
}
