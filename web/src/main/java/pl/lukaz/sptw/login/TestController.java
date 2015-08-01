package pl.lukaz.sptw.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lzenczuk 01/08/2015
 */
@Controller
public class TestController {

    @RequestMapping("/test")
    public String login(Model model){
        model.addAttribute("message", "Test hello");
        return "test";
    }
}
