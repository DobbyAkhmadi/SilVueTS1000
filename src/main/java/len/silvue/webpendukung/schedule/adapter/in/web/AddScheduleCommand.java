package len.silvue.webpendukung.schedule.adapter.in.web;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddScheduleCommand {
    private String scheduleName;
    private int idTrain;
    private int idTypeMasterPlan;
    private int idRuteRole;
    private String flagMaster;
    private String nextDay;
}
