package len.silvue.webpendukung.actual.adapter.out.persistence;

import len.silvue.webpendukung.actual.adapter.out.persistence.repositories.ActualRunningScheduleRepository;
import len.silvue.webpendukung.actual.application.port.out.DeleteActualPort;
import len.silvue.webpendukung.actual.application.port.out.FindActualRunningSchedulePort;
import len.silvue.webpendukung.actual.application.port.out.LoadActualSchedulePort;
import len.silvue.webpendukung.actual.application.port.out.SaveActualSchedulePort;
import len.silvue.webpendukung.domains.ActualPlan;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ActualRunningScheduleAdapter implements FindActualRunningSchedulePort, DeleteActualPort, SaveActualSchedulePort, LoadActualSchedulePort {
    private final ActualRunningScheduleRepository actualRepository;

    @Override
    public Optional<List<ActualPlan>> loadAllActualRunningScheduleByActualCode(Date actualCodeFrom, Date actualCodeTo) throws Exception {
        try {
             return actualRepository.findActualPlanByActualCode(actualCodeFrom,actualCodeTo);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil Data Actual Code", e);
        }
    }

    @Override
    public Optional<List<ActualPlan>> loadActualPlanByActualCode(Date selectActualCode) throws Exception {
        try{
           return actualRepository.selectActualPlanByActualCode(selectActualCode);
        }catch(Exception e){
            throw new Exception("Gagal mengambil data actual", e);
        }
    }

    @Override
    public Optional<Integer> getmaxindeactual() throws Exception {
        return actualRepository.getmaxindeactual();
    }

    @Override
    public Optional<List<Integer>> getindex() throws Exception {
        try {
            return actualRepository.findDistinctByIndexActual();
        } catch(Exception e) {
            throw new Exception("failed to get data actual plan", e);
        }
    }

    @Override
    public Optional<List<ActualPlan>> loadAllActualRunningSchedule() throws Exception {
        try {
            return Optional.of(actualRepository.findAll());
        } catch(Exception e) {
            throw new Exception("failed to get data actual plan", e);
        }
    }

    @Override
    public Optional<ActualPlan> loadActualRunningScheduleById(int id) {
        return actualRepository.findById(id);
    }

    @Override
    public Optional<Date> loadMaxTimeData() throws Exception {
        try {
            return actualRepository.findMaxDateFromActualPlan();
        } catch(Exception e) {
            throw new Exception("Gagal mengambil maximun time data", e);
        }
    }

    @Override
    public Optional<List<ActualPlan>> loadAllActualRunningSchedule(int index) throws Exception {
        try {
            Optional<List<ActualPlan>> actualPlan = actualRepository.findAllByIndexActualOrderByTrainActualPlan(index);
            return actualPlan;
        } catch(Exception e) {
            throw new Exception("failed to get data actual plan", e);
        }
    }


    @Override
    public void eraseActualScheduleByActualSchedule(int idActualPlan) throws Exception {
        try {
            actualRepository.deleteById(idActualPlan);
        } catch(Exception e) {
            throw new Exception("gagal to delete data actual plan", e);
        }
    }

    @Override
    public void eraseAllActualSchedule() throws Exception {
        try {
            actualRepository.deleteAll();
        } catch(Exception e) {
            throw new Exception("gagal erase all actual plan", e);
        }
    }

    @Override
    public void eraseAllActualPlans(List<ActualPlan> actualPlans) throws Exception {
        try {
            List<Integer> idActualPlans = actualPlans.stream().map(ActualPlan::getIdActualPlan)
                    .collect(Collectors.toList());
            actualRepository.deleteAllByIdInBatch(idActualPlans);
        } catch (Exception e) {
            throw new Exception("Gagal menghapus actual plan berdasarkan list", e);
        }
    }

    @Override
    public void eraseActualScheduleByActualSpesificDate(Date actualCode) throws Exception {
        try{
            actualRepository.deleteActualScheduleBySpesificDate(actualCode);
        }catch(Exception e){
            throw new Exception("Gagal menghapus actual Spesific Date", e);
        }
    }


    @Override
    public void saveActualSchedule(ActualPlan actual) throws Exception {
        try {
            ActualPlan saveResultActualSchedule = actualRepository.save(actual);
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan Actual Plan", e);
        }
    }

    @Override
    public Optional<ActualPlan> storeActualSchedule(ActualPlan actualSchedule) throws Exception {
        try {
            ActualPlan saveResultActualSchedule = actualRepository.save(actualSchedule);
            return Optional.of(saveResultActualSchedule);
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan Actual Plan", e);
        }
    }

    @Override
    public void storeActualScheduleList(List<ActualPlan> actualScheduleList) throws Exception {
        try {
            actualRepository.saveAll(actualScheduleList);
        } catch(Exception e) {
            throw new Exception("failed to update data Actual Schedule", e);
        }
    }

    @Override
    public Optional<List<ActualPlan>> loadActualScheduleByTrain(String trainId) throws Exception {
        try {
            return actualRepository.findActualPlanByTrainActualPlan(trainId);
        } catch (Exception e) {
            throw new Exception("failed to update data train", e);
        }
    }

    @Override
    public Optional<List<String>> loadTypePlanFromActualPlanByActualCode(Date actualCode) throws Exception {
        try {
            return actualRepository.findDistinctTypePlanByActualCode(actualCode);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil data type plan berdasarkan actual code", e);
        }
    }

    @Override
    public Optional<List<String>> loadRuteFromActualPlanByActualCode(Date actualCode) throws Exception {
        try {
            return actualRepository.findDistinctRuteRoleByActualCode(actualCode);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil rute role berdasarkan actual code", e);
        }
    }

    @Override
    public Optional<List<String>> loadAllTypePlanFromActualPlan() throws Exception {
        try {
            return actualRepository.findDistinctAllTypePlan();
        } catch(Exception e) {
            throw new Exception("Gagal mengambil semua type plan", e);
        }
    }

    @Override
    public Optional<List<String>> loadAllRuteRoleFromActualPlan() throws Exception {
        try {
            return actualRepository.findDistinctAllRuteRole();
        } catch(Exception e) {
            throw new Exception("Gagal mengambil semua rute role", e);
        }
    }
}
