package len.silvue.webpendukung.schedule.application.services;

import len.silvue.webpendukung.domains.*;
import len.silvue.webpendukung.schedule.adapter.in.web.AddMasterPlanCommand;
import len.silvue.webpendukung.schedule.adapter.out.persistence.repositories.TrainRepository;
import len.silvue.webpendukung.schedule.application.port.in.AddMasterPlanUseCase;
import len.silvue.webpendukung.schedule.application.port.out.*;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class AddMasterPlanService implements AddMasterPlanUseCase {
    private final LoadPeronPort loadPeronPort;
    private final LoadTrainPort loadTrainPort;
    private final LoadTypeMasterPlanPort loadTypeMasterPlanPort;
    private final LoadRuteRolePort loadRuteRolePort;
    private final SaveMasterPlanPort saveMasterPlanPort;
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    private final TrainRepository trainRepository;

    @Override
    public void saveMasterPlan(AddMasterPlanCommand addMasterPlanCommand) throws Exception {
        try {
            // get type master plan
            Optional<TypeMasterPlan> optionalTypeMasterPlan = loadTypeMasterPlanPort.loadTypeMasterPlanById(addMasterPlanCommand.getIdTypeMasterPlan());
            TypeMasterPlan typeMasterPlan = optionalTypeMasterPlan.orElseThrow(DataNotFoundException::new);

            // get peron
            Optional<Peron> optionalPeron = loadPeronPort.loadPeronById(addMasterPlanCommand.getIdPeron());
            Peron peron = optionalPeron.orElseThrow(DataNotFoundException::new);

            // get train
            Optional<Train> optionalTrain = loadTrainPort.loadTrainById(addMasterPlanCommand.getIdTrain());

            // get rute role
            Optional<RuteRole> optionalRuteRole = loadRuteRolePort.loadRuteRoleById(addMasterPlanCommand.getIdRuteRole());

            // convert string to time
            Date arrivalDate = convertStringToTime(addMasterPlanCommand.getArrival());
            Date departDate = convertStringToTime(addMasterPlanCommand.getDepart());

            // calculate depart time to arrival time
            long diff = departDate.getTime() - arrivalDate.getTime();
            long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);

            if (optionalTrain.isPresent())
            {
                // add when train selected
                MasterPlan masterPlan = MasterPlan
                        .builder()
                        .train(optionalTrain.orElse(null))
                        .arrival(arrivalDate)
                        .depart(departDate)
                        .dwellingTime(seconds)
                        .typeMasterPlan(typeMasterPlan)
                        .flagMasterPlan(addMasterPlanCommand.getFlagMasterPlan())
                        .peronFrom(peron)
                        .peronTo(peron)
                        .ruteRole(optionalRuteRole.orElse(null))
                        .build();
                saveMasterPlanPort.saveMasterPlan(masterPlan);
            }
            else
            {
                // add when train not selected and add new from textbox
                Train train = Train.builder()
                       .noka(addMasterPlanCommand.getIdTrainAdd())
               .build();
                train = trainRepository.save(train);
                MasterPlan masterPlan = MasterPlan
                    .builder()
                        .train(train)
                        .arrival(arrivalDate)
                        .depart(departDate)
                        .dwellingTime(seconds)
                        .typeMasterPlan(typeMasterPlan)
                        .flagMasterPlan(addMasterPlanCommand.getFlagMasterPlan())
                        .peronFrom(peron)
                        .peronTo(peron)
                        .ruteRole(optionalRuteRole.orElse(null))
                        .build();
                saveMasterPlanPort.saveMasterPlan(masterPlan);
            }
        } catch (Exception e) {
            throw new Exception("failed to save data master plan", e);
        }
    }

    private Date convertStringToTime(String time) throws Exception {
        try {
            return sdf.parse(time);
        } catch (Exception e) {
            throw new Exception("Error make time string to date", e);
        }
    }
}
