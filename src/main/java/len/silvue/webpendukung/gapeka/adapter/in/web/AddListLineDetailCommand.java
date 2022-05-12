package len.silvue.webpendukung.gapeka.adapter.in.web;

import len.silvue.webpendukung.domains.Line;
import len.silvue.webpendukung.domains.Station;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddListLineDetailCommand {
    private int idListLineDetail;
    private Line Line;
    private int idLine;
    private Station station;
    private int idStation;
    private int indexListLineDetail;
    private String locUnitLine;
}
