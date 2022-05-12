package len.silvue.webpendukung.gapeka.application.port.out;

import len.silvue.webpendukung.domains.Line;

import java.util.List;

public interface SaveLinePort {
    void saveLineAll(List<Line> lineList) throws Exception;
    Line save(Line line) throws Exception;
}
