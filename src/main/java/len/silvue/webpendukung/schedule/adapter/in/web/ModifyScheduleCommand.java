package len.silvue.webpendukung.schedule.adapter.in.web;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyScheduleCommand {
    private int scheduleId;
    private String scheduleName;
    private int idTrain;
    private int idTypeMasterPlan;
    private int idRuteRole;
    private String flagMaster;
}
