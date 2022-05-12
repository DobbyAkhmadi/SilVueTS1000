package len.silvue.webpendukung.schedule.adapter.out.persistence;

import len.silvue.webpendukung.schedule.adapter.out.persistence.repositories.TypeMasterPlanRepository;
import len.silvue.webpendukung.schedule.application.port.out.DeleteScheduleTypePort;
import len.silvue.webpendukung.schedule.application.port.out.LoadTypeMasterPlanPort;
import len.silvue.webpendukung.schedule.application.port.out.SaveScheduleTypePort;
import len.silvue.webpendukung.domains.TypeMasterPlan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TypeMasterPlanPersistenceAdapter implements LoadTypeMasterPlanPort, SaveScheduleTypePort, DeleteScheduleTypePort {
    private final TypeMasterPlanRepository typeMasterPlanRepository;

    @Override
    public Optional<List<TypeMasterPlan>> loadAllTypeMasterPlan() throws Exception {
        try {
            return Optional.of(typeMasterPlanRepository.findAll());
        } catch(Exception e) {
            throw new Exception("Gagal mengambil semua data type master plan", e);
        }
    }

    @Override
    public Optional<TypeMasterPlan> loadTypeMasterPlanById(int idTypeMasterPlan) throws Exception {
        try {
            return typeMasterPlanRepository.findById(idTypeMasterPlan);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil type master plan by id", e);
        }
    }

    @Override
    public Optional<TypeMasterPlan> storeScheduleType(TypeMasterPlan typeMasterPlan) throws Exception {
        try {
            TypeMasterPlan saveResultTypeMasterPlan = typeMasterPlanRepository.save(typeMasterPlan);
            return Optional.of(saveResultTypeMasterPlan);
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan schedule type", e);
        }
    }

    @Override
    public void eraseScheduleType(int scheduleId) throws Exception {
        try {
            typeMasterPlanRepository.deleteById(scheduleId);
        } catch(Exception e) {
            throw new Exception("gagal erase schedule type", e);
        }
    }

    @Override
    public void eraseAllScheduleType() throws Exception {
        try {
            typeMasterPlanRepository.deleteAll();
        } catch(Exception e) {
            throw new Exception("Gagal erase type master plan", e);
        }
    }

    @Override
    public Optional<TypeMasterPlan> loadTypeMasterPlanByName(String typeMasterPlan) throws Exception {
        try {
            return typeMasterPlanRepository.findTypeMasterPlanByNameTypeMasterPlan(typeMasterPlan);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil typemasterplan berdasarkan nama", e);
        }
    }
}
