package len.silvue.webpendukung.schedule.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddScheduleTypeCommand {
    private String scheduleTypeName;
}
