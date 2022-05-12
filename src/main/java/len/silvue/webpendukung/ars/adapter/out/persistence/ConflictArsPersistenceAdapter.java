package len.silvue.webpendukung.ars.adapter.out.persistence;

import len.silvue.webpendukung.ars.adapter.out.persistence.repositories.ArsScheduleRepository;
import len.silvue.webpendukung.ars.adapter.out.persistence.repositories.ConflictArsRepository;
import len.silvue.webpendukung.ars.application.port.out.LoadConflictArsPort;
import len.silvue.webpendukung.ars.application.port.out.SaveArsConflictPort;
import len.silvue.webpendukung.domains.ArsConflict;
import len.silvue.webpendukung.domains.ArsSchedule;
import len.silvue.webpendukung.domains.RouteStick;
import len.silvue.webpendukung.schedule.adapter.out.persistence.repositories.RouteStickRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConflictArsPersistenceAdapter implements LoadConflictArsPort, SaveArsConflictPort {
    private final ConflictArsRepository conflictArsRepository;
    private final ArsScheduleRepository arsScheduleRepository;
    private final RouteStickRepository routeStickRepository;

    @Override
    public Optional<List<ArsConflict>> loadAllConflictArs() throws Exception {
        try {
            Optional<List<ArsConflict>> conflictArsList = Optional.of(conflictArsRepository.findAll());
            return conflictArsList;
        } catch(Exception e) {
            throw new Exception("failed ars by id", e);
        }
    }

    @Override
    public Optional<ArsSchedule> loadArsScheduleById(int idArsSchedule) throws Exception {
        try {
            return arsScheduleRepository.findById(idArsSchedule);
        } catch(Exception e) {
            throw new Exception("failed load ars schedule by id", e);
        }
    }

    @Override
    public Optional<List<ArsSchedule>> loadArsScheduleByTrain(int idTrain) throws Exception {
        try {
            Optional<List<ArsSchedule>> arsSchedule = Optional.ofNullable(arsScheduleRepository.findArsScheduleByTrain(idTrain));
            return arsSchedule;
        } catch(Exception e) {
            throw new Exception("failed load ars schedule by id", e);
        }
    }

    @Override
    public Optional<RouteStick> loadRouteStickById(int idRouteStick) throws Exception {
        try {
            Optional<RouteStick> routeStickOptional = routeStickRepository.findById(idRouteStick);
            return routeStickOptional;
        } catch(Exception e) {
            throw new Exception("failed load masterPlan by id", e);
        }
    }

    @Override
    public List<ArsSchedule> storeArsList(List<ArsSchedule> arsSchedule) throws Exception {
        try {
            return arsScheduleRepository.saveAll(arsSchedule);
        } catch(Exception e) {
            throw new Exception("failed to update data masterplan", e);
        }
    }

    @Override
    public ArsConflict storeDataConflictArs(ArsConflict arsConflict) throws Exception {
        try
        {
            return conflictArsRepository.save(arsConflict);
        } catch(Exception e) {
            throw new Exception("failed load masterPlan by id", e);
        }
    }
}
