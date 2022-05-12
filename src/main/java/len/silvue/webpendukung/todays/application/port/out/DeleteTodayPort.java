package len.silvue.webpendukung.todays.application.port.out;



public interface DeleteTodayPort {
    void eraseTodayDetailByTodayRunningSchedule(int idTodayRunningSchedule) throws Exception;
    void eraseAllTodayDetail() throws Exception;
    void eraseAllTodayDetailByTrainId(int trainId) throws Exception;
    void eraseTodayDetailByTypeMasterPlan(int idTypeMasterPlan) throws Exception;
}
