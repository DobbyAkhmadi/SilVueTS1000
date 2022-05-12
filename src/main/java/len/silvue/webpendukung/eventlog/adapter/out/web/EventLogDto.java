package len.silvue.webpendukung.eventlog.adapter.out.web;

import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventLogDto {

    private int idEventLog;
    private Date timeLogMessage;
    private String eventMessage;
    private String userLogIn;

}

