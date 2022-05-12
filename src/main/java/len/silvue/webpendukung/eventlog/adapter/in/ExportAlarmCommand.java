package len.silvue.webpendukung.eventlog.adapter.in;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExportAlarmCommand {
    private String timeMessage;
}
