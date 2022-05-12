package len.silvue.webpendukung.gapeka.adapter.out.web;

import len.silvue.webpendukung.schedule.adapter.out.web.RuteRoleDto;
import len.silvue.webpendukung.schedule.adapter.out.web.StationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListRuteDetailDto {
    private int idListRuteDetail;
    private RuteRoleDto ruteRole;
    private StationDto station;
    private String locUnitRute;
    private int indexListRuteDetail;
}
