package len.silvue.webpendukung.schedule.adapter.in.web;

import len.silvue.webpendukung.schedule.adapter.out.web.TrainDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CombineMasterPlanCommand {
    private String idTypeMasterPlan;
    private String nameTypeMasterPlan;
    private String newNameTypeMasterPlan;
}
