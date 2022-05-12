package len.silvue.webpendukung.actual.application.port.in;

import org.springframework.web.multipart.MultipartFile;

public interface ImportActualUseCase {
    void setMultipartFile(MultipartFile file) throws Exception;
    void importData() throws Exception;
    void saveToDatabase() throws Exception;
}
