package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.schedule.adapter.in.web.AddScheduleTypeCommand;
import len.silvue.webpendukung.schedule.adapter.out.web.TypeMasterPlanDto;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.TypeMasterPlanMapper;
import len.silvue.webpendukung.schedule.application.port.in.AddScheduleTypeUseCase;
import len.silvue.webpendukung.schedule.application.port.out.SaveScheduleTypePort;
import len.silvue.webpendukung.domains.TypeMasterPlan;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddScheduleTypeService implements AddScheduleTypeUseCase {
    private final SaveScheduleTypePort saveScheduleTypePort;
    private final TypeMasterPlanMapper typeMasterPlanMapper;

    @Override
    public TypeMasterPlanDto savaScheduleType(AddScheduleTypeCommand addScheduleTypeCommand) throws Exception {
        try {
            TypeMasterPlan typeMasterPlanToSave = TypeMasterPlan.builder()
                    .nameTypeMasterPlan(addScheduleTypeCommand.getScheduleTypeName())
                    .build();
            Optional<TypeMasterPlan> optionalTypeMasterPlan = saveScheduleTypePort.storeScheduleType(typeMasterPlanToSave);

            return typeMasterPlanMapper.toTypeMasterPlanDto(optionalTypeMasterPlan.orElseThrow(DataNotFoundException::new));
        } catch(Exception e) {
            throw new Exception("Gagal menyimpan schedule type", e);
        }
    }
}
