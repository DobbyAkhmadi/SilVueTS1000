package len.silvue.webpendukung.web.controllers;

import len.silvue.webpendukung.actual.application.port.in.ActualRunningScheduleUseCase;
import len.silvue.webpendukung.gapeka.adapter.out.web.LineDto;
import len.silvue.webpendukung.gapeka.application.port.in.FindLineUseCase;
import len.silvue.webpendukung.schedule.adapter.out.web.RuteRoleDto;
import len.silvue.webpendukung.schedule.adapter.out.web.TypeMasterPlanDto;
import len.silvue.webpendukung.schedule.application.port.in.FindRuteRoleUseCase;
import len.silvue.webpendukung.schedule.application.port.in.FindTypeMasterPlanUseCase;
import len.silvue.webpendukung.tmconfig.adapter.out.web.ConfigurationDto;
import len.silvue.webpendukung.tmconfig.application.port.in.FindConfigurationUseCase;
import len.silvue.webpendukung.utility.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/gapeka")
@RequiredArgsConstructor
@Slf4j
public class GapekaController {
    private final FindRuteRoleUseCase findRuteRoleUseCase;
    private final FindLineUseCase findLineUseCase;
    private final FindTypeMasterPlanUseCase findTypeMasterPlanUseCase;
    private final ActualRunningScheduleUseCase actualRunningScheduleUseCase;
    private final FindConfigurationUseCase findConfigurationUseCase;

    @GetMapping
    public ModelAndView viewGapekaMainPage() {
        ModelAndView mav = new ModelAndView("gapeka");
        try {
            List<RuteRoleDto> ruteRoleDtoList = findRuteRoleUseCase.getAllRuteRole();
            mav.addObject("ruteRoles", ruteRoleDtoList);

            List<LineDto> lineDtoList = findLineUseCase.getAllLine();
            mav.addObject("lines", lineDtoList);

            List<TypeMasterPlanDto> typeMasterPlanDtoList = findTypeMasterPlanUseCase.getAllTypeMasterPlan();
            mav.addObject("typeMasterPlans", typeMasterPlanDtoList);

            Date maximumdate = actualRunningScheduleUseCase.getMaximumTimeData();
            mav.addObject("actualDate", maximumdate);

            Optional<ConfigurationDto> optionalConfiguration = findConfigurationUseCase.getConfiguration();
            if(optionalConfiguration.isPresent()) {
                ConfigurationDto configuration = optionalConfiguration.get();
                String tdgPlan = configuration.getTdgPlan();
                String tdgSch = configuration.getTdgSch();
                String tdgLine = configuration.getTdgLine();
                Date tdgDate = configuration.getTdgDate();
                String tdgRute = configuration.getTdgRute();
            }



        } catch(DataNotFoundException de) {
            log.info("Data kosong " + de.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal menampilkan halaman utama gapeka");
        }
        return mav;
    }
}
