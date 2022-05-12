package len.silvue.webpendukung.gapeka.application.services;

import len.silvue.webpendukung.gapeka.adapter.out.web.LineDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.mapper.LineMapper;
import len.silvue.webpendukung.gapeka.application.port.in.FindLineUseCase;
import len.silvue.webpendukung.gapeka.application.port.out.LoadLinePort;
import len.silvue.webpendukung.domains.Line;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindLineService implements FindLineUseCase {
    private final LoadLinePort loadLinePort;
    private final LineMapper lineMapper;

    @Override
    public List<LineDto> getAllLine() throws Exception {
        try {
            Optional<List<Line>> optionalLines = loadLinePort.loadAllLine();
            return lineMapper.toLineDtoList(optionalLines.orElseThrow(DataNotFoundException::new));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil line list", e);
        }
    }
}
