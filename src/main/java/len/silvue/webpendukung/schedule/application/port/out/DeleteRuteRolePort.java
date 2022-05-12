package len.silvue.webpendukung.schedule.application.port.out;

public interface DeleteRuteRolePort {
    void eraseRuteRoleById(int idRuteRole) throws Exception;
    void eraseAllRuteRole() throws Exception;
}
