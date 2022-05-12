package len.silvue.webpendukung.ars.application.services;

import len.silvue.webpendukung.ars.application.port.in.DeleteArsScheduleUseCase;
import len.silvue.webpendukung.ars.application.port.out.DeleteArsSchedulePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeleteAllArsScheduleService implements DeleteArsScheduleUseCase {
    private final DeleteArsSchedulePort deleteArsSchedulePort;

    @Override
    public void deleteAllArsSchedule() throws Exception {
        try {
            deleteArsSchedulePort.eraseAllArsSchedule();
        } catch (Exception e) {
            throw new Exception("Gagal delete all schedule ", e);
        }
    }
}
