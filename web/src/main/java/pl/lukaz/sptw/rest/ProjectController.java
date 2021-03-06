package pl.lukaz.sptw.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.lukaz.sptw.rest.model.RestResult;
import pl.lukaz.sptw.rest.model.project.RestProject;
import pl.lukaz.sptw.service.project.ProjectService;
import pl.lukaz.sptw.service.project.model.ProjectDTO;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author lzenczuk 31/07/2015
 */

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final Log log = LogFactory.getLog(ProjectController.class);

    private static Function<ProjectDTO, RestProject> projectDTOToRestProject = p -> new RestProject(p.getId(), p.getTitle());

    @Autowired
    private ProjectService projectService;

    @RequestMapping(method = RequestMethod.GET)
    public RestResult<List<RestProject>> getAllProjects() {
        List<RestProject> restProjects = projectService.getAllProjects()
                .stream()
                .map(projectDTOToRestProject)
                .peek(log::debug)
                .collect(Collectors.toList());

        return RestResult.success(restProjects);
    }

    @RequestMapping(value = "/id/{projectId}", method = RequestMethod.GET)
    RestResult<RestProject> getProjectById(@PathVariable String projectId) {
        return projectService.getProjectById(projectId)
                .map(projectDTOToRestProject)
                .map(RestResult::success).orElse(RestResult.fail());
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('PROJECTS_MANAGER')")
    RestResult<RestProject> createProject(String title) {
        return projectService.createProject(title)
                .map(projectDTOToRestProject)
                .map(RestResult::success).orElse(RestResult.fail());
    }

    @RequestMapping("/ex")
    RestResult<RestProject> throwException(String title) {
        throw new RuntimeException("Something goes wrong");
    }

}
