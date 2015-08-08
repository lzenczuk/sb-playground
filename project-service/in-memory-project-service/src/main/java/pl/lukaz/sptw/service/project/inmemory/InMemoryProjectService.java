package pl.lukaz.sptw.service.project.inmemory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import pl.lukaz.sptw.service.project.ProjectService;
import pl.lukaz.sptw.service.project.inmemory.model.Project;
import pl.lukaz.sptw.service.project.model.ProjectDTO;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author lzenczuk 31/07/2015
 */
@Service
public class InMemoryProjectService implements ProjectService {

    private final Log log = LogFactory.getLog(InMemoryProjectService.class);

    private AtomicInteger keyGenerator = new AtomicInteger();
    private Map<String, Project> storage = new HashMap<>();

    private static Function<Project, ProjectDTO> projectToProjectDTO = p -> new ProjectDTO(p.getId(), p.getTitle());

    @Override
    @PreAuthorize("hasRole('USER')")
    public List<ProjectDTO> getAllProjects() {

        log.debug("Get all projects");

        return storage.values().stream()
                .map(projectToProjectDTO)
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public Optional<ProjectDTO> getProjectById(String projectId) {

        log.debug("Get project by id: "+projectId);

        return Optional.ofNullable(storage.get(projectId)).map(projectToProjectDTO);
    }

    @Override
    @PreAuthorize("hasRole('PROJECTS_MANAGER')")
    public Optional<ProjectDTO> createProject(String title) {

        log.debug("Create project: "+title);

        String key = Integer.toString(keyGenerator.getAndIncrement());
        Project project = new Project(key, title);
        storage.put(key, project);
        return Optional.of(projectToProjectDTO.apply(project));
    }

    @Override
    @PreAuthorize("hasRole('PROJECTS_MANAGER')")
    public boolean deleteProject(String projectId) {

        log.debug("Delete project: " + projectId);

        if (storage.containsKey(projectId)) {
            storage.remove(projectId);
            return true;
        }
        return false;
    }

    @Override
    @PreAuthorize("hasRole('PROJECTS_MANAGER')")
    public Optional<ProjectDTO> updateProject(String projectId, String title) {

        log.debug("Update project: "+projectId+", "+title);

        return Optional.ofNullable(storage.get(projectId))
                .map((p) -> {
                    p.setTitle(title);
                    return p;
                })
                .map(projectToProjectDTO);
    }

    // TODO - remove this, it is only to provide some initial data for web application
    @PostConstruct
    private void initForWebApplication(){
        createProject("Project 1");
        createProject("Project 2");
        createProject("Project 3");
        createProject("Project 4");
    }
}
