package len.silvue.webpendukung.schedule.adapter.out.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ViewMasterScheduleDto {
    private int scheduleId;
    private String routeName;
    private String trainName;
    private String scheduleType;
    private List<ViewRoute> viewRouteList;
}
