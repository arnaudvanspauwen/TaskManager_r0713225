package be.ucll.taskmanager.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

public class SubTaskDTO {
    @Id
    private UUID id;
    @NotEmpty(message = "Title can't be empty.")
    @Size(min=2, max = 50)
    private String title;
    @NotEmpty(message = "Description can't be empty.")
    @Size(max=150)
    private String description;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
