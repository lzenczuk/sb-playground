package pl.lukaz.sptw.service.project.inmemory;

import org.springframework.stereotype.Component;
import pl.lukaz.sptw.service.project.ProjectService;
import pl.lukaz.sptw.service.project.inmemory.model.Project;
import pl.lukaz.sptw.service.project.model.ProjectDTO;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author lzenczuk 31/07/2015
 */
@Component
public class InMemoryProjectService implements ProjectService {

    private Map<String, List<Project>> storage = new HashMap<>();

    private static Function<Project, ProjectDTO> projectToProjectDTO = p -> new ProjectDTO(p.getId(), p.getTitle());

    public InMemoryProjectService() {
        storage.put("1", Arrays.asList(new Project("1", "Project 1 of U1"), new Project("2", "Project 2 of U1")));
        storage.put("2", Arrays.asList(new Project("3", "Project 1 of U2")));
        storage.put("3", Arrays.asList(new Project("4", "Project 1 of U3"), new Project("5", "Project 2 of U3")));
    }

    @Override
    public List<ProjectDTO> getAllProjects(String userId) {
        return Optional.ofNullable(storage.get(userId))
                .map(l -> l.stream()
                        .map(projectToProjectDTO)
                        .collect(Collectors.toList()))
                .orElse(Collections.<ProjectDTO>emptyList());
    }

    @Override
    public Optional<ProjectDTO> getProjectById(String userId, final String projectId) {
        return Optional.ofNullable(storage.get(userId))
                .flatMap(
                        l -> l.stream()
                                .filter(p -> p.getId().equals(projectId))
                                .findFirst()
                                .map(projectToProjectDTO));
    }
}
