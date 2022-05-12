package len.silvue.webpendukung.schedule.adapter.out.web;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PeronDto {
    private int idPeron;
    private StationDto station;
    private RouteStickDto routeStick;
    private int noPeron;
}
