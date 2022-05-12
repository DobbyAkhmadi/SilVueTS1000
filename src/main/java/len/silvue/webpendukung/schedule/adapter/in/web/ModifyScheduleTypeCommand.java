package len.silvue.webpendukung.schedule.adapter.in.web;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyScheduleTypeCommand {
    private String scheduleTypeName;
    private int idTypeMasterPlan;

}
