package len.silvue.webpendukung.eventlog.adapter.out.web;

import len.silvue.webpendukung.actual.adapter.out.web.DepartementDto;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LogMessageDto {
    private Integer idLogMessage;
    private DepartementDto departement;
    private String message;
    private Date timeLogMessage;
    private String userLogin;
}
