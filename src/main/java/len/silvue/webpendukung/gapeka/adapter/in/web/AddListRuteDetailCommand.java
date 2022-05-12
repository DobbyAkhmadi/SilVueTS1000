package len.silvue.webpendukung.gapeka.adapter.in.web;

import len.silvue.webpendukung.domains.RuteRole;
import len.silvue.webpendukung.domains.Station;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddListRuteDetailCommand {
    private int idListRuteDetail;
    private RuteRole ruteRole;
    private Station station;
    private int idStation;
    private int idRuteRole;
    private int indexListRuteDetail;
    private String locUnitRute;
}
