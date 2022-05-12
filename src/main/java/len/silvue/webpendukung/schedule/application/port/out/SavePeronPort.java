package len.silvue.webpendukung.schedule.application.port.out;

import len.silvue.webpendukung.domains.Peron;

public interface SavePeronPort {
    Peron savePeron(Peron peron) throws Exception;
}
