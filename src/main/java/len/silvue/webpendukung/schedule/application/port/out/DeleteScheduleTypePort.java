package len.silvue.webpendukung.schedule.application.port.out;

public interface DeleteScheduleTypePort {
    void eraseScheduleType(int scheduleId) throws Exception;
    void eraseAllScheduleType() throws Exception;
}
