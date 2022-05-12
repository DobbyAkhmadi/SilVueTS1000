package len.silvue.webpendukung.schedule.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ModifyRouteCommand {
    private int routeId;
    private int idRuteRole;
    private String nameRoute;
    private int stationId;
    private int peronId;
    private String arrival;
    private String depart;
}
