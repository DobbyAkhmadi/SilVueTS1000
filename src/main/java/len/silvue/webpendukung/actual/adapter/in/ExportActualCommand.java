package len.silvue.webpendukung.actual.adapter.in;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExportActualCommand {
    private String fromActualCode;
    private String toActualCode;
}
