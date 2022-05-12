package len.silvue.webpendukung.web.controllers;

import len.silvue.webpendukung.tmconfig.adapter.out.web.ConfigurationDto;
import len.silvue.webpendukung.tmconfig.adapter.out.web.SystemStatusDto;
import len.silvue.webpendukung.tmconfig.application.port.in.FindConfigurationUseCase;
import len.silvue.webpendukung.tmconfig.application.port.in.FindSystemStatusUseCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping("/")
public class MainPageController {
    private final FindConfigurationUseCase findConfiguration;
    private final FindSystemStatusUseCase findSystemStatus;

    @GetMapping
    public ModelAndView viewMainPage() {
        try {
            ModelAndView mav = new ModelAndView("index");
            mav.addObject("redundancy", getRedundancyHtmlData());
            mav.addObject("driver", getDriverHtmlData());
            mav.addObject("tds", getTdsHtmlData());
            return mav;
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal mengambil konfigurasi");
        }
    }

    private HtmlData getRedundancyHtmlData() throws Exception {
        Optional<ConfigurationDto> optionalconfigDto = findConfiguration.getConfiguration();
        ConfigurationDto configDto = optionalconfigDto.orElse(new ConfigurationDto());
        SystemStatusDto systemStatusDto = findSystemStatus.getSystemStatus();
        HtmlData htmlData = new HtmlData();
        if(systemStatusDto.getCtcStatus() == 1 && configDto.getRedudancy() == "Dominan" || configDto.getRedudancy() == "NonDominan") {
            htmlData.setCss("bg-info");
            htmlData.setTxt("MAIN");
        } else if(systemStatusDto.getCtcStatus() == 2 && configDto.getRedudancy() == "Dominan" || configDto.getRedudancy() == "NonDominan") {
            htmlData.setCss("bg-warning");
            htmlData.setTxt("BACKUP");
        } else {
            htmlData.setCss("bg-danger");
            htmlData.setTxt("DISABLE");
        }
        return htmlData;
    }

    private HtmlData getDriverHtmlData() throws Exception {
        SystemStatusDto systemStatusDto = findSystemStatus.getSystemStatus();
        HtmlData htmlData = new HtmlData();
        if(systemStatusDto.getClientStatus() == 1) {
            htmlData.setCss("bg-info");
            htmlData.setTxt("CONNECTED");
        } else {
            htmlData.setCss("bg-danger");
            htmlData.setTxt("DISCONNECTED");
        }
        return htmlData;
    }

    private HtmlData getTdsHtmlData() throws Exception {
        SystemStatusDto systemStatusDto = findSystemStatus.getSystemStatus();
        HtmlData htmlData = new HtmlData();
        if(systemStatusDto.getTdsStatus() == 1) {
            htmlData.setCss("bg-info");
            htmlData.setTxt("ENABLE");
        } else {
            htmlData.setCss("bg-danger");
            htmlData.setTxt("DISABLE");
        }
        return htmlData;
    }

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @Data
    private class HtmlData {
        private String css;
        private String txt;
    }
}
