package len.silvue.webpendukung.tmconfig.application.port.in;

import len.silvue.webpendukung.tmconfig.adapter.in.web.GapekaConfigCommand;
import len.silvue.webpendukung.utility.SaveGapekaConfigFailedException;

public interface SaveGapekaConfigUseCase {
    void store(GapekaConfigCommand gapekaConfigCommand) throws SaveGapekaConfigFailedException;
}
