package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.schedule.adapter.in.web.ModifyScheduleCommand;
import len.silvue.webpendukung.schedule.adapter.out.web.ScheduleDto;
import len.silvue.webpendukung.schedule.adapter.out.web.mapper.ScheduleMapper;
import len.silvue.webpendukung.schedule.application.port.in.ModifyScheduleUseCase;
import len.silvue.webpendukung.schedule.application.port.out.*;
import len.silvue.webpendukung.domains.RuteRole;
import len.silvue.webpendukung.domains.Schedule;
import len.silvue.webpendukung.domains.Train;
import len.silvue.webpendukung.domains.TypeMasterPlan;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ModifyScheduleService implements ModifyScheduleUseCase {
    private final LoadSchedulePort loadSchedulePort;
    private final LoadTrainPort loadTrainPort;
    private final LoadTypeMasterPlanPort loadTypeMasterPlanPort;
    private final LoadRuteRolePort loadRuteRolePort;
    private final SaveSchedulePort saveSchedulePort;
    private final ScheduleMapper scheduleMapper;

    @Override
    public ScheduleDto modifySchedule(ModifyScheduleCommand modifyScheduleCommand) throws Exception {
        try {
            Optional<Schedule> optionalSchedule = loadSchedulePort.loadScheduleById(modifyScheduleCommand.getScheduleId());
            Schedule schedule = optionalSchedule.orElseThrow(DataNotFoundException::new);

            Optional<Train> optionalTrain = loadTrainPort.loadTrainById(modifyScheduleCommand.getIdTrain());
            Train train = optionalTrain.orElseThrow(DataNotFoundException::new);

            Optional<TypeMasterPlan> optionalTypeMasterPlan = loadTypeMasterPlanPort.loadTypeMasterPlanById(modifyScheduleCommand.getIdTypeMasterPlan());
            TypeMasterPlan typeMasterPlan = optionalTypeMasterPlan.orElseThrow(DataNotFoundException::new);

            Optional<RuteRole> optionalRuteRole = loadRuteRolePort.loadRuteRoleById(modifyScheduleCommand.getIdRuteRole());
            RuteRole ruteRole = optionalRuteRole.orElseThrow(DataNotFoundException::new);

            schedule.setScheduleName(modifyScheduleCommand.getScheduleName());
            schedule.setTrain(train);
            schedule.setTypeMasterPlan(typeMasterPlan);
            schedule.setRuteRole(ruteRole);
            schedule.setNextDay(modifyScheduleCommand.getFlagMaster());
          //  schedule.setFlagMaster(modifyScheduleCommand.getFlagMaster());


            return scheduleMapper.toScheduleDto(saveSchedulePort.storeSchedule(schedule));
        } catch(Exception e) {
            throw new Exception("Gagal modify schedule", e);
        }
    }
}
