package len.silvue.webpendukung.gapeka.application.services;

import len.silvue.webpendukung.gapeka.adapter.in.web.AddListRuteDetailCommand;
import len.silvue.webpendukung.gapeka.adapter.out.web.ListRuteDetailDto;
import len.silvue.webpendukung.gapeka.application.port.in.AddListRouteDetailUseCase;
import len.silvue.webpendukung.gapeka.application.port.out.SaveListRuteDetailPort;
import len.silvue.webpendukung.domains.ListRuteDetail;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.ListRouteMapper;
import len.silvue.webpendukung.schedule.application.port.out.LoadRuteRolePort;
import len.silvue.webpendukung.schedule.application.port.out.LoadStationPort;
import len.silvue.webpendukung.domains.RuteRole;
import len.silvue.webpendukung.domains.Station;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddListRuteDetailService implements AddListRouteDetailUseCase {
    private final SaveListRuteDetailPort saveListRoutePort;
    private final LoadStationPort loadStationPort;
    private final LoadRuteRolePort loadRuteRolePort;
    private final ListRouteMapper listRouteMapper;
    @Override
    public ListRuteDetailDto saveListRouteDetail(AddListRuteDetailCommand addListRuteDetailCommand) throws Exception {
        try {
            Optional<Station> optionalStation = loadStationPort.loadStationById(addListRuteDetailCommand.getIdStation());
            Station station = optionalStation.orElseThrow(DataNotFoundException::new);

            Optional<RuteRole> optionalRuteRole = loadRuteRolePort.loadRuteRoleById(addListRuteDetailCommand.getIdRuteRole());
            RuteRole ruteRole = optionalRuteRole.orElseThrow(DataNotFoundException::new);

            ListRuteDetail listRuteDetail = ListRuteDetail.builder()
                    .ruteRole(ruteRole)
                    .station(station)
                    .locUnitRute(addListRuteDetailCommand.getLocUnitRute())
                    .indexListRuteDetail(addListRuteDetailCommand.getIndexListRuteDetail())
                    .build();
            Optional<ListRuteDetail> optionalListRuteDetail = Optional.ofNullable(saveListRoutePort.storeListRoute(listRuteDetail));
           return listRouteMapper.toListRuteDetailDto(optionalListRuteDetail.orElseThrow(DataNotFoundException::new));
        } catch(Exception e) {
            throw new Exception("failed to add Route Detail Command", e);
        }
    }
}
