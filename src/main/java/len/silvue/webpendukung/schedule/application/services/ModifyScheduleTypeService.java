package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.schedule.adapter.in.web.ModifyScheduleTypeCommand;
import len.silvue.webpendukung.schedule.adapter.out.web.TypeMasterPlanDto;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.TypeMasterPlanMapper;
import len.silvue.webpendukung.schedule.application.port.in.ModifyScheduleTypeUseCase;
import len.silvue.webpendukung.schedule.application.port.out.LoadTypeMasterPlanPort;
import len.silvue.webpendukung.schedule.application.port.out.SaveScheduleTypePort;
import len.silvue.webpendukung.domains.TypeMasterPlan;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModifyScheduleTypeService implements ModifyScheduleTypeUseCase {
    private final SaveScheduleTypePort saveScheduleTypePort;
    private final LoadTypeMasterPlanPort loadTypeMasterPlanPort;
    private final TypeMasterPlanMapper typeMasterPlanMapper;

    @Override
    public TypeMasterPlanDto modifyScheduleType(ModifyScheduleTypeCommand modifyScheduleTypeCommand) throws Exception {
        try {
            Optional<TypeMasterPlan> optionalTypeMasterPlan = loadTypeMasterPlanPort.loadTypeMasterPlanById(modifyScheduleTypeCommand.getIdTypeMasterPlan());
            TypeMasterPlan typeMasterPlan = optionalTypeMasterPlan.orElseThrow(DataNotFoundException::new);

            typeMasterPlan.setNameTypeMasterPlan(modifyScheduleTypeCommand.getScheduleTypeName());

            saveScheduleTypePort.storeScheduleType(typeMasterPlan);
            return typeMasterPlanMapper.toTypeMasterPlanDto(typeMasterPlan);
        } catch (Exception e) {
            throw new Exception("Gagal mengubah schedule type", e);
        }
    }
}
