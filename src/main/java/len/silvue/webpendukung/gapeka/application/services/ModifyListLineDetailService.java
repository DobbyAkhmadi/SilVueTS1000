package len.silvue.webpendukung.gapeka.application.services;

import len.silvue.webpendukung.gapeka.adapter.in.web.ModifyListLineDetailCommand;
import len.silvue.webpendukung.gapeka.adapter.out.web.ListLineDetailDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.mapper.ListLineDetailMapper;
import len.silvue.webpendukung.gapeka.application.port.in.ModifyListLineUseCase;
import len.silvue.webpendukung.gapeka.application.port.out.LoadListLineDetailPort;
import len.silvue.webpendukung.gapeka.application.port.out.SaveListLineDetailPort;
import len.silvue.webpendukung.domains.ListLineDetail;
import len.silvue.webpendukung.schedule.application.port.out.LoadStationPort;
import len.silvue.webpendukung.domains.Station;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ModifyListLineDetailService implements ModifyListLineUseCase {
    private final LoadStationPort loadStationPort;
    private final LoadListLineDetailPort loadListLineDetailPort;
    private final SaveListLineDetailPort saveListLineDetailPort;
    private final ListLineDetailMapper listLineDetailMapper;

    @Override
    public ListLineDetailDto modifyListLine(ModifyListLineDetailCommand modifyListLineDetailCommand) throws Exception {
        try {
            Optional<Station> optionalStation = loadStationPort.loadStationById(modifyListLineDetailCommand.getIdStation());
            Station station = optionalStation.orElseThrow(DataNotFoundException::new);

            Optional<ListLineDetail> optionalListLineDetail = loadListLineDetailPort.loadListLineDetailById(modifyListLineDetailCommand.getIdListLineDetail());
            ListLineDetail listLineDetail = optionalListLineDetail.orElseThrow(DataNotFoundException::new);

            listLineDetail.setStation(station);
            listLineDetail.setLocUnitLine(modifyListLineDetailCommand.getLocUnitLine());
            listLineDetail.setIndexListLineDetail(modifyListLineDetailCommand.getIndexListLineDetail());

            saveListLineDetailPort.storeListLine(listLineDetail);
            return listLineDetailMapper.toListLineDetailDto(listLineDetail);
        } catch (Exception e) {
            throw new Exception("failed change detail Line", e);
        }
    }
}
