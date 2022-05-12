package len.silvue.webpendukung.schedule.adapter.out.web;

import lombok.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MasterPlanDto {
    private int idMasterPlan;
    private TrainDto train;
    private PeronDto peronFrom;
    private RuteRoleDto ruteRole;
    private TypeMasterPlanDto typeMasterPlan;
    private Date depart;
    private Date arrival;
    private List<RouteDto> routes;
    private long dwellingTime;
    private int flagMasterPlan;
    private int flagCheckConflict;
}
