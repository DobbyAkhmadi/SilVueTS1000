package len.silvue.webpendukung.ars.application.services;

import len.silvue.webpendukung.ars.application.port.in.StatusArsUseCase;
import len.silvue.webpendukung.domains.Configuration;
import len.silvue.webpendukung.domains.SystemStatus;
import len.silvue.webpendukung.tmconfig.application.port.out.LoadConfigurationPort;
import len.silvue.webpendukung.tmconfig.application.port.out.LoadSystemStatusPort;
import len.silvue.webpendukung.tmconfig.application.port.out.SaveConfigurationPort;
import len.silvue.webpendukung.tmconfig.application.port.out.SaveSystemStatusPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatusArsService implements StatusArsUseCase {
    private final SaveSystemStatusPort saveSystemStatusPort;
    private final SaveConfigurationPort saveConfigurationPort;
    private final LoadConfigurationPort loadConfigurationPort;

    @Override
    public boolean isArsEnable() throws Exception{
        try {
            Optional<Configuration> optionalConfiguration = loadConfigurationPort.loadDataConfiguration();
            if(optionalConfiguration.isPresent()) {
                Configuration systemStatus = optionalConfiguration.get();
                return systemStatus.getArsStatusEnable().compareTo("1") == 0;
            } else {
                return false;
            }
        } catch(Exception e) {
            throw new Exception("Gagal cek status ARS", e);
        }
    }

    @Override
    public void enableArs() throws Exception {
        try(Socket clientSocket = new Socket("localhost", 12330)) {
            try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
                bw.write("Web~Enable%&");
                saveStatusArs(1);
            } catch(Exception e) {
                log.error("Status send", e);
            }
        }catch(Exception e){
            log.error("Status send", e);
        }
    }

    @Override
    public void disableArs() throws Exception {
        try(Socket clientSocket = new Socket("localhost", 12330)) {
            try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
                bw.write("Web~Disable%&");
                saveStatusArs(0);
            } catch(Exception e) {
                log.error("Status send", e);
            }
        }catch(Exception e){
            log.error("Status send", e);
        }
    }

    private void saveStatusArs(int idStatus) throws Exception {
        Optional<Configuration> optionalConfiguration = loadConfigurationPort.loadDataConfiguration();
        Configuration configuration = optionalConfiguration.orElseGet(Configuration::new);
        if(configuration.getTdgDate() == null) {
            configuration.setTdgDate(new Date());
        }
        if(configuration.getTdgDateLive() == null) {
            configuration.setTdgDateLive(new Date());
        }
        configuration.setArsStatusEnable(String.valueOf(idStatus));
        saveConfigurationPort.storeDataConfiguration(configuration);
    }
}
