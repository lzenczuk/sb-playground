package pl.lukaz.sptw.service.project.inmemory.model;

/**
 * @author lzenczuk 31/07/2015
 */
public class Project {
    private String id;
    private String title;

    public Project(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
