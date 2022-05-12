package len.silvue.webpendukung.ars.adapter.in.web;

import len.silvue.webpendukung.domains.ArsSchedule;
import len.silvue.webpendukung.domains.Peron;
import len.silvue.webpendukung.schedule.adapter.out.web.*;
import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ModifyArsCommand {
    private int idArsSchedule;
    private TypeMasterPlanDto typeMasterPlan;
    private int idTrain;
    private int idStation;
    private int idTypeMasterPlan;
    private int idRuteRole;
    private int idRouteStick;
    private int idPeron;
    private String choice;
    private int delayArs;
    private PeronDto peronToArs;
    private TrainDto train;
    private RuteRoleDto ruteRole;
    private String departArs;
    private String arrivalArs;
    private int scheduleStatusArs;
    private String routeSettingStatus;
    private int flagArsSchedule;

}

