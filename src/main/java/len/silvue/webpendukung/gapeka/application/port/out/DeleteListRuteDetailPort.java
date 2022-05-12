package len.silvue.webpendukung.gapeka.application.port.out;

public interface DeleteListRuteDetailPort {
    void eraseListRoute(int idRouteList) throws Exception;
    void eraseAllListRoute(int idRouteList) throws Exception;
    void eraseAllListRuteDetail() throws Exception;
}
