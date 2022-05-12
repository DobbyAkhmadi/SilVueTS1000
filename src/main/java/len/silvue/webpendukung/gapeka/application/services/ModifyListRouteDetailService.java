package len.silvue.webpendukung.gapeka.application.services;

import len.silvue.webpendukung.gapeka.adapter.in.web.ModifyListRuteDetailCommand;
import len.silvue.webpendukung.gapeka.adapter.out.web.ListRuteDetailDto;
import len.silvue.webpendukung.gapeka.application.port.out.LoadListRuteDetailPort;
import len.silvue.webpendukung.gapeka.application.port.out.SaveListRuteDetailPort;
import len.silvue.webpendukung.domains.ListRuteDetail;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.ListRouteMapper;
import len.silvue.webpendukung.gapeka.application.port.in.ModifyListRouteUseCase;
import len.silvue.webpendukung.schedule.application.port.out.*;
import len.silvue.webpendukung.domains.Station;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ModifyListRouteDetailService implements ModifyListRouteUseCase {
    private final LoadStationPort loadStationPort;
    private final LoadListRuteDetailPort loadListRuteDetailPort;
    private final SaveListRuteDetailPort saveListRoutePort;
    private final ListRouteMapper listRouteMapper;
    @Override
    public ListRuteDetailDto modifyListRoute(ModifyListRuteDetailCommand modifyListRuteDetailCommand) throws Exception {
        try {
            Optional<Station> optionalStation = loadStationPort.loadStationById(modifyListRuteDetailCommand.getIdStation());
            Station station = optionalStation.orElseThrow(DataNotFoundException::new);

            Optional<ListRuteDetail> optionalListRuteDetail = loadListRuteDetailPort.
                    loadListRuteDetailById(modifyListRuteDetailCommand.getIdListRuteDetail());
            ListRuteDetail listRuteDetail = optionalListRuteDetail.orElseThrow(DataNotFoundException::new);

            listRuteDetail.setStation(station);
            listRuteDetail.setLocUnitRute(modifyListRuteDetailCommand.getLocUnitRute());
            listRuteDetail.setIndexListRuteDetail(modifyListRuteDetailCommand.getIndexListRuteDetail());

            saveListRoutePort.storeListRoute(listRuteDetail);
            return listRouteMapper.toListRuteDetailDto(listRuteDetail);
            } catch (Exception e) {
                throw new Exception("failed change detail route", e);
            }
    }
}
