package len.silvue.webpendukung.gapeka.application.services;

import len.silvue.webpendukung.gapeka.adapter.out.web.GapekaDataDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.GapekaDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.GapekaStationDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.GapekaTrainDto;
import len.silvue.webpendukung.gapeka.application.port.in.GapekaUseCase;
import len.silvue.webpendukung.gapeka.application.port.out.LoadListLinePort;
import len.silvue.webpendukung.domains.ListLine;
import len.silvue.webpendukung.domains.Train;
import len.silvue.webpendukung.todays.application.port.out.LoadTodayDetailPort;
import len.silvue.webpendukung.domains.TodayRunningSchedule;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class GapekaTodayLineService implements GapekaUseCase {
    private final LoadListLinePort loadListLinePort;
    private final LoadTodayDetailPort loadTodayDetailPort;

    private int typeMasterPlanId;
    private int lineId;
    private List<ListLine> listLines;
    private List<TodayRunningSchedule> todayListByListLine;
    private Date minimumDate;
    private Date maximumDate;
    private List<Train> trainList;
    private List<GapekaStationDto> gapekaStationDtoList;
    private final Map<Integer, Integer> stationMap = new HashMap<>();
    private List<GapekaTrainDto> gapekaTrainDtoList;
    private String lineName;
    private Optional<GapekaDto> optionalGapekaDto = Optional.empty();

    public void setTypeMasterPlanId(int typeMasterPlanId) {
        this.typeMasterPlanId = typeMasterPlanId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    @Override
    public void generateGapekaDto() throws Exception {
        try {
            fetchData();
            createGapekaStationDto();
            createGapekaTrainDto();
            createGapekaDto();
        } catch(Exception e) {
            throw new Exception("Gagal generate gapeka line", e);
        }
    }

    private void fetchData() throws Exception {
        try {
            fetchListLine();
            fetchTodayByListLine();
            fetchMinimumDate();
            fetchMaximumDate();
            fetchTrains();
        } catch(Exception e) {
            throw new Exception("Gagal mengambil data", e);
        }
    }

    private void fetchListLine() throws Exception {
        Optional<List<ListLine>> optionalListLines = loadListLinePort.loadListLineByLineId(lineId);
        listLines = optionalListLines.orElseThrow(DataNotFoundException::new);
    }

    private void fetchTodayByListLine() throws Exception {
        todayListByListLine = new ArrayList<>();
        Optional<List<TodayRunningSchedule>> optionalTodayRunningSchedules = loadTodayDetailPort.loadTodayRunningScheduleByTypeMasterPlanId(typeMasterPlanId);
        List<TodayRunningSchedule> todayRunningScheduleList = optionalTodayRunningSchedules.orElseThrow(DataNotFoundException::new);
        for(TodayRunningSchedule today : todayRunningScheduleList) {
            for(ListLine listLine : listLines) {
                if(today.getPeronFrom().getStation().getIdStation() == listLine.getStation().getIdStation()) {
                    todayListByListLine.add(today);
                }
            }
        }
    }

    private void fetchMinimumDate() throws Exception {
        minimumDate = new Date();
        for(TodayRunningSchedule today : todayListByListLine) {
            if(today.getArrival().getTime() < minimumDate.getTime()) {
                minimumDate = today.getArrival();
            }
        }
    }

    private void fetchTrains() throws Exception {
        trainList = new ArrayList<>();
        for(TodayRunningSchedule today : todayListByListLine) {
            if(!trainList.contains(today.getTrain())) {
                trainList.add(today.getTrain());
            }
        }
    }

    private void fetchMaximumDate() throws Exception {
        maximumDate = new GregorianCalendar(1960, Calendar.JANUARY, 1).getTime();
        for(TodayRunningSchedule today : todayListByListLine) {
            if(today.getDepart().getTime() > maximumDate.getTime()) {
                maximumDate = today.getArrival();
            }
        }
    }

    private void createGapekaStationDto() throws Exception {
        gapekaStationDtoList = new ArrayList<>();
        for(ListLine listLine : listLines) {
            GapekaStationDto gapekaStationDto = new GapekaStationDto();
            gapekaStationDto.setStationId(listLine.getStation().getIdStation());
            gapekaStationDto.setStationName(listLine.getStation().getNameStation());
            gapekaStationDto.setStationPoint(listLine.getListLineNumber());
            gapekaStationDtoList.add(gapekaStationDto);
            stationMap.put(listLine.getStation().getIdStation(), listLine.getListLineNumber());
        }
    }

    private void createGapekaTrainDto() throws Exception {
        gapekaTrainDtoList = new ArrayList<>();
        for(Train train : trainList) {
            GapekaTrainDto gapekaTrainDto = new GapekaTrainDto();
            gapekaTrainDto.setTrainName(train.getNoka());
            List<GapekaDataDto> gapekaDataDtoList = new ArrayList<>();
            gapekaTrainDto.setGapekaDataDtos(gapekaDataDtoList);
            for (TodayRunningSchedule today : todayListByListLine) {
                if(train.getIdTrain() == today.getTrain().getIdTrain()) {
                    GapekaDataDto gapekaDataDtoArrival = new GapekaDataDto();
                    gapekaDataDtoArrival.setStationPoint(stationMap.get(today.getPeronFrom().getStation().getIdStation()));
                    gapekaDataDtoArrival.setDatePoint(today.getArrival().getTime());
                    gapekaDataDtoList.add(gapekaDataDtoArrival);

                    GapekaDataDto gapekaDataDtoDepart = new GapekaDataDto();
                    gapekaDataDtoDepart.setStationPoint(stationMap.get(today.getPeronFrom().getStation().getIdStation()));
                    gapekaDataDtoDepart.setDatePoint(today.getDepart().getTime());
                    gapekaDataDtoList.add(gapekaDataDtoDepart);
                }
            }
            gapekaTrainDtoList.add(gapekaTrainDto);
        }
    }

    private void createGapekaDto() throws Exception {
        if(!listLines.isEmpty()) {
            lineName = listLines.get(0).getLine().getNameLine();
        }
        GapekaDto gapekaDto = GapekaDto.builder()
                .gapekaStationDtos(gapekaStationDtoList)
                .gapekaTrainDtos(gapekaTrainDtoList)
                .maxDate(maximumDate.getTime())
                .minDate(minimumDate.getTime())
                .title("TODAY - LINE - " + lineName)
                .build();
        optionalGapekaDto = Optional.of(gapekaDto);
    }

    @Override
    public GapekaDto getGapekaDto() throws Exception {
        return optionalGapekaDto.orElseThrow(DataNotFoundException::new);
    }
}
