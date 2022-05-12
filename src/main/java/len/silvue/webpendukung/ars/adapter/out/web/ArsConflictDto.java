package len.silvue.webpendukung.ars.adapter.out.web;

import len.silvue.webpendukung.domains.ArsSchedule;
import len.silvue.webpendukung.domains.RouteStick;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ArsConflictDto {
    private int idArsConflict;
    private ArsSchedule arsScheduleNow;
    private ArsSchedule arsScheduleNext;
    private RouteStick routeStick;
}
