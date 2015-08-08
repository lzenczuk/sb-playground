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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectDTO that = (ProjectDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return !(title != null ? !title.equals(that.title) : that.title != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProjectDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
