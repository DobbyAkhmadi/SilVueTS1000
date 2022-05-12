package len.silvue.webpendukung.gapeka.application.port.out;

import len.silvue.webpendukung.domains.ListLineDetail;

import java.util.List;

public interface SaveListLineDetailPort {
    ListLineDetail saveListLine(ListLineDetail listLineDetail) throws Exception;
    ListLineDetail storeListLine(ListLineDetail listLineDetail) throws Exception;
    List<ListLineDetail> storeAllListLine(List<ListLineDetail> listLineDetailList) throws Exception;
}
