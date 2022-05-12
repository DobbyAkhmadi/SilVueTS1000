package len.silvue.webpendukung.schedule.adapter.out.web;

import len.silvue.webpendukung.gapeka.adapter.out.web.LineDto;
import len.silvue.webpendukung.schedule.adapter.out.web.RuteRoleDto;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
@Builder
public class ParamDto {
   private List<RuteRoleDto> ruteRoleList;
   private List<LineDto> lineList;
   private List<TypeMasterPlanDto> typeMasterPlanList;
}
