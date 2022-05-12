package len.silvue.webpendukung.gapeka.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyListLineDetailCommand {
    private int idListLineDetail;
    private int idLine;
    private int idStation;
    private int indexListLineDetail;
    private String locUnitLine;
}
