package len.silvue.webpendukung.ars.adapter.out.persistence;

import len.silvue.webpendukung.ars.adapter.out.persistence.repositories.ArsScheduleRepository;
import len.silvue.webpendukung.ars.application.port.out.DeleteArsSchedulePort;
import len.silvue.webpendukung.ars.application.port.out.FindArsSchedulePort;
import len.silvue.webpendukung.ars.application.port.out.SaveArsDetailPort;
import len.silvue.webpendukung.domains.ArsSchedule;
import len.silvue.webpendukung.domains.SystemStatus;
import len.silvue.webpendukung.tmconfig.application.port.out.LoadSystemStatusPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ArsScheduleAdapter implements FindArsSchedulePort, DeleteArsSchedulePort, SaveArsDetailPort {
    private final ArsScheduleRepository arsScheduleRepository;
    private final LoadSystemStatusPort loadSystemStatusPort;

    @Override
    public List<ArsSchedule> loadAllArsSchedule() throws Exception{
        try {
            return arsScheduleRepository.findAllArsScheduleByScheduleStatusArsIs0();
        } catch(Exception e) {
            throw new Exception("Gagal mengambil semua arsschedule", e);
        }
    }

//    @Override
//    public void storeTodayDetailScheduleList(List<ArsSchedule> arsScheduleList) throws Exception{
//        try{
//            arsScheduleRepository.saveAll(arsScheduleList);
//        } catch(Exception e){
//        }
//    }


    @Override
    public void eraseAllArsSchedule() throws Exception {
        try {
            arsScheduleRepository.deleteAll();
        } catch(Exception e) {
            throw new Exception("gagal erase all ars schedule", e);
        }
    }


    @Override
    public void saveArsSchedule(ArsSchedule ars) throws Exception {

    }

//    @Override
//    public Optional<ArsSchedule> storeTodayDetailSchedule(ArsSchedule arsSchedule) throws Exception {
//        return Optional.empty();
//    }

    @Override
    public void storeArsDetailScheduleList(List<ArsSchedule> ars) throws Exception {
        try {
            arsScheduleRepository.saveAll(ars);
        } catch(Exception e) {
            throw new Exception("failed to update data ars", e);
        }
    }

    @Override
    public Optional<ArsSchedule> storeArsConflict(ArsSchedule arsSchedule) throws Exception {
        try {
            ArsSchedule saveResultArsConflict = arsScheduleRepository.save(arsSchedule);
            return Optional.of(saveResultArsConflict);
        } catch(Exception e) {
            throw new Exception("failed to update data masterplan", e);
        }
    }

    @Override
    public List<ArsSchedule> storeArsList(List<ArsSchedule> arsSchedule) throws Exception {
        try {
            return arsScheduleRepository.saveAll(arsSchedule);
        } catch(Exception e) {
            throw new Exception("failed to update data ars", e);
        }
    }
}

