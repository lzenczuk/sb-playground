package pl.lukaz.sptw.service.project;

import pl.lukaz.sptw.service.project.model.ProjectDTO;

import java.util.List;
import java.util.Optional;

/**
 * @author lzenczuk 31/07/2015
 */
public interface ProjectService {

    List<ProjectDTO> getAllProjects(String userId);
    Optional<ProjectDTO> getProjectById(String userId, String projectId);
}
