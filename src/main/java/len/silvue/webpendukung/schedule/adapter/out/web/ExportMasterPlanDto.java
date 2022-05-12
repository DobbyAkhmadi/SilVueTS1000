package len.silvue.webpendukung.schedule.adapter.out.web;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExportMasterPlanDto {
    private List<StationDto> stationList;
    private List<MasterScheduleDto> masterScheduleList;
    private String typeMasterPlan;
}
