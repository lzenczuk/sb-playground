package pl.lukaz.sptw.rest.model.project;

/**
 * @author lzenczuk 31/07/2015
 */

public class RestProject {
    private final String id;
    private final String title;

    public RestProject(String id, String title) {
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
