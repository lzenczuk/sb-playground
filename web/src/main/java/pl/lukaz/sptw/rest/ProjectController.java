package pl.lukaz.sptw.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lukaz.sptw.rest.model.RestResult;
import pl.lukaz.sptw.rest.model.project.RestProject;
import pl.lukaz.sptw.service.project.ProjectService;
import pl.lukaz.sptw.service.project.model.ProjectDTO;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author lzenczuk 31/07/2015
 */

@RestController
@RequestMapping("/project")
public class ProjectController {

    private static Function<ProjectDTO, RestProject> projectDTOToRestProject = p -> new RestProject(p.getId(), p.getTitle());

    @Autowired
    private ProjectService projectService;

    @RequestMapping(method = RequestMethod.GET)
    public RestResult<List<RestProject>> getAllUsersProjects() {
        List<RestProject> restProjects = projectService.getAllProjects("1")
                .stream()
                .map(projectDTOToRestProject)
                .collect(Collectors.toList());

        return RestResult.success(restProjects);
    }

    @RequestMapping(value = "/id/{projectId}", method = RequestMethod.GET)
    RestResult<RestProject> getProjectById(@PathVariable String projectId) {
        return projectService.getProjectById("1", projectId)
                .map(projectDTOToRestProject)
                .map(RestResult::success).orElse(RestResult.fail());
    }

}
