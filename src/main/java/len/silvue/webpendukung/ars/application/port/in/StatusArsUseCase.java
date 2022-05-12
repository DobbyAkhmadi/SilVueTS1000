package len.silvue.webpendukung.ars.application.port.in;

public interface StatusArsUseCase {
    boolean isArsEnable() throws Exception;
    void enableArs() throws Exception;
    void disableArs() throws Exception;
}
