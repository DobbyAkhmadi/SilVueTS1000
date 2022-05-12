package len.silvue.webpendukung.actual.application.service;


import len.silvue.webpendukung.actual.adapter.in.web.ModifyActualCommand;
import len.silvue.webpendukung.actual.adapter.out.web.ActualModifyDto;
import len.silvue.webpendukung.actual.adapter.out.web.ActualRunningScheduleDto;
import len.silvue.webpendukung.actual.adapter.out.web.mapper.ActualRunningScheduleMapper;
import len.silvue.webpendukung.actual.application.port.in.ModifyActualTypeUseCase;
import len.silvue.webpendukung.actual.application.port.out.*;
import len.silvue.webpendukung.domains.ActualPlan;
import len.silvue.webpendukung.domains.Departement;
import len.silvue.webpendukung.domains.Problem;

import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ModifyActualScheduleService implements ModifyActualTypeUseCase {

    private final FindActualRunningSchedulePort loadActualSchedulePort;

    private final SaveActualSchedulePort saveActualSchedulePort;
    private final ActualRunningScheduleMapper actualRunningScheduleMapper;
    private final LoadDepartementPort loadDepartementPort;
    private final LoadProblemPort loadProblemPort;
    private final LoadActualSchedulePort loadActualTrainPort;
    private final DeleteActualPort deleteActualPort;

    @Override
    public ActualModifyDto modifyActualScheduleType(ModifyActualCommand modifyActualCommand) throws Exception {
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



          //  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String isoDatePatterns = "yyyy-MM-dd";

            String isoDatePattern = "yyyy-MM-dd'T'HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);
            SimpleDateFormat simpleDateFormats = new SimpleDateFormat(isoDatePatterns);

            Date dateString = simpleDateFormat.parse(modifyActualCommand.getArriveActualPlan());
            Date dateString2 = simpleDateFormat.parse(modifyActualCommand.getArriveSchedule());
            Date dateString3 = simpleDateFormat.parse(modifyActualCommand.getDepartActualPlan());
            Date dateString4 = simpleDateFormat.parse(modifyActualCommand.getDepartSchedule());


            ActualPlan.setTrainActualPlan(modifyActualCommand.getTrainActualPlan());
            ActualPlan.setArriveActualPlan(dateString);
            ActualPlan.setArriveSchedule(dateString2);
            ActualPlan.setDepartement(departement);
            ActualPlan.setProblem(problem);
            ActualPlan.setComments(modifyActualCommand.getComments());
            ActualPlan.setDepartActualPlan(dateString3);
            ActualPlan.setDepartSchedule(dateString4);


            saveActualSchedulePort.storeActualSchedule(ActualPlan);
            ActualRunningScheduleDto actualRunningScheduleDto = actualRunningScheduleMapper.toActualRunningScheduleDto(ActualPlan);

            ActualModifyDto actualModifyDto = ActualModifyDto.builder()
                    .idActualPlan(actualRunningScheduleDto.getIdActualPlan())
                    .timeData(actualRunningScheduleDto.getTimeData())
                    .trainActualPlan(actualRunningScheduleDto.getTrainActualPlan())
                    .ruteRoleActualPlan(actualRunningScheduleDto.getRuteRoleActualPlan())
                    .statiunActualPlan(actualRunningScheduleDto.getStatiunActualPlan())
                    .platformActualPlan(actualRunningScheduleDto.getPlatformActualPlan())
                    .platformSchedulePlan(actualRunningScheduleDto.getPlatformSchedulePlan())
                    .actualCode(actualRunningScheduleDto.getActualCode() != null ? simpleDateFormat.format(actualRunningScheduleDto.getActualCode()) : "")
                    .arriveActualPlan(actualRunningScheduleDto.getArriveActualPlan() != null ? simpleDateFormats.format(actualRunningScheduleDto.getArriveActualPlan()) : "")
                    .departActualPlan(actualRunningScheduleDto.getDepartActualPlan() != null ? simpleDateFormat.format(actualRunningScheduleDto.getDepartActualPlan()) : "")
                    .arriveSchedule(actualRunningScheduleDto.getArriveSchedule() != null ? simpleDateFormat.format(actualRunningScheduleDto.getArriveSchedule()) : "")
                    .departSchedule(actualRunningScheduleDto.getDepartSchedule() != null ? simpleDateFormat.format(actualRunningScheduleDto.getDepartSchedule()) : null)
                    .typePlan(actualRunningScheduleDto.getTypePlan())
                    .statusActualPlan(actualRunningScheduleDto.getStatusActualPlan())
                    .delayActualPlan(actualRunningScheduleDto.getDelayActualPlan())
                    .comments(actualRunningScheduleDto.getComments())
                    .departmentActual(actualRunningScheduleDto.getDepartmentActual())
                    .vehicleTrainActualPlan(actualRunningScheduleDto.getVehicleTrainActualPlan())
                    .flagActualPlan(actualRunningScheduleDto.isFlagActualPlan())
                    .indexActual(actualRunningScheduleDto.getIndexActual())
                    .departement(actualRunningScheduleDto.getDepartement())
                    .problem(actualRunningScheduleDto.getProblem())
                    .build();
            return actualModifyDto;
        } catch (Exception e) {
            throw new Exception("failed to modify actual schedule", e);
        }
    }

    @Override
    public List<ActualRunningScheduleDto> modifyAllActualScheduleType(ModifyActualCommand modifyActualCommand) throws Exception {
        try {

            Optional<List<ActualPlan>> optionalPrevActualPlan =
                    loadActualTrainPort.loadActualScheduleByTrain(modifyActualCommand.getTempActualPlan());
            List<ActualPlan> prevActualPlanList =  optionalPrevActualPlan.orElseThrow(DataNotFoundException::new);

            Optional<List<ActualPlan>> optionalTargetActualPlanList = loadActualTrainPort.loadActualScheduleByTrain(modifyActualCommand.getTrainActualPlan());
            List<ActualPlan> targetActualPlanList = optionalTargetActualPlanList.orElse(new ArrayList<>());


            List<ActualPlan> actualPlansToDelete = new ArrayList<>();
            List<ActualPlan> actualPlanListToSave = prevActualPlanList.stream().filter(prevActualPlan -> {
               boolean doubleActualPlan = true;
               for(ActualPlan actualPlan : targetActualPlanList) {
                   if((actualPlan.getStatiunActualPlan().compareToIgnoreCase(prevActualPlan.getStatiunActualPlan()) == 0)
                       && ((actualPlan.getArriveActualPlan() == null && prevActualPlan.getArriveActualPlan() == null)
                           || (actualPlan.getArriveActualPlan() != null
                                       && actualPlan.getArriveActualPlan().compareTo(prevActualPlan.getArriveActualPlan()) == 0))
                   && ((actualPlan.getDepartActualPlan() == null && prevActualPlan.getDepartActualPlan() == null)
                   || (actualPlan.getDepartActualPlan() != null
                           && (actualPlan.getDepartActualPlan().compareTo(prevActualPlan.getDepartActualPlan())  == 0)))) {
                           doubleActualPlan = false;
                           actualPlansToDelete.add(prevActualPlan);
                           break;
                   }
               }
               return doubleActualPlan;
            })
                    .peek(actualPlan -> actualPlan.setTrainActualPlan(modifyActualCommand.getTrainActualPlan()))
                    .collect(Collectors.toList());
            saveActualSchedulePort.storeActualScheduleList(actualPlanListToSave);
            deleteActualPort.eraseAllActualPlans(actualPlansToDelete);
            return actualRunningScheduleMapper.toActualRunningScheduleDtoList(actualPlanListToSave);
        } catch (Exception e){
            throw new Exception("failed to modify all train", e);
        }
    }
}
