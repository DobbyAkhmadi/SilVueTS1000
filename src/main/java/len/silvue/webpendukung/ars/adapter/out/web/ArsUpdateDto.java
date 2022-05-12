package len.silvue.webpendukung.ars.adapter.out.web;

import len.silvue.webpendukung.schedule.adapter.out.web.*;
import lombok.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArsUpdateDto {

    private int idArsSchedule;
    private TypeMasterPlanDto typeMasterPlan;
    private NumberTrainDto numberTrain;
    private PeronDto peronFromArs;
    private PeronDto peronToArs;
    private TrainDto train;
    private RuteRoleDto ruteRole;
    private Date departArs;
    private Date arrivalArs;
    private int scheduleStatusArs;
    private String routeSettingStatus;
    private int flagArsSchedule;
}

