package len.silvue.webpendukung.todays.adapter.out.persistence;


import len.silvue.webpendukung.domains.Train;
import len.silvue.webpendukung.domains.TypeMasterPlan;
import len.silvue.webpendukung.todays.adapter.out.persistence.repositories.TodayRunningScheduleRepository;
import len.silvue.webpendukung.todays.application.port.in.FindTodayRunningSchedulePort;
import len.silvue.webpendukung.todays.application.port.out.DeleteTodayPort;
import len.silvue.webpendukung.todays.application.port.out.LoadTodayDetailPort;
import len.silvue.webpendukung.domains.TodayRunningSchedule;
import len.silvue.webpendukung.todays.application.port.out.SaveTodayDetailPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TodayRunningScheduleAdapter implements FindTodayRunningSchedulePort, SaveTodayDetailPort , LoadTodayDetailPort , DeleteTodayPort {
    private final TodayRunningScheduleRepository todayRunningScheduleRepository;

    @Override
    public Optional<List<TodayRunningSchedule>> loadAllTodayRunningSchedule() throws Exception {
        try {
            return Optional.of(todayRunningScheduleRepository.findAllOrderByTrain());
        } catch(Exception e) {
            throw new Exception("failed to get data today detail", e);
        }
    }

    @Override
    public Optional<List<TodayRunningSchedule>> loadTodayRunningScheduleByTypeMasterPlanIdAndRuteRoleId(int typeMasterPlanId, int ruteRoleId) throws Exception {
        try {
            return todayRunningScheduleRepository.findAllByTypeMasterPlanIdAndRuteRoleIdOrderByTrainIdAndStationidAndRuteRoleId(typeMasterPlanId, ruteRoleId);
        } catch(Exception e) {
            throw new Exception("failed to get today by typemasterplan id");
        }
    }

    @Override
    public Optional<List<TodayRunningSchedule>> loadTodayRunningScheduleByTypeMasterPlanId(int typeMasterPlanId) throws Exception {
        try {
            return todayRunningScheduleRepository.findAllByTypeMasterPlanId(typeMasterPlanId);
        } catch(Exception e) {
            throw new Exception("Gagal load today running schedule by typemaster plan id", e);
        }
    }


   /* @Override
    public Optional<List<TodayRunningSchedule>> loadTodayDetailToActual() throws Exception {
        try {
            return todayRunningScheduleRepository.selectTodayDetailByIdToday();
        } catch(Exception e) {
            throw new Exception("Gagal load today running schedule by typemaster plan id", e);
        }
    }*/



    @Override
    public Optional<TodayRunningSchedule> loadTodayRunningScheduleById(int id) throws Exception {
        try{
        return todayRunningScheduleRepository.findById(id);
    } catch (Exception e){
            throw new Exception("failed load Today detail by id", e);
        }
    }

    @Override
    public Optional<List<TodayRunningSchedule>> loadTodayRunningScheduleByTrain(int idTrain) throws Exception {
        try {
            Optional<List<TodayRunningSchedule>> todayRunningSchedules = todayRunningScheduleRepository.findTodayRunningScheduleByTrain(idTrain);
            return todayRunningSchedules;
        } catch(Exception e) {
            throw new Exception("failed load today by id Train", e);
        }
    }

    @Override
    public Optional<List<TodayRunningSchedule>> getFlagToday() throws Exception {
        try{
            return todayRunningScheduleRepository.getFlagToday();
        } catch (Exception e){
            throw new Exception("failed load Today detail by flag", e);
        }
    }


    @Override
    public void saveTodayRunningSchedule(TodayRunningSchedule todayRunningSchedule) throws Exception {

    }

    @Override
    public Optional<TodayRunningSchedule> storeTodayDetailSchedule(TodayRunningSchedule todayRunningSchedule) throws Exception {
        try {
            TodayRunningSchedule saveResultTodayRunningSchedule = todayRunningScheduleRepository.save(todayRunningSchedule);
            return Optional.of(saveResultTodayRunningSchedule);
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan schedule", e);
        }
    }

    @Override
    public void storeTodayDetailScheduleList(List<TodayRunningSchedule> todayRunningScheduleList) throws Exception {
        try {
            todayRunningScheduleRepository.saveAll(todayRunningScheduleList);
        } catch(Exception e) {
            throw new Exception("failed to update data Today Detail", e);
        }
    }

    @Override
    public List<TodayRunningSchedule> storeTodayList(List<TodayRunningSchedule> todayRunningScheduleList) throws Exception {
       return todayRunningScheduleRepository.saveAll(todayRunningScheduleList);
    }

    @Override
    public Optional<List<TodayRunningSchedule>> loadTodayRunningScheduleByTrainAndTypeSchedule(Train train, TypeMasterPlan typeMasterPlan) throws Exception {
        try {
            Optional<List<TodayRunningSchedule>> todayRunningScheduleList = todayRunningScheduleRepository.findTodayRunningScheduleByTrainAndTypeMasterPlan(train, typeMasterPlan);
            return todayRunningScheduleList;
        } catch(Exception e) {
            throw new Exception("Gagal load master plan by train and type Todays Detail", e);
        }
    }

    @Override
    public void eraseTodayDetailByTodayRunningSchedule(int idTodayRunningSchedule) throws Exception {
        try {
            todayRunningScheduleRepository.deleteById(idTodayRunningSchedule);
        } catch(Exception e) {
            throw new Exception("gagal to delete data Today Detail", e);
        }
    }

    @Override
    public void eraseAllTodayDetail() throws Exception {
        try {
            todayRunningScheduleRepository.deleteAll();
        } catch(Exception e) {
            throw new Exception("gagal erase all today", e);
        }
    }

    @Override
    public void eraseAllTodayDetailByTrainId(int trainId) throws Exception {
        try {
            todayRunningScheduleRepository.deleteTodayDetailByTrain(trainId);
        } catch(Exception e) {
            throw new Exception("gagal erase all by train", e);
        }
    }

    @Override
    public void eraseTodayDetailByTypeMasterPlan(int idTypeMasterPlan) throws Exception {
        try {
            todayRunningScheduleRepository.deleteTodayRunningScheduleByTypeMasterPlan(idTypeMasterPlan);
        } catch(Exception e) {
            throw new Exception("gagal erase today detail by type master plan", e);
        }
    }
}
