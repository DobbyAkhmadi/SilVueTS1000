package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.schedule.application.port.in.DeleteScheduleTypeUseCase;
import len.silvue.webpendukung.schedule.application.port.out.DeleteMasterPlanPort;
import len.silvue.webpendukung.schedule.application.port.out.DeleteScheduleTypePort;
import len.silvue.webpendukung.todays.application.port.out.DeleteTodayPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteScheduleTypeService implements DeleteScheduleTypeUseCase {
    private final DeleteScheduleTypePort deleteScheduleTypePort;
    private final DeleteTodayPort deleteTodayPort;
    @Override
    public void deleteScheduleType(int scheduleTypeId) throws Exception {
        try {
            deleteTodayPort.eraseTodayDetailByTypeMasterPlan(scheduleTypeId);
            deleteScheduleTypePort.eraseScheduleType(scheduleTypeId);
        } catch (Exception e) {
            throw new Exception("Gagal delete schedule type", e);
        }
    }
}
