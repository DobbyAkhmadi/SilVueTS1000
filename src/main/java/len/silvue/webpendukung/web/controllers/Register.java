package len.silvue.webpendukung.web.controllers;


import len.silvue.webpendukung.user.adapter.in.web.model.UserDto;
import len.silvue.webpendukung.user.application.in.UserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
@Slf4j
public class Register {

    private final UserUseCase userUseCase;

    @GetMapping
    public ModelAndView viewRegister() {
        try {
            ModelAndView mav = new ModelAndView("register/register");
            List<UserDto> userRunning = userUseCase.getAllUser();
            System.out.println(userRunning);
            mav.addObject("userRole", userRunning);
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("Gagal register");
        }
    }

    @GetMapping("/view-add-data-user")
    private ModelAndView viewAddDataUser() {
        try {
            ModelAndView mav = new ModelAndView("register/view-add-data-user");
            return mav;
        } catch(Exception e) {
            throw new RuntimeException("failed to get export actual",e);
        }
    }


}
