package len.silvue.webpendukung.actual.adapter.out.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParamActualPlanDto {
    private List<String> typePlans;
    private List<String> ruteRoles;
}
