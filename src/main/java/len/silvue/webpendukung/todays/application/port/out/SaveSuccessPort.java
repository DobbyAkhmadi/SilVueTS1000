package len.silvue.webpendukung.todays.application.port.out;

import len.silvue.webpendukung.domains.SuccessBrowseConflict;

public interface SaveSuccessPort {
    SuccessBrowseConflict saveBrowse(SuccessBrowseConflict successBrowse) throws Exception;
}
