package len.silvue.webpendukung.schedule.adapter.out.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteDto {
    private int routeId;
    private StationDto station;
    private PeronDto peron;
    private RuteRoleDto ruteRole;
    private Date arrival;
    private Date depart;
    private String nextDay;
}
