package len.silvue.webpendukung.todays.adapter.in;


import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyTodayCommand {
    private int idTodayRunningSchedule;
    private int idTrainSelect;
    private int trainId;
    private String idTrainAdd;
    private String trainName;
    private int idTrain;
    private int idTypeMasterPlan;
    private int idRuteRole;
    private String nameRoute;
    private String depart;
    private String arrival;
    private long dwellingTime;
    private String flagMaster;

}
