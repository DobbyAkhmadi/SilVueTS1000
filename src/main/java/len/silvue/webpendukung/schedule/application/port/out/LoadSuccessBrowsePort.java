package len.silvue.webpendukung.schedule.application.port.out;

import len.silvue.webpendukung.domains.SuccessBrowse;

import java.util.Optional;

public interface LoadSuccessBrowsePort {
    Optional<SuccessBrowse> loadSuccessBrowse(int idStatus) throws Exception;
}
