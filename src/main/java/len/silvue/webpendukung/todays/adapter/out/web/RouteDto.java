package len.silvue.webpendukung.todays.adapter.out.web;

import len.silvue.webpendukung.schedule.adapter.out.web.PeronDto;
import len.silvue.webpendukung.schedule.adapter.out.web.StationDto;
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
        private int idRuteRole;
        private String nameRoute;
        private StationDto station;
        private PeronDto peron;
        private Date arrival;
        private Date depart;
        private int scheduleId;
}


