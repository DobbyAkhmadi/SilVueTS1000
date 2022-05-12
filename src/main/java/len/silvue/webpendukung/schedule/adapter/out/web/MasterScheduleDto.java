package len.silvue.webpendukung.schedule.adapter.out.web;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MasterScheduleDto {
    private int idMasterPlan;
    private String noka;
    private String nameRoute;
    private String idTypeMasterPlan;
    private String nameTypeMasterPlan;
    private List<TrainScheduleDto> trainScheduleDtoList;
}
