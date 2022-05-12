package len.silvue.webpendukung.gapeka.application.port.out;

import len.silvue.webpendukung.domains.ListRuteDetail;
import len.silvue.webpendukung.domains.RuteRole;

import java.util.List;
import java.util.Optional;

public interface LoadListRuteDetailPort {
    Optional<List<ListRuteDetail>> loadAllListRuteDetail() throws Exception;
    Optional<List<ListRuteDetail>> loadListRuteDetailByRuteRoleId(int ruteRoleId) throws Exception;
    Optional<ListRuteDetail> loadListRuteDetailById (int idDetail) throws Exception;
    Optional<List<RuteRole>> loadAllListRuteDetailByDistinctRuteRole() throws Exception;
    Optional<List<ListRuteDetail>> loadListRuteDetailByRuteRoleName(String ruteRoleName) throws Exception;
}
