package len.silvue.webpendukung.gapeka.adapter.out.persistence;

import len.silvue.webpendukung.domains.ActualPlan;
import len.silvue.webpendukung.gapeka.adapter.out.persistence.repositories.ActualPlanRepository;
import len.silvue.webpendukung.gapeka.application.port.out.LoadActualPlanPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ActualPlanPersistenceAdapter implements LoadActualPlanPort {
    private final ActualPlanRepository actualPlanRepository;

    @Override
    public Optional<List<ActualPlan>> loadAllActualPlan() throws Exception {
        try {
            List<ActualPlan> actualPlanList = actualPlanRepository.findAll();
            return Optional.ofNullable(actualPlanList);
        } catch(Exception e) {
            throw new Exception("Gagal load all actual plan", e);
        }
    }

    @Override
    public Optional<List<ActualPlan>> loadActualPlanByRuteRole(String ruteRole) throws Exception {
        try {
            List<ActualPlan> actualPlanList = actualPlanRepository.findActualPlansByRuteRoleActualPlan(ruteRole);
            return Optional.ofNullable(actualPlanList);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil load actual plan by rute role", e);
        }
    }

    @Override
    public Optional<List<ActualPlan>> loadActualPlanByActualCodeAndRuteRoleAndTypePlan(Date actualCode, String ruteRole, String typePlan) throws Exception {
        try {
            List<ActualPlan> actualPlanList = actualPlanRepository.findActualPlansByActualCodeAndRuteRoleActualPlan(typePlan, actualCode, ruteRole);
            return Optional.ofNullable(actualPlanList);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil actual plan by actual code dan rute role", e);
        }
    }

    @Override
    public Optional<List<String>> loadDistinctStationOrderByActualCode(Date actualCodeDate) throws Exception {
        try {
            List<String> stationList = actualPlanRepository.findDistinctStatiunByActualCode(actualCodeDate);
            return Optional.of(stationList);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil distinct station order by actual code", e);
        }
    }

    @Override
    public Optional<Date> loadMinimumArrivalActualPlanByActualCode(Date actualCode) throws Exception {
        try {
            Date minimumDateArrivalActual = actualPlanRepository.findMinimumArriveActualPlanByActualCode(actualCode);
            return Optional.ofNullable(minimumDateArrivalActual);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil minimum arrive actual berdasarkan actual code", e);
        }
    }

    @Override
    public Optional<Date> loadMaximumDepartActualPlanByActualCode(Date actualCode) throws Exception {
        try {
            Date maximumDateDepartActual = actualPlanRepository.findMaximumDepartActualPlanByActualCode(actualCode);
            return Optional.ofNullable(maximumDateDepartActual);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil maximum depart actual berdasarkan actual code", e);
        }
    }

    @Override
    public Optional<List<String>> loadDistinctTrainOrderByActualCode(Date actualCodeDate) throws Exception {
        try {
            List<String> trainList = actualPlanRepository.findDistinctTrainByActualCode(actualCodeDate);
            return Optional.ofNullable(trainList);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil train berdasarkan actual code", e);
        }
    }

    @Override
    public Optional<List<ActualPlan>> loadActualPlanByActualCodeAndTypePlan(Date actualCode, String typePlan) throws Exception {
        try {
            List<ActualPlan> actualPlanList = actualPlanRepository.findActualPlansByActualCodeAndTypePlan(actualCode, typePlan);
            return Optional.ofNullable(actualPlanList);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil actual plan berdasarkan actual code dan type plan", e);
        }
    }
}
