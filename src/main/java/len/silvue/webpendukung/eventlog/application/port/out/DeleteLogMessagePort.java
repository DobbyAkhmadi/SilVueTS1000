package len.silvue.webpendukung.eventlog.application.port.out;



public interface DeleteLogMessagePort {
    void eraseEventtById(int idDepartement) throws Exception;
    void eraseAllEvent() throws Exception;

}
