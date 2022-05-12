package len.silvue.webpendukung.gapeka.application.port.in;

import len.silvue.webpendukung.gapeka.adapter.out.web.ListRuteDetailDto;
import len.silvue.webpendukung.schedule.adapter.out.web.RuteRoleDto;

import java.util.List;

public interface FindListRuteDetailUseCase {
    List<ListRuteDetailDto> getAllListRuteDetail() throws Exception;
    List<ListRuteDetailDto> getAllListRuteDetailByRuteRoleId(int ruteRoleId) throws Exception;
    ListRuteDetailDto getListRuteDetailById(int idDetail) throws Exception;
    List<RuteRoleDto> getAllListRuteDetailByDistinctRuteRole() throws Exception;
}
