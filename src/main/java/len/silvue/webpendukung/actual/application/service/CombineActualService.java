package len.silvue.webpendukung.actual.application.service;


import len.silvue.webpendukung.actual.adapter.in.CombineActualPlanCommand;
import len.silvue.webpendukung.actual.adapter.out.web.ActualRunningScheduleDto;
import len.silvue.webpendukung.actual.application.port.in.CombineActualScheduleUseCase;
import len.silvue.webpendukung.actual.application.port.out.DeleteActualPort;
import len.silvue.webpendukung.actual.application.port.out.FindActualRunningSchedulePort;
import len.silvue.webpendukung.actual.application.port.out.SaveActualSchedulePort;
import len.silvue.webpendukung.domains.ActualPlan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CombineActualService implements CombineActualScheduleUseCase {
    private final SaveActualSchedulePort saveActualSchedulePort;
    private final FindActualRunningSchedulePort loadActualPort;
    private final DeleteActualPort deleteActualPort;

    private List<ActualPlan> actualPlansToRemove;
    private List<ActualPlan> actualPlans;
    private List<ActualPlan> actualPlansTemp;

    @Override
    public List<ActualRunningScheduleDto> combineActualByIndex(CombineActualPlanCommand combineActualPlanCommand) throws Exception {
        try {
            fetchAllActualPlans();
            combineActualPlanBasedIndex();
            saveActualPlans();
            return null;
        } catch (Exception e) {
            throw new Exception("Gagal mengambil combine actual", e);
        }
    }

    private void fetchAllActualPlans() throws Exception {
        Optional<List<ActualPlan>> optionalActualPlans = loadActualPort.loadAllActualRunningSchedule();
        actualPlans = optionalActualPlans.orElse(new ArrayList<>());

        Optional<List<ActualPlan>> optionalActualPlansToAdd = loadActualPort.loadAllActualRunningSchedule();
        actualPlansTemp = optionalActualPlansToAdd.orElse(new ArrayList<>());
    }

    private void combineActualPlanBasedIndex() throws Exception {
        actualPlansToRemove = new ArrayList<>();

        actualPlans.forEach(actualPlan -> {
            actualPlansTemp.forEach(actualPlanTemp -> {
                if(actualPlan.getIdActualPlan() != actualPlanTemp.getIdActualPlan()
                        && actualPlan.getTrainActualPlan().compareToIgnoreCase(actualPlanTemp.getTrainActualPlan()) == 0
                        && actualPlan.getStatiunActualPlan().compareToIgnoreCase(actualPlanTemp.getStatiunActualPlan()) == 0
                        && actualPlan.getTypePlan().compareToIgnoreCase(actualPlanTemp.getTypePlan()) == 0
                        && actualPlan.getRuteRoleActualPlan().compareToIgnoreCase(actualPlanTemp.getRuteRoleActualPlan()) == 0) {
                    boolean arriveActualChange = false;
                    boolean arriveScheduleChange = false;
                    if((actualPlan.getArriveActualPlan() == null && actualPlanTemp.getArriveActualPlan() != null)
                        || (actualPlan.getArriveActualPlan() != null && actualPlanTemp.getArriveActualPlan() != null
                            && (actualPlan.getArriveActualPlan().compareTo(actualPlanTemp.getArriveActualPlan()) < 0
                                || actualPlan.getArriveActualPlan().compareTo(actualPlanTemp.getArriveActualPlan()) == 0) )) {
                        actualPlan.setArriveActualPlan(actualPlanTemp.getArriveActualPlan());
                        arriveActualChange = true;
                    }

                    if((actualPlan.getArriveSchedule() == null && actualPlanTemp.getArriveSchedule() != null)
                            || (actualPlan.getArriveActualPlan() != null && actualPlanTemp.getArriveActualPlan() != null
                            && (actualPlan.getArriveSchedule().compareTo(actualPlanTemp.getArriveSchedule()) < 0
                                || actualPlan.getArriveSchedule().compareTo(actualPlanTemp.getArriveSchedule()) == 0))) {
                        actualPlan.setArriveSchedule(actualPlanTemp.getArriveSchedule());
                        arriveScheduleChange = true;
                    }

                    if(arriveActualChange || arriveScheduleChange) {
                        actualPlansToRemove.add(actualPlanTemp);
                    }
                }
            });
        });
    }

    private void saveActualPlans() throws Exception {
        setIndexToZero();
        deleteAllActualPlan();
        saveActualPlansFromCombine();
    }

    private void setIndexToZero() throws Exception {
        for (ActualPlan actualPlanToAdd : actualPlans) {
            actualPlanToAdd.setIndexActual(0);
        }
    }

    private void deleteAllActualPlan() throws Exception {
        if (!actualPlans.isEmpty()) {
            deleteActualPort.eraseAllActualSchedule();
        }
    }

    private void saveActualPlansFromCombine() throws Exception {
        if (!actualPlans.isEmpty()) {
            List<ActualPlan> actualPlansToAdd = new ArrayList<>();
            actualPlans.forEach(actualPlan -> {
                boolean addActualPlan = true;
                for(ActualPlan actualPlanRemove : actualPlansToRemove) {
                    if(actualPlan.getIdActualPlan() == actualPlanRemove.getIdActualPlan()) {
                        addActualPlan = false;
                        break;
                    }
                }
                if(addActualPlan) {
                    actualPlansToAdd.add(actualPlan);
                }
            });
            saveActualSchedulePort.storeActualScheduleList(actualPlansToAdd);
        }
    }
}
