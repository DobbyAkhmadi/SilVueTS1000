package len.silvue.webpendukung.setting.application.port.in;

import len.silvue.webpendukung.gapeka.adapter.out.web.ListLineDetailDto;
import len.silvue.webpendukung.gapeka.adapter.out.web.ListRuteDetailDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImportSettingUseCase {
    void setMultipartFile(MultipartFile file) throws Exception;
    void importData() throws Exception;
}
