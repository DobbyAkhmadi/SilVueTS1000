package len.silvue.webpendukung.web.controllers;


import len.silvue.webpendukung.trainlocation.adapter.out.web.RouteStickTrainDTO;
import len.silvue.webpendukung.trainlocation.application.port.in.TrainLocationRunningUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/trainlocation")
@RequiredArgsConstructor
@Slf4j
public class TrainLocationController {

    private final TrainLocationRunningUseCase trainLocationRunningUseCase;

    @GetMapping
    public ModelAndView viewTrainLocationSchedule() {
        ModelAndView mav = new ModelAndView("trainlocation");
        try {
            List<RouteStickTrainDTO> trainLocation= trainLocationRunningUseCase.getAllTrainLocationRunning();
            mav.addObject("trainLocation", trainLocation);
            return mav;
        } catch(Exception e) {
            log.error("Gagal view train location");
            return mav;
        }
    }


}
