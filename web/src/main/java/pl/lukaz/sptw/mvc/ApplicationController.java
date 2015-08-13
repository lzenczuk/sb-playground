package pl.lukaz.sptw.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.lukaz.sptw.security.CurrentUser;
import pl.lukaz.sptw.security.WebUser;
import pl.lukaz.sptw.service.project.ProjectService;
import pl.lukaz.sptw.service.project.model.ProjectDTO;

import java.util.List;

/**
 * @author lzenczuk 03/08/2015
 */
@Controller
public class ApplicationController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/")
    public String helloPage(Model model, @CurrentUser WebUser user){
        if(user!=null){
            model.addAttribute("loggedIn", true);
            model.addAttribute("userName", user.getName());
        }else{
            model.addAttribute("loggedIn", false);
            model.addAttribute("userName", "Guest");
        }
        return "application";
    }

    @RequestMapping("/projects")
    @PreAuthorize("hasRole('USER')")
    public String getAllProjects(Model model){
        List<ProjectDTO> allProjects = projectService.getAllProjects();
        model.addAttribute("projectsNum", allProjects.size());
        return "number-of-projects";
    }
}
