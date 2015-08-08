package pl.lukaz.sptw.service.project.inmemory;

import org.junit.Test;
import pl.lukaz.sptw.service.project.model.ProjectDTO;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;



/**
 * @author lzenczuk 08/08/2015
 */
public class InMemoryProjectServiceTest {

    @Test
    public void shouldReturnOptionalProjectDTOAfterCreatingNewOne(){
        InMemoryProjectService service = new InMemoryProjectService();

        Optional<ProjectDTO> optional = service.createProject("Test 1");

        assertThat(optional, equalTo(Optional.of(new ProjectDTO("0", "Test 1"))));
    }

    @Test
    public void shouldContainProjectAfterCreatingNewOne(){
        InMemoryProjectService service = new InMemoryProjectService();

        service.createProject("Test 0");
        service.createProject("Test 1");
        service.createProject("Test 2");

        List<ProjectDTO> allProjects = service.getAllProjects();
        assertThat(
                allProjects,
                containsInAnyOrder(new ProjectDTO("0", "Test 0"), new ProjectDTO("1", "Test 1"), new ProjectDTO("2", "Test 2"))
        );
    }

    @Test
    public void shouldReturnOptionalProjectDTOGettingExistingProjectById(){
        InMemoryProjectService service = new InMemoryProjectService();

        service.createProject("Test 0");
        service.createProject("Test 1");
        service.createProject("Test 2");

        Optional<ProjectDTO> optional = service.getProjectById("1");

        assertThat(optional, equalTo(Optional.of(new ProjectDTO("1", "Test 1"))));
    }

    @Test
    public void shouldReturnEmptyOptionalGettingNonExistingProjectById(){
        InMemoryProjectService service = new InMemoryProjectService();

        service.createProject("Test 0");
        service.createProject("Test 1");
        service.createProject("Test 2");

        Optional<ProjectDTO> optional = service.getProjectById("3");

        assertThat(optional, equalTo(Optional.empty()));
    }

    @Test
    public void shouldReturnTrueWhenDeletingExistingProject(){
        InMemoryProjectService service = new InMemoryProjectService();

        service.createProject("Test 0");
        service.createProject("Test 1");
        service.createProject("Test 2");

        boolean result = service.deleteProject("1");

        assertThat(result, is(true));
    }

    @Test
    public void shouldReturnFalseWhenDeletingNonExistingProject(){
        InMemoryProjectService service = new InMemoryProjectService();

        service.createProject("Test 0");
        service.createProject("Test 1");
        service.createProject("Test 2");

        boolean result = service.deleteProject("3");

        assertThat(result, is(false));
    }

    @Test
    public void shouldReturnEmptyOptionWhenGettingDeletedProject(){
        InMemoryProjectService service = new InMemoryProjectService();

        service.createProject("Test 0");
        service.createProject("Test 1");
        service.createProject("Test 2");

        service.deleteProject("1");

        Optional<ProjectDTO> optional = service.getProjectById("1");

        assertThat(optional, equalTo(Optional.empty()));
    }

    @Test
    public void shouldNotContainDeletedProject(){
        InMemoryProjectService service = new InMemoryProjectService();

        service.createProject("Test 0");
        service.createProject("Test 1");
        service.createProject("Test 2");

        service.deleteProject("1");

        List<ProjectDTO> allProjects = service.getAllProjects();

        assertThat(
                allProjects,
                containsInAnyOrder(new ProjectDTO("0", "Test 0"), new ProjectDTO("2", "Test 2"))
        );
    }

    @Test
    public void shouldReturnEmptyOptionalWhenUpdatingNonExistingProject(){
        InMemoryProjectService service = new InMemoryProjectService();

        service.createProject("Test 0");
        service.createProject("Test 1");
        service.createProject("Test 2");

        Optional<ProjectDTO> optional = service.updateProject("3", "Updated");

        assertThat(optional, equalTo(Optional.empty()));
    }

    @Test
    public void shouldReturnOptionalProjectDTOWhenUpdatingExistingProject(){
        InMemoryProjectService service = new InMemoryProjectService();

        service.createProject("Test 0");
        service.createProject("Test 1");
        service.createProject("Test 2");

        Optional<ProjectDTO> optional = service.updateProject("1", "Updated");

        assertThat(optional, equalTo(Optional.of(new ProjectDTO("1", "Updated"))));
    }

    @Test
    public void shouldContainsUpdatedProject(){
        InMemoryProjectService service = new InMemoryProjectService();

        service.createProject("Test 0");
        service.createProject("Test 1");
        service.createProject("Test 2");

        service.updateProject("1", "Updated");

        List<ProjectDTO> allProjects = service.getAllProjects();
        assertThat(
                allProjects,
                containsInAnyOrder(new ProjectDTO("0", "Test 0"), new ProjectDTO("1", "Updated"), new ProjectDTO("2", "Test 2"))
        );
    }
}
