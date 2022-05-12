package len.silvue.webpendukung.schedule.adapter.out.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StationDto {
    private int idStation;
    private String nameStation;
    private String mnemonic;
}
