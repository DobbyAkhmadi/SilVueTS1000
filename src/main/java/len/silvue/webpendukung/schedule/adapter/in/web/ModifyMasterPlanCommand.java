package len.silvue.webpendukung.schedule.adapter.in.web;

import len.silvue.webpendukung.schedule.adapter.out.web.TrainDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ModifyMasterPlanCommand {
    private int idMasterPlan;
    private int idTrain;
    private String idTrainAdd;
    private int idTrainSelected;
    private TrainDto train;
    private int idStation;
    private int idTypeMasterPlan;
    private int idRuteRole;
    private int idPeron;
    private long dwellingTime;
    private String arrival;
    private String depart;
    private int flagMasterPlan;
}
