package len.silvue.webpendukung.tmconfig.application.services;

import len.silvue.webpendukung.tmconfig.adapter.out.web.SettingPrintDto;
import len.silvue.webpendukung.tmconfig.adapter.out.web.mapper.SettingPrintMapper;
import len.silvue.webpendukung.tmconfig.application.port.in.FindSettingPrintUseCase;
import len.silvue.webpendukung.tmconfig.application.port.out.LoadSettingPrintPort;
import len.silvue.webpendukung.domains.SettingPrint;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FindPrintSettingService implements FindSettingPrintUseCase {
    private final LoadSettingPrintPort loadSettingPrintPort;

    @Override
    public SettingPrintDto getSettingPrint() throws Exception {
        try {
            Optional<SettingPrint> optionalSettingPrint = loadSettingPrintPort.loadDataSettingPort();
            return SettingPrintMapper.MAPPER.toSettingPrintDto(optionalSettingPrint.orElse(new SettingPrint()));
        } catch(Exception e) {
            throw new Exception("Gagal mengambil system status", e);
        }
    }
}
