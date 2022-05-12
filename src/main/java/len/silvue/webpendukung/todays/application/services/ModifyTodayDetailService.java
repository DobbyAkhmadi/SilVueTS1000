package len.silvue.webpendukung.todays.application.services;


import len.silvue.webpendukung.schedule.application.port.out.*;
import len.silvue.webpendukung.todays.adapter.in.ModifyTodayCommand;
import len.silvue.webpendukung.todays.application.port.in.FindTodayRunningSchedulePort;
import len.silvue.webpendukung.domains.RuteRole;
import len.silvue.webpendukung.domains.Train;
import len.silvue.webpendukung.domains.TypeMasterPlan;
import len.silvue.webpendukung.todays.adapter.out.web.TodayRunningScheduleDto;
import len.silvue.webpendukung.todays.adapter.out.web.mapper.TodayRunningScheduleMapper;
import len.silvue.webpendukung.todays.application.port.in.ModifyTodayDetailUseCase;
import len.silvue.webpendukung.todays.application.port.out.SaveTodayDetailPort;
import len.silvue.webpendukung.domains.TodayRunningSchedule;
import len.silvue.webpendukung.utility.DataFormat;
import len.silvue.webpendukung.utility.DataNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j

public class ModifyTodayDetailService implements ModifyTodayDetailUseCase {

    private final FindTodayRunningSchedulePort loadTodayDetailSchedulePort;
    private final LoadTrainPort loadTrainPort;
    private final LoadTypeMasterPlanPort loadTypeMasterPlanPort;
    private final LoadRuteRolePort loadRuteRolePort;
    private final SaveTodayDetailPort saveTodayDetailPort;
    private final TodayRunningScheduleMapper todayRunningScheduleMapper;


    @Override
    public TodayRunningScheduleDto modifyTodayRunningScheduleType(ModifyTodayCommand modifyTodayCommand) throws Exception {
        try {
            log.info("Data id today running schedule: " + modifyTodayCommand.getIdTodayRunningSchedule());
            Optional<TodayRunningSchedule> optionalTodayRunningSchedule =
                    loadTodayDetailSchedulePort.loadTodayRunningScheduleById(modifyTodayCommand.getIdTodayRunningSchedule());
            TodayRunningSchedule TodayRunningSchedule =
                    optionalTodayRunningSchedule.orElseThrow(DataNotFoundException::new);
            Optional<Train> optionalTrain = loadTrainPort.loadTrainById(modifyTodayCommand.getIdTrain());
                Train train = optionalTrain.orElseThrow(DataNotFoundException::new);
            Optional<RuteRole> optionalRuteRole =
                    loadRuteRolePort.loadRuteRoleById(modifyTodayCommand.getIdRuteRole());
            RuteRole ruteRole =
                    optionalRuteRole.orElseThrow(DataNotFoundException::new);

            Optional<TypeMasterPlan> optionalTypeMasterPlan =
                    loadTypeMasterPlanPort.loadTypeMasterPlanById(modifyTodayCommand.getIdTypeMasterPlan());
            TypeMasterPlan typeMasterPlan =
                    optionalTypeMasterPlan.orElseThrow(DataNotFoundException::new);



            TodayRunningSchedule.setTrain(train);
            TodayRunningSchedule.setRuteRole(ruteRole);
            TodayRunningSchedule.setTypeMasterPlan(typeMasterPlan);
            TodayRunningSchedule.setArrival(DataFormat.changeStrTimeToDate(modifyTodayCommand.getArrival()));
            TodayRunningSchedule.setDepart(DataFormat.changeStrTimeToDate(modifyTodayCommand.getDepart()));

            saveTodayDetailPort.storeTodayDetailSchedule(TodayRunningSchedule);
            return todayRunningScheduleMapper.toTodayRunningScheduleDto(TodayRunningSchedule);
        } catch (Exception e) {
            throw new Exception("failed to modify today detail", e);
        }
    }

    @Override
    public List<TodayRunningScheduleDto> modifyAllTodayDetailScheduleType(ModifyTodayCommand modifyTodayCommand) throws Exception {
        try {

            Optional<List<TodayRunningSchedule>> optionalTodayRunningSchedule =
                    loadTodayDetailSchedulePort.loadTodayRunningScheduleByTrain(modifyTodayCommand.getIdTrainSelect());
            List<TodayRunningSchedule> optionalTodayRunningSchedulesList =  optionalTodayRunningSchedule.orElseThrow(DataNotFoundException::new);

            Optional<Train> optionalTrain = loadTrainPort.loadTrainById(modifyTodayCommand.getIdTrain());
            Train train = optionalTrain.orElseThrow(DataNotFoundException::new);

            Optional<RuteRole> optionalRuteRole = loadRuteRolePort.loadRuteRoleById(modifyTodayCommand.getIdRuteRole());
            RuteRole ruteRole = optionalRuteRole.orElseThrow(DataNotFoundException::new);

            Optional<TypeMasterPlan> optionalTypeMasterPlan = loadTypeMasterPlanPort.loadTypeMasterPlanById(modifyTodayCommand.getIdTypeMasterPlan());
            TypeMasterPlan typeMasterPlan = optionalTypeMasterPlan.orElseThrow(DataNotFoundException::new);
            log.info("id"+modifyTodayCommand.getIdTrain());
            log.info("selected"+modifyTodayCommand.getIdTrainSelect());
            for(TodayRunningSchedule trs : optionalTodayRunningSchedulesList) {

                trs.setTrain(train);
                trs.setRuteRole(ruteRole);
                trs.setTypeMasterPlan(typeMasterPlan);
            }

            saveTodayDetailPort.storeTodayList(optionalTodayRunningSchedulesList);
            return todayRunningScheduleMapper.toTodayRunningScheduleDtoList(optionalTodayRunningSchedulesList);
        } catch(Exception e) {
            throw new Exception("Gagal modify todaydetailschedule", e);
        }
    }
}
