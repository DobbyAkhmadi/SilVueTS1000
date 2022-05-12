package len.silvue.webpendukung.gapeka.application.port.out;

public interface DeleteListLineDetailPort {
    void eraseAllListLineDetail() throws Exception;
    void eraseListLine(int idLineList) throws Exception;
    void eraseAllListLine(int idLineList) throws Exception;
}
