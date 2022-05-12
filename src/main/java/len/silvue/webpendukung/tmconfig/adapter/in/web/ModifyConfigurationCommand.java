package len.silvue.webpendukung.tmconfig.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyConfigurationCommand {
    private String arsStatusEnable;
    private String autoUpdateActual;
    private String autoUpdateEnable;
    private String autoUpdateEnableTemp;
    private String planBase;
    private String typeLine;

    private Integer tdgRangeALive;
    private Integer tdgRangeBLive;
}
