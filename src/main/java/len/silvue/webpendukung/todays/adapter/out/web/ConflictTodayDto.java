package len.silvue.webpendukung.todays.adapter.out.web;

import len.silvue.webpendukung.todays.adapter.out.web.TodayRunningScheduleDto;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConflictTodayDto {
    private int idConflictTableMaster;
    private TodayRunningScheduleDto todayA;
    private TodayRunningScheduleDto todayB;
}
