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
public class ListLineDetailDto {
    private int idListLineDetail;
    private LineDto line;
    private StationDto station;
    private String locUnitLine;
    private int indexListLineDetail;
}
