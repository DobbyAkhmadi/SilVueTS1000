package len.silvue.webpendukung.ars.application.services;

import len.silvue.webpendukung.ars.adapter.out.web.ArsConflictDto;
import len.silvue.webpendukung.ars.adapter.out.web.ArsScheduleDto;
import len.silvue.webpendukung.ars.adapter.out.web.mapper.ArsScheduleMapper;
import len.silvue.webpendukung.ars.adapter.out.web.mapper.ConflictArsMapper;
import len.silvue.webpendukung.ars.application.port.in.AddArsConflictUseCase;
import len.silvue.webpendukung.ars.application.port.in.FindArsConflictUseCase;
import len.silvue.webpendukung.ars.application.port.out.LoadConflictArsPort;
import len.silvue.webpendukung.domains.ArsConflict;
import len.silvue.webpendukung.domains.ArsSchedule;
import len.silvue.webpendukung.domains.RouteStick;
import len.silvue.webpendukung.schedule.adapter.out.web.RouteStickDto;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.ConflictMasterPlanMapper;
import len.silvue.webpendukung.schedule.application.port.out.LoadSuccessBrowsePort;
import len.silvue.webpendukung.domains.MasterPlan;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindConflictArsService implements FindArsConflictUseCase {
    private final LoadConflictArsPort loadConflictArsPort;
    private final ConflictArsMapper conflictArsMapper;
    private final ArsScheduleMapper arsScheduleMapper;


    @Override
    public List<ArsConflictDto> getAllArsConflict() throws Exception {
        try {
            Optional<List<ArsConflict>> optionalConflictArs = Optional.empty();

            optionalConflictArs = loadConflictArsPort.loadAllConflictArs();

            return conflictArsMapper.toConflictArsDto(optionalConflictArs.orElse(new ArrayList<>()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("failed to get all conflict ARS", e);
        }
    }

    @Override
    public ArsConflictDto getArsConflictById(int idArs) throws DataNotFoundException {
        try {
            Optional<ArsSchedule> optionalMasterPlan = loadConflictArsPort.loadArsScheduleById(idArs);
            return conflictArsMapper.toConflictAtrPlanDto(optionalMasterPlan.orElseThrow());
        } catch (Exception e) {
            throw new DataNotFoundException("Gagal mengambil schedule berdasarkan id", e);
        }
    }

    @Override
    public ArsScheduleDto getArsScheduleById(int idArsSchedule ) throws Exception {
        try {
            Optional<ArsSchedule> optionalArsSchedule = loadConflictArsPort.loadArsScheduleById(idArsSchedule);
            return arsScheduleMapper.toArsScheduleDto(optionalArsSchedule.orElseThrow());
        } catch (Exception e) {
            throw new DataNotFoundException("failed to get arsschedule by id", e);
        }
    }

    @Override
    public RouteStickDto getRoutestickById(int idRouteStick) throws Exception {
        try {
            Optional<RouteStick> optionalRouteStick = loadConflictArsPort.loadRouteStickById(idRouteStick);
            return arsScheduleMapper.toRouteStickDto(optionalRouteStick.orElseThrow());
        } catch (Exception e) {
            throw new DataNotFoundException("failed to get routestick by id", e);
        }
    }
}
