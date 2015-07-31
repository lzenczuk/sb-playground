package pl.lukaz.sptw.service.project.model;

/**
 * @author lzenczuk 31/07/2015
 */
public class ProjectDTO {
    private final String id;
    private final String title;

    public ProjectDTO(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
