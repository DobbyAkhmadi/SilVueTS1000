package len.silvue.webpendukung.schedule.adapter.out.web;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConflictMasterPlanDto {
    private int idConflictTableMaster;
    private MasterPlanDto masterPlanA;
    private MasterPlanDto masterPlanB;
}
