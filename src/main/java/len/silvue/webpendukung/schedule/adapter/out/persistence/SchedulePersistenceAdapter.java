package len.silvue.webpendukung.schedule.adapter.out.persistence;

import len.silvue.webpendukung.schedule.adapter.out.persistence.repositories.ScheduleRepository;
import len.silvue.webpendukung.schedule.application.port.out.LoadSchedulePort;
import len.silvue.webpendukung.schedule.application.port.out.SaveSchedulePort;
import len.silvue.webpendukung.domains.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SchedulePersistenceAdapter implements LoadSchedulePort, SaveSchedulePort {
    private final ScheduleRepository scheduleRepository;

    @Override
    public Optional<List<Schedule>> loadAllSchedules() throws Exception {
        try {
            return Optional.of(scheduleRepository.findAll());
        } catch(Exception e) {
            throw new Exception("Gagal mengambil semua data schedule", e);
        }
    }

    @Override
    public Optional<Schedule> loadScheduleById(int id) throws Exception {
        try {
            return scheduleRepository.findById(id);
        } catch(Exception e) {
            throw new Exception("Gagal load schedule by id", e);
        }
    }

    @Override
    public Schedule storeSchedule(Schedule schedule) throws Exception {
        try {
            return scheduleRepository.save(schedule);
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan schedule", e);
        }
    }
}
