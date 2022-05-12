package len.silvue.webpendukung.schedule.application.port.in;

import len.silvue.webpendukung.schedule.adapter.out.web.ExportMasterPlanDto;
import len.silvue.webpendukung.schedule.adapter.out.web.MasterScheduleDto;

import java.io.Writer;
import java.util.List;

public interface ExportUseCase {
    void generateMasterSchedule(int idTypeMasterPlan) throws Exception;
    void writeCsv(Writer writer) throws Exception;
}
