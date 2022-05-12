package len.silvue.webpendukung.schedule.application.port.out;

import len.silvue.webpendukung.domains.SuccessBrowse;

public interface SaveSuccessBrowsePort {
    SuccessBrowse saveBrowse(SuccessBrowse successBrowse) throws Exception;
}
