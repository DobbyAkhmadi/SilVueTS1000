package len.silvue.webpendukung.todays.application.services;

import len.silvue.webpendukung.schedule.adapter.out.web.RouteDto;
import len.silvue.webpendukung.schedule.application.port.out.LoadMasterPlanPort;
import len.silvue.webpendukung.schedule.application.port.out.LoadTrainPort;
import len.silvue.webpendukung.schedule.application.port.out.LoadTypeMasterPlanPort;
import len.silvue.webpendukung.domains.Train;
import len.silvue.webpendukung.domains.TypeMasterPlan;
import len.silvue.webpendukung.todays.adapter.out.web.TodayRunningScheduleDto;
import len.silvue.webpendukung.todays.adapter.out.web.mapper.TodayRunningScheduleMapper;
import len.silvue.webpendukung.todays.application.port.in.FindTodayRunningSchedulePort;
import len.silvue.webpendukung.todays.application.port.in.TodayRunningScheduleUseCase;
import len.silvue.webpendukung.todays.application.port.out.LoadTodayDetailPort;
import len.silvue.webpendukung.domains.TodayRunningSchedule;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindTodayRunningScheduleService implements TodayRunningScheduleUseCase{
    private List<RouteDto> routeDtoList = null;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
    private final FindTodayRunningSchedulePort findTodayRunningSchedulePort;
    private final TodayRunningScheduleMapper todayRunningScheduleMapper;
    private final LoadTodayDetailPort loadTodayDetailPort;
    private final LoadTrainPort loadTrainPort;
    private final LoadTypeMasterPlanPort loadTypeMasterPlanPort;
    private final LoadMasterPlanPort loadMasterPlanPort;
    @Override
    public List<TodayRunningScheduleDto> getAllTodayRunningSchedule() throws Exception {
        try {
            Optional<List<TodayRunningSchedule>> optionalTodayRunningSchedule = findTodayRunningSchedulePort.loadAllTodayRunningSchedule();
            return todayRunningScheduleMapper.toTodayRunningScheduleDtoList(optionalTodayRunningSchedule.orElseThrow());
        } catch(Exception e) {
            throw new DataNotFoundException("Data TodayRuunningSchedule kosong", e);
        }
    }

    @Override
    public TodayRunningScheduleDto getTodayRunningScheduleById(int id) throws DataNotFoundException {
        try {
            Optional<TodayRunningSchedule> optionalTodayRunningSchedule = findTodayRunningSchedulePort.loadTodayRunningScheduleById(id);
            return todayRunningScheduleMapper.toTodayRunningScheduleDto(optionalTodayRunningSchedule.orElse(null));
        } catch(Exception e) {
            throw new DataNotFoundException("Gagal mengambil TodayRuunningSchedule berdasarkan id", e);
        }
    }

    @Override
    public TodayRunningScheduleDto getHeadTodayRunningSchedule(int id) throws DataNotFoundException {
        return null;
    }

    @Override
    public List<TodayRunningScheduleDto> getTodayRunningScheduleByTrainIdAndTypeMasterPlanId(int trainId, int typeMasterPlanId) throws Exception {
        try {
            Optional<Train> optionalTrain = loadTrainPort.loadTrainById(trainId);
            Optional<TypeMasterPlan> optionalTypeMasterPlan = loadTypeMasterPlanPort.loadTypeMasterPlanById(typeMasterPlanId);
            Optional<List<TodayRunningSchedule>> optionalTodayRunningSchedules = loadTodayDetailPort
                    .loadTodayRunningScheduleByTrainAndTypeSchedule(optionalTrain.orElseThrow(DataNotFoundException::new),optionalTypeMasterPlan.orElseThrow(DataNotFoundException::new));
            return todayRunningScheduleMapper.toTodayRunningScheduleDtoList(optionalTodayRunningSchedules.orElseThrow(DataNotFoundException::new));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil service get master plan by trainid and type master plan id", e);
        }
    }


}
