package len.silvue.webpendukung.schedule.adapter.out.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleDto {
    private int scheduleId;
    private String scheduleName;
    private TypeMasterPlanDto typeMasterPlan;
    private TrainDto train;
    private RuteRoleDto ruteRole;
    private String flagMaster;
}
