package len.silvue.webpendukung.gapeka.application.port.out;

import len.silvue.webpendukung.domains.ListRuteDetail;

import java.util.List;

public interface SaveListRuteDetailPort {
    ListRuteDetail saveListRoute(ListRuteDetail listRuteDetail) throws Exception;
    ListRuteDetail storeListRoute(ListRuteDetail listRuteDetail) throws Exception;
    List<ListRuteDetail> storeAllListRoute(List<ListRuteDetail> listRuteDetailList) throws Exception;
}
