package pl.lukaz.sptw.service.project.inmemory.model;

/**
 * @author lzenczuk 31/07/2015
 */
public class Project {
    private final String id;
    private final String title;

    public Project(String id, String title) {
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
