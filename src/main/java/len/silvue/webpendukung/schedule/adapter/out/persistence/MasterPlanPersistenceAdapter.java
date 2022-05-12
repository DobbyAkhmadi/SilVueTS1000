package len.silvue.webpendukung.schedule.adapter.out.persistence;

import len.silvue.webpendukung.domains.*;
import len.silvue.webpendukung.schedule.adapter.out.persistence.repositories.MasterPlanRepository;
import len.silvue.webpendukung.schedule.application.port.out.DeleteMasterPlanPort;
import len.silvue.webpendukung.schedule.application.port.out.LoadMasterPlanPort;
import len.silvue.webpendukung.schedule.application.port.out.LoadMasterSchedulePort;
import len.silvue.webpendukung.schedule.application.port.out.SaveMasterPlanPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class MasterPlanPersistenceAdapter implements SaveMasterPlanPort, LoadMasterPlanPort, DeleteMasterPlanPort ,LoadMasterSchedulePort{
    private final MasterPlanRepository masterPlanRepository;

    @Override
    public void saveMasterPlan(MasterPlan masterPlan) throws Exception {
        try {
            masterPlanRepository.save(masterPlan);
        } catch (Exception e) {
            throw new Exception("Gagal menyimpan master plan", e);
        }
    }

    @Override
    public Optional<MasterPlan> storeMasterPlan(MasterPlan masterPlan) throws Exception {
        try {
            MasterPlan saveResultMasterPlan = masterPlanRepository.save(masterPlan);
            return Optional.of(saveResultMasterPlan);
        } catch(Exception e) {
            throw new Exception("failed to update data masterplan", e);
        }
    }

    @Override
    public List<MasterPlan> storeMasterPlanList(List<MasterPlan> masterPlan) throws Exception {
        try {
            return masterPlanRepository.saveAll(masterPlan);
        } catch(Exception e) {
            throw new Exception("failed to update data masterplan", e);
        }
    }

    @Override
    public Optional<List<MasterPlan>> loadAllMasterPlan() throws Exception {
        try {
            return Optional.of(masterPlanRepository.findAllOrderByTrain());
        } catch(Exception e) {
            throw new Exception("Gagal mengambil data master plan", e);
        }
    }

    @Override
    public Optional<List<MasterPlan>> loadMasterPlanOrderByNokaAndArrivalAndDepart() throws Exception {
        try {
            return Optional.of(masterPlanRepository.findByArrivalIsNotNullOrDepartIsNotNull(Sort.by("train.noka").and(Sort.by("arrival")).and(Sort.by("depart"))));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil find all master plan order by station", e);
        }
    }

    @Override
    public Optional<List<MasterPlan>> loadMasterPlanByTypeMasterPlanAndRuteRole(TypeMasterPlan typeMasterPlan, RuteRole ruteRole) throws Exception {
        try {
            return Optional.of(masterPlanRepository.findByTypeMasterPlanAndRuteRole(typeMasterPlan, ruteRole));
        } catch (Exception e) {
            throw new Exception("Gagal mengambil master plan by type master plan dan rute role", e);
        }
    }

//    @Override
//    public void eraseMasterPlan(int masterPlanId) throws Exception {
//        try {
//            masterPlanRepository.deleteById(masterPlanId);
//        } catch(Exception e) {
//            throw new Exception("gagal erase masterplan by id", e);
//        }
//    }

    @Override
    public Optional<List<Train>> loadTrainsFromMasterPlanByTypeMasterPlanAndRuteRole(TypeMasterPlan typeMasterPlan, RuteRole ruteRole) throws Exception {
        try {
            return Optional.of(masterPlanRepository.findDistinctTrainsFromMasterPlanByTypeMasterPlanAndRuteRole(typeMasterPlan, ruteRole));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil train from masterplan", e);
        }
    }

    @Override
    public Optional<Date> loadMinimumArrivalFromMasterPlanByTypeMasterPlanAndRuteRole(TypeMasterPlan typeMasterPlan, RuteRole ruteRole) throws Exception {
        try {
            log.info("type masterplan " + typeMasterPlan);
            log.info("ruterole " + ruteRole);
            return Optional.of(masterPlanRepository.findMinimumArrivalFromMasterPlanByTypeMasterPlanAndRuteRole(typeMasterPlan, ruteRole));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil minimum arrival", e);
        }
    }

    @Override
    public Optional<Date> loadMaximumDepartFromMasterPlanByTypeMasterPlanAndRuteRole(TypeMasterPlan typeMasterPlan, RuteRole ruteRole) throws Exception {
        try {
            return Optional.of(masterPlanRepository.findMaximumDepartFromMasterPlanByTypeMasterPlanAndRuteRole(typeMasterPlan, ruteRole));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil maximum depart", e);
        }
    }

    @Override
    public Optional<List<MasterPlan>> loadMasterPlanByTypeMasterPlanAndRuteRoleAndTrain(TypeMasterPlan typeMasterPlan, RuteRole ruteRole, Train train) throws Exception {
        try {
            return Optional.of(masterPlanRepository.findMasterPlanByTypeMasterPlanAndRuteRoleAndTrain(typeMasterPlan, ruteRole, train));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil master plan berdasarkan typemasterplan ruterole dan train", e);
        }
    }

    @Override
    public Optional<List<Station>> loadStationsFromMasterPlanByTypeMasterPlanAndRuteRole(TypeMasterPlan typeMasterPlan, RuteRole ruteRole) throws Exception {
        try {
            return Optional.of(masterPlanRepository.findDistinctStationsFromMasterPlanByTypeMasterPlanAndRuteRole(typeMasterPlan, ruteRole));
        } catch (Exception e) {
            throw new Exception("Gagal mengambil stasiun di master plan berdasarkan type master dan rute role", e);
        }
    }

    @Override
    public Optional<List<MasterPlan>> loadMasterPlanByTrain(int idTrain) throws Exception {
        try {
            Optional<List<MasterPlan>> masterPlans = masterPlanRepository.findMasterPlanByTrain(idTrain);
            return masterPlans;
        } catch(Exception e) {
            throw new Exception("failed load masterPlan by id Train", e);
        }
    }

    @Override
    public Optional<MasterPlan> loadMasterPlanById(int idMasterPlan) throws Exception {
        try {
            Optional<MasterPlan> optionalMasterPlan = masterPlanRepository.findById(idMasterPlan);
            return optionalMasterPlan;
        } catch(Exception e) {
            throw new Exception("Gagal load master plan by id", e);
        }
    }

    @Override
    public Optional<List<MasterPlan>> loadAllMasterPlanByTypeMasterPlan(int idTypeMasterPlan) throws Exception {
        try {
            Optional<List<MasterPlan>> masterPlans = masterPlanRepository.findMasterPlanByTypeMasterPlan(idTypeMasterPlan);
            return masterPlans;
        } catch(Exception e) {
            throw new Exception("failed load masterPlan by id Train", e);
        }
    }

    @Override
    public Optional<MasterPlan> loadMasterPlanByTypeMasterPlan(int idMasterPlan) throws Exception {
        try {
            return masterPlanRepository.findById(idMasterPlan);
        } catch(Exception e) {
            throw new Exception("failed load masterPlan by id", e);
        }
    }

    @Override
    public Optional<List<MasterPlan>> loadMasterPlanByTrainAndTypeMasterPlan(Train train, TypeMasterPlan typeMasterPlan) throws Exception {
        try {
            Optional<List<MasterPlan>> masterPlans = masterPlanRepository.findMasterPlanByTrainAndTypeMasterPlan(train, typeMasterPlan);
            return masterPlans;
        } catch(Exception e) {
            throw new Exception("Gagal load master plan by train and type masterPlan", e);
        }
    }

    @Override
    public Optional<List<MasterPlan>> loadAllMasterPlanByIDAAndB(int idTypeMasterPlanA, int idTypeMasterPlanB) throws Exception {
        try {
            Optional<List<MasterPlan>> masterPlanList = masterPlanRepository.findMasterPlanByIdMasterPlanAndIdMasterPlan(idTypeMasterPlanA,idTypeMasterPlanB);
            return masterPlanList;
        } catch(Exception e) {
            throw new Exception("failed load masterPlan by id", e);
        }
    }

    @Override
    public void eraseMasterPlanByTrainId(int trainId) throws Exception {
        try {
            masterPlanRepository.deleteById(trainId);
        } catch(Exception e) {
            throw new Exception("failed erase masterPlan by id", e);
        }
    }

    @Override
    public void eraseAllMasterPlan() throws Exception {
        try {
            masterPlanRepository.deleteAll();
        } catch(Exception e) {
            throw new Exception("gagal erase all masterplan", e);
        }
    }

    @Override
    public void eraseAllMasterPlanByTrainId(int trainId) throws Exception {
        try {
            masterPlanRepository.deleteMasterPlanByTrain(trainId);
        } catch(Exception e) {
            throw new Exception("failed erase all masterPlan", e);
        }
    }

    @Override
    public Optional<List<MasterPlan>> loadMasterScheduleByTypeMasterPlan(int idTypeMasterPlan) throws Exception {
        try {
            Optional<List<MasterPlan>> masterScheduleByTypeMasterPlan=masterPlanRepository.findDistinctByTypeMasterPlan(idTypeMasterPlan);
            return masterScheduleByTypeMasterPlan;
        } catch(Exception e) {
            throw new Exception("failed load masterPlan by type master plan", e);
        }
    }

    @Override
    public void eraseAllMasterPlanByTypeMasterPlanName(String typeMasterPlanName) throws Exception {
        try {
            masterPlanRepository.deleteAllByTypeMasterPlanName(typeMasterPlanName);
        } catch(Exception e) {
            throw new Exception("Gagal menghapus masterplan berdasarkan typemasterplan name", e);
        }
    }

    @Override
    public void eraseAllMasterPlanByTypeMasterPlan(TypeMasterPlan typeMasterPlan) throws Exception {
        try {
            masterPlanRepository.deleteAllByTypeMasterPlan(typeMasterPlan);
        } catch(Exception e) {
            throw new Exception("Gagal menghapus masterplan berdasarkan typemasterplan", e);
        }
    }
}
