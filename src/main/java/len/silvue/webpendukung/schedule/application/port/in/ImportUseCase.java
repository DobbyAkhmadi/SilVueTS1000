package len.silvue.webpendukung.schedule.application.port.in;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface ImportUseCase {
    void setMultipartFile(MultipartFile file) throws Exception;
    void importDataPrep() throws Exception;
    void saveToDatabase() throws Exception;
}
