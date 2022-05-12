package len.silvue.webpendukung.gapeka.application.services;

import len.silvue.webpendukung.gapeka.adapter.in.web.AddListLineDetailCommand;
import len.silvue.webpendukung.gapeka.adapter.out.web.ListLineDetailDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.mapper.ListLineDetailMapper;
import len.silvue.webpendukung.gapeka.application.port.in.AddListLineDetailUseCase;
import len.silvue.webpendukung.gapeka.application.port.out.LoadLinePort;
import len.silvue.webpendukung.gapeka.application.port.out.SaveListLineDetailPort;
import len.silvue.webpendukung.domains.Line;
import len.silvue.webpendukung.domains.ListLineDetail;
import len.silvue.webpendukung.schedule.application.port.out.LoadStationPort;
import len.silvue.webpendukung.domains.Station;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddListLineDetailService implements AddListLineDetailUseCase {
    private final SaveListLineDetailPort saveListLineDetailPort;
    private final LoadStationPort loadStationPort;
    private final LoadLinePort loadLinePort;
    private final ListLineDetailMapper listLineDetailMapper;

    @Override
    public ListLineDetailDto saveListLineDetail(AddListLineDetailCommand addListLineDetailCommand) throws Exception {
        try {
            Optional<Station> optionalStation = loadStationPort.loadStationById(addListLineDetailCommand.getIdStation());
            Station station = optionalStation.orElseThrow(DataNotFoundException::new);

            Optional<Line> optionalRuteRole = loadLinePort.loadLineById(addListLineDetailCommand.getIdLine());
            Line line = optionalRuteRole.orElseThrow(DataNotFoundException::new);

            ListLineDetail listLineDetail = ListLineDetail.builder()
                    .line(line)
                    .station(station)
                    .locUnitLine(addListLineDetailCommand.getLocUnitLine())
                    .indexListLineDetail(addListLineDetailCommand.getIndexListLineDetail())
                    .build();

            Optional<ListLineDetail> optionalListLineDetail = Optional.ofNullable(saveListLineDetailPort.storeListLine(listLineDetail));

            return listLineDetailMapper.toListLineDetailDto(optionalListLineDetail.orElseThrow(DataNotFoundException::new));
        } catch(Exception e) {
            throw new Exception("failed to save line detail service", e);
        }
    }
}
