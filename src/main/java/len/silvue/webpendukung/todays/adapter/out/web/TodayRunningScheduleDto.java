package len.silvue.webpendukung.todays.adapter.out.web;

import len.silvue.webpendukung.schedule.adapter.out.web.*;
import lombok.*;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TodayRunningScheduleDto {
    private int idTodayRunningSchedule;
    private TrainDto train;
    private NumberTrainDto numberTrain;
    private PeronDto peronFrom;
    private RuteRoleDto ruteRole;
    private PeronDto peronTo;
    private TypeMasterPlanDto typeMasterPlan;
    private Date depart;
    private Date arrival;
    private long dwellingTime;
    private int flagMasterPlan;
    private int flagCheckConflict;
    private Date actualCode;
}


