package len.silvue.webpendukung.actual.application.port.out;


import len.silvue.webpendukung.domains.ActualPlan;

import java.util.Date;
import java.util.List;

public interface DeleteActualPort {
    void eraseActualScheduleByActualSchedule(int idActualPlan) throws Exception;
    void eraseAllActualSchedule() throws Exception;
    //void eraseAllActualScheduleByTrainId(int trainId) throws Exception;
    void eraseAllActualPlans(List<ActualPlan> actualPlans) throws Exception;
    void eraseActualScheduleByActualSpesificDate(Date actualCode) throws Exception;
}
