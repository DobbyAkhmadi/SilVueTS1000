package len.silvue.webpendukung.domains;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MasterPlanImport {
    private String arrival;
    private String depart;
    private PeronImport peron;
    private Boolean flagNextDay;
    private String train;
    private String rute;
}
