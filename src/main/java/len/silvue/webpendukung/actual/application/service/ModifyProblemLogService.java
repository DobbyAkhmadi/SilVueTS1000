package len.silvue.webpendukung.actual.application.service;


import len.silvue.webpendukung.actual.adapter.in.web.ModifyActualCommand;
import len.silvue.webpendukung.actual.adapter.out.web.ActualModifyDto;
import len.silvue.webpendukung.actual.adapter.out.web.ActualRunningScheduleDto;
import len.silvue.webpendukung.actual.adapter.out.web.mapper.ActualRunningScheduleMapper;
import len.silvue.webpendukung.actual.application.port.in.ModifyProblemLogUseCase;
import len.silvue.webpendukung.actual.application.port.out.FindActualRunningSchedulePort;
import len.silvue.webpendukung.actual.application.port.out.LoadDepartementPort;
import len.silvue.webpendukung.actual.application.port.out.LoadProblemPort;
import len.silvue.webpendukung.actual.application.port.out.SaveActualSchedulePort;
import len.silvue.webpendukung.domains.ActualPlan;
import len.silvue.webpendukung.domains.Problem;
import len.silvue.webpendukung.domains.Departement;
import len.silvue.webpendukung.schedule.application.port.out.LoadRuteRolePort;
import len.silvue.webpendukung.schedule.application.port.out.LoadTrainPort;
import len.silvue.webpendukung.schedule.application.port.out.LoadTypeMasterPlanPort;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ModifyProblemLogService implements ModifyProblemLogUseCase {

    private final FindActualRunningSchedulePort loadActualSchedulePort;
    private final LoadTrainPort loadTrainPort;
    private final LoadTypeMasterPlanPort loadTypeMasterPlanPort;
    private final LoadRuteRolePort loadRuteRolePort;
    private final SaveActualSchedulePort saveActualSchedulePort;
    private final ActualRunningScheduleMapper actualRunningScheduleMapper;
    private final LoadDepartementPort loadDepartementPort;
    private final LoadProblemPort loadProblemPort;


    @Override
    public ActualModifyDto modifyProblemLogType(ModifyActualCommand modifyActualCommand) throws Exception {
        try {
            Optional<ActualPlan> optionalActualRunningSchedule =
                    loadActualSchedulePort.loadActualRunningScheduleById(modifyActualCommand.getIdActualPlan());
            ActualPlan ActualPlan =
                    optionalActualRunningSchedule.orElseThrow(DataNotFoundException::new);

            Optional<Departement> optionalDepartement =
                    loadDepartementPort.loadDepartementById(modifyActualCommand.getIdDepartement());
            Departement departement =
                    optionalDepartement.orElseThrow(DataNotFoundException::new);

            Optional<Problem> optionalProblem =
                    loadProblemPort.loadProblemById(modifyActualCommand.getIdDepartement());
            Problem problem =
                    optionalProblem.orElseThrow(DataNotFoundException::new);

            ActualPlan.setDepartement(departement);
            ActualPlan.setProblem(problem);
            ActualPlan.setComments(modifyActualCommand.getComments());

            saveActualSchedulePort.storeActualSchedule(ActualPlan);
            ActualRunningScheduleDto actualRunningScheduleDto = actualRunningScheduleMapper.toActualRunningScheduleDto(ActualPlan);

            ActualModifyDto actualModifyDto = ActualModifyDto.builder()
                    .idActualPlan(actualRunningScheduleDto.getIdActualPlan())
                    .comments(actualRunningScheduleDto.getComments())
                    .departement(actualRunningScheduleDto.getDepartement())
                    .problem(actualRunningScheduleDto.getProblem())
                    .build();
            return actualModifyDto;
        } catch (Exception e) {
            throw new Exception("failed to modify problem service", e);
        }
    }

    @Override
    public ActualRunningScheduleDto modifyAllProblemLogType(ModifyActualCommand modifyActualCommand) throws Exception {
        return null;
    }

    @Override
    public ActualRunningScheduleDto modifyAllProblemLogType(int IdActualPlan) throws Exception {
        try {
            Optional<ActualPlan> optionalActualRunningSchedule =
                    loadActualSchedulePort.loadActualRunningScheduleById(IdActualPlan);
            ActualPlan ActualPlan =
                    optionalActualRunningSchedule.orElseThrow(DataNotFoundException::new);

            ActualPlan.setComments(null);
            ActualPlan.setDepartement(null);
            ActualPlan.setProblem(null);

            saveActualSchedulePort.storeActualSchedule(ActualPlan);
            return actualRunningScheduleMapper.toActualRunningScheduleDto(ActualPlan);
        }catch (Exception e){
            throw new Exception("failed to delete problem service", e);
        }

    }
}
