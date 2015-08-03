package pl.lukaz.sptw.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.lukaz.sptw.security.CurrentUser;
import pl.lukaz.sptw.security.WebUser;

/**
 * @author lzenczuk 03/08/2015
 */
@Controller
public class HelloController {

    @RequestMapping("/")
    public String helloPage(Model model, @CurrentUser WebUser user){
        model.addAttribute("userName", user.getName());
        return "hello";
    }
}
