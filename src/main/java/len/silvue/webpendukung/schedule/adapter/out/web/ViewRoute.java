package len.silvue.webpendukung.schedule.adapter.out.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewRoute {
    private int routeId;
    private String timeString;
}
