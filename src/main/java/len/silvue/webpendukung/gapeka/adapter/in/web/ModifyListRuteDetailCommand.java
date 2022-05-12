package len.silvue.webpendukung.gapeka.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyListRuteDetailCommand {
    private int idListRuteDetail;
    private int idRuteRole;
    private int idStation;
    private int indexListRuteDetail;
    private String locUnitRute;
}
