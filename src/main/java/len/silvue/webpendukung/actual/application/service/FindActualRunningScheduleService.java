package len.silvue.webpendukung.actual.application.service;

import len.silvue.webpendukung.actual.adapter.out.web.ActualModifyDto;
import len.silvue.webpendukung.actual.application.port.out.LoadActualSchedulePort;
import len.silvue.webpendukung.domains.ActualPlan;
import len.silvue.webpendukung.actual.adapter.out.web.ActualRunningScheduleDto;
import len.silvue.webpendukung.actual.adapter.out.web.mapper.ActualRunningScheduleMapper;
import len.silvue.webpendukung.actual.application.port.in.ActualRunningScheduleUseCase;
import len.silvue.webpendukung.actual.application.port.out.FindActualRunningSchedulePort;

import len.silvue.webpendukung.schedule.application.port.out.LoadMasterPlanPort;
import len.silvue.webpendukung.schedule.application.port.out.LoadTypeMasterPlanPort;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindActualRunningScheduleService implements ActualRunningScheduleUseCase {
    private final FindActualRunningSchedulePort port;
    private final ActualRunningScheduleMapper mapper;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private SimpleDateFormat simpleDateFormats = new SimpleDateFormat("yyyy-MM-dd");
    private final LoadActualSchedulePort loadActualPort;
    private final LoadTypeMasterPlanPort loadTypeMasterPlanPort;
    private final LoadMasterPlanPort loadMasterPlanPort;
    private final LoadActualSchedulePort loadtrainbyid;


    @Override
    public List<ActualRunningScheduleDto> getAllActualRunningScheduleByIndex(int index) throws Exception {
        try {
            Optional<List<ActualPlan>> optionalActualRunningSchedule = port.loadAllActualRunningSchedule(index);
            return mapper.toActualRunningScheduleDtoList(optionalActualRunningSchedule.orElseThrow());
        } catch(Exception e) {
            throw new DataNotFoundException("Data Actual Plan kosong", e);
        }
    }

    @Override
    public List<ActualRunningScheduleDto> getAllActualRunningByActualCode(String actualCodeFrom, String actualCodeTo) throws Exception {
       try {
           SimpleDateFormat actualfrom = new SimpleDateFormat("yyyy-MM-dd");
           Date from = actualfrom.parse(actualCodeFrom);
           SimpleDateFormat actualTo = new SimpleDateFormat("yyyy-MM-dd");
           Date to = actualTo.parse(actualCodeTo);
           Optional<List<ActualPlan>> optionalActualRunningSchedule = port.loadAllActualRunningScheduleByActualCode(actualfrom.parse(actualCodeFrom),actualTo.parse(actualCodeTo));
           return mapper.toActualRunningScheduleDtoList(optionalActualRunningSchedule.orElseThrow());
       } catch (Exception e){
           throw new DataNotFoundException("Data Actual Plan Kosong",e);
       }
       }


    @Override
    public List<ActualRunningScheduleDto> getActualScheduleByTrainId(String trainId) throws Exception {
        try {
            Optional<List<ActualPlan>> optionalActualRunningSchedule = loadtrainbyid.loadActualScheduleByTrain(trainId);
            return mapper.toActualRunningScheduleDtoList(optionalActualRunningSchedule.orElseThrow());
        } catch(Exception e) {
            throw new DataNotFoundException("Data Actual Plan kosong", e);
        }
    }


    @Override
    public ActualModifyDto getActualRunningScheduleById(int id) throws DataNotFoundException {
        try {
            Optional<ActualPlan> optionalActualRunningSchedule = port.loadActualRunningScheduleById(id);
            ActualRunningScheduleDto actualRunningScheduleDto = mapper.toActualRunningScheduleDto(optionalActualRunningSchedule.orElse(null));
            ActualModifyDto actualModifyDto = ActualModifyDto.builder()
                    .idActualPlan(actualRunningScheduleDto.getIdActualPlan())
                    .timeData(actualRunningScheduleDto.getTimeData())
                    .trainActualPlan(actualRunningScheduleDto.getTrainActualPlan())
                    .ruteRoleActualPlan(actualRunningScheduleDto.getRuteRoleActualPlan())
                    .statiunActualPlan(actualRunningScheduleDto.getStatiunActualPlan())
                    .platformActualPlan(actualRunningScheduleDto.getPlatformActualPlan())
                    .platformSchedulePlan(actualRunningScheduleDto.getPlatformSchedulePlan())
                    .actualCode(actualRunningScheduleDto.getActualCode() != null ? simpleDateFormats.format(actualRunningScheduleDto.getActualCode()) : "")
                    .arriveActualPlan(actualRunningScheduleDto.getArriveActualPlan() != null ? simpleDateFormat.format(actualRunningScheduleDto.getArriveActualPlan()) : "")
                    .departActualPlan(actualRunningScheduleDto.getDepartActualPlan() != null ? simpleDateFormat.format(actualRunningScheduleDto.getDepartActualPlan()) : "")
                    .arriveSchedule(actualRunningScheduleDto.getArriveSchedule() != null ? simpleDateFormat.format(actualRunningScheduleDto.getArriveSchedule()) : "")
                    .departSchedule(actualRunningScheduleDto.getDepartSchedule() != null ? simpleDateFormat.format(actualRunningScheduleDto.getDepartSchedule()) : "")
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
        } catch(Exception e) {
            throw new DataNotFoundException("Gagal mengambil ActualRuunningSchedule berdasarkan id", e);
        }
    }

    @Override
    public List<ActualRunningScheduleDto> getActualPlanByActualCode(String selectActualCode) throws Exception {
        try {
            SimpleDateFormat selectActual = new SimpleDateFormat("yyyy-MM-dd");
            Date select = selectActual.parse(selectActualCode);
            Optional<List<ActualPlan>> optionalActualRunningSchedule = port.loadActualPlanByActualCode(selectActual.parse(selectActualCode));
            return mapper.toActualRunningScheduleDtoList(optionalActualRunningSchedule.orElseThrow());
            //return port.loadActualPlanByActualCode(selectActual.parse(selectActualCode)).orElse(new ArrayList<>());
        } catch (Exception e){
            throw new DataNotFoundException("Gagal mengambil data actualplan", e);
        }

    }

    @Override
    public List<Integer> getindexActual() throws Exception {
        try {
            Optional<List<Integer>> optionalActualRunningSchedule = port.getindex();
            return optionalActualRunningSchedule.orElseThrow(DataNotFoundException::new);
        } catch(Exception e) {
            throw new DataNotFoundException("Data Index Actual kosong", e);
        }
    }



    @Override
    public Date getMaximumTimeData() throws DataNotFoundException {
        try {
            Optional<Date> optionalMaximumDate = port.loadMaxTimeData();
            return optionalMaximumDate.orElseThrow(DataNotFoundException::new);
        } catch(Exception e) {
            throw new DataNotFoundException("Gagal mengambil maximum time data", e);
        }
    }

    @Override
    public List<ActualRunningScheduleDto> getAllActualByMaxIndex() throws Exception {
        try {
            List<Integer> indexs = getindexActual();
            int maxIndex = indexs.stream()
                    .mapToInt(v -> v)
                    .max().orElse(0);
            return getAllActualRunningScheduleByIndex(maxIndex);
        } catch(Exception e) {
            throw new Exception("Gagal mengambil actual by max index", e);
        }
    }

}
