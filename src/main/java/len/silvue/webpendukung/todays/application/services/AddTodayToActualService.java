package len.silvue.webpendukung.todays.application.services;


import len.silvue.webpendukung.actual.adapter.out.web.ActualRunningScheduleDto;
import len.silvue.webpendukung.actual.adapter.out.web.mapper.ActualRunningScheduleMapper;
import len.silvue.webpendukung.actual.application.port.out.SaveActualSchedulePort;
import len.silvue.webpendukung.domains.ActualPlan;
import len.silvue.webpendukung.actual.application.port.out.FindActualRunningSchedulePort;
import len.silvue.webpendukung.todays.application.port.in.AddTodayToActualUseCase;
import len.silvue.webpendukung.todays.application.port.in.FindTodayRunningSchedulePort;
import len.silvue.webpendukung.todays.application.port.out.LoadTodayDetailPort;
import len.silvue.webpendukung.domains.TodayRunningSchedule;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j

public class AddTodayToActualService implements AddTodayToActualUseCase {

    //private final LoadTypeMasterPlanPort loadTypeMasterPlanPort;
    private final SaveActualSchedulePort saveActualPlanPort;
    private final ActualRunningScheduleMapper actualPlanMapper;
    private final FindActualRunningSchedulePort loadActualPlanPort;
    private final LoadTodayDetailPort loadTodayDetailPort;
    private final FindTodayRunningSchedulePort loadTodayPort;


    @Override
    public List<ActualRunningScheduleDto> setActualPlanFromToday() throws Exception {
        try {
          // String[] arrayStringTodayDetailId;
          //  log.info("Id today"+arrayStringTodayDetailId);
          //  List <TodayRunningSchedule> arrayToday = new ArrayList<>();
           /* List<ActualPlan> actualList = new ArrayList<>();

            for (strTypeMasterPlanId : arrayStringTodayDetailId) {*/
            List<ActualPlan> actualList = new ArrayList<>();
                Optional<List<TodayRunningSchedule>> optionalTodaysDetail = loadTodayDetailPort.loadAllTodayRunningSchedule();
                List<TodayRunningSchedule> todayDetailList = optionalTodaysDetail.orElseThrow(DataNotFoundException::new);

            Optional<Integer> optionalIndex = loadActualPlanPort.getmaxindeactual();
            Integer index = optionalIndex.orElse(0);
            todayDetailList.forEach(todayDetail -> {
                try {
                    Date arriveDate = getDayBeginTime(todayDetail.getArrival());
                    Date departDate = getDayBeginTime(todayDetail.getDepart());
                    ActualPlan actualRunningSchedule = ActualPlan.builder()
                            .trainActualPlan(String.valueOf(todayDetail.getTrain().getNoka()))
                            .ruteRoleActualPlan(String.valueOf(todayDetail.getRuteRole().getNameRoute()))
                            .typePlan(String.valueOf(todayDetail.getTypeMasterPlan().getNameTypeMasterPlan()))
                            .statiunActualPlan(String.valueOf(todayDetail.getPeronFrom().getStation().getNameStation()))
                            .arriveSchedule(arriveDate)
                            .departSchedule(departDate)
                            .actualCode(new Date())
                            .indexActual(index+1)
                            .timeData(new Date())
                            .build();
                    actualList.add(actualRunningSchedule);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            if(!actualList.isEmpty()) {
                saveActualPlanPort.storeActualScheduleList(actualList);;
            }

            return actualPlanMapper.toActualRunningScheduleDtoList(actualList);
        } catch (Exception e) {
            throw new Exception("Gagal insert data Today", e);
        }
    }

    @Override
    public List<ActualRunningScheduleDto> setActualPlanFromTodayWithConflict() throws Exception {
        try {
            List<ActualPlan> actualList = new ArrayList<>();
            Optional<List<TodayRunningSchedule>> optionalTodaysDetail = loadTodayDetailPort.loadAllTodayRunningSchedule();
            List<TodayRunningSchedule> todayDetailList = optionalTodaysDetail.orElseThrow(DataNotFoundException::new);

            Optional<Integer> optionalIndex = loadActualPlanPort.getmaxindeactual();
            Integer index = optionalIndex.orElse(0);

            todayDetailList.forEach(todayDetail -> {
                try {
                    Date arriveDate = getDayBeginTime(todayDetail.getArrival());
                    Date departDate = getDayBeginTime(todayDetail.getDepart());
                    ActualPlan actualRunningSchedule = ActualPlan.builder()
                            .trainActualPlan(String.valueOf(todayDetail.getTrain().getNoka()))
                            .ruteRoleActualPlan(String.valueOf(todayDetail.getRuteRole().getNameRoute()))
                            .typePlan(String.valueOf(todayDetail.getTypeMasterPlan().getNameTypeMasterPlan()))
                            .statiunActualPlan(String.valueOf(todayDetail.getPeronFrom().getStation().getNameStation()))
                            .arriveSchedule(arriveDate)
                            .departSchedule(departDate)
                            .arriveActualPlan(arriveDate)
                            .departActualPlan(departDate)
                            .delayActualPlan(String.valueOf(todayDetail.getDwellingTime()))
                            .actualCode(new Date())
                            .indexActual(index+1)
                            .build();
                    actualList.add(actualRunningSchedule);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

            if(!actualList.isEmpty()) {
                saveActualPlanPort.storeActualScheduleList(actualList);;
            }

            return actualPlanMapper.toActualRunningScheduleDtoList(actualList);
        } catch (Exception e) {
            throw new Exception("Gagal insert data Today", e);
        }
    }


    private Date getDayBeginTime(Date date) throws Exception {
        if(date == null)
            return null;
        String[] timeStrSplit = date.toString().split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeStrSplit[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeStrSplit[1]));
        calendar.set(Calendar.SECOND, Integer.parseInt(timeStrSplit[2]));

        return calendar.getTime();
    }

    private Date getDayTime(Date date) {

        // Get today's date and time.
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date());

        // Get the required time of day, copy year, month, day.
        Calendar c2 = Calendar.getInstance();
        c2.setTime(java.sql.Time.valueOf(String.valueOf(LocalDateTime.now())));
        c2.set(Calendar.YEAR, c1.get(Calendar.YEAR));
        c2.set(Calendar.MONTH, c1.get(Calendar.MONTH));
        c2.set(Calendar.DAY_OF_MONTH, c1.get(Calendar.DAY_OF_MONTH));

        // Construct required java.sql.Timestamp object.
        Timestamp time = new Timestamp(c2.getTimeInMillis());


        return date;
    }

}
