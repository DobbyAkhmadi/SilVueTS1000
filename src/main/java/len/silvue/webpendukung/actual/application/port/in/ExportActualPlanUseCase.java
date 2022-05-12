package len.silvue.webpendukung.actual.application.port.in;

import java.io.Writer;

public interface ExportActualPlanUseCase {
    void generateActualPlan(String actualCode) throws Exception;
    void writeCsv(Writer writer) throws Exception;
}
