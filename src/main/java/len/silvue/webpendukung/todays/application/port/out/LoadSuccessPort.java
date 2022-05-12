package len.silvue.webpendukung.todays.application.port.out;

import len.silvue.webpendukung.domains.SuccessBrowseConflict;

import java.util.Optional;

public interface LoadSuccessPort {
    Optional<SuccessBrowseConflict> loadSuccessBrowse(int idStatus) throws Exception;
}
