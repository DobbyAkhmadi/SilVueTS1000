package len.silvue.webpendukung.gapeka.application.port.in;

import len.silvue.webpendukung.gapeka.adapter.out.web.GapekaDto;

public interface GapekaUseCase {
    void generateGapekaDto() throws Exception;
    GapekaDto getGapekaDto() throws Exception;
}
