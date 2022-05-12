package len.silvue.webpendukung.schedule.adapter.out.web;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TrainScheduleDto {
    private int idStation;
    private String stationName;
    private String arrivalTime;
    private String departureTime;
    private int flagMasterPlan;
    private int platform;
}
