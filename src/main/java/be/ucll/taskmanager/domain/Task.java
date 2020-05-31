package be.ucll.taskmanager.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Task {
    @Id
    @GeneratedValue
    private UUID id;

    @NotEmpty(message = "Title can't be empty.")
    @Size(min=2, max=50)
    private String title;

    @NotEmpty(message = "Description can't be empty.")
    @Size(max=150)
    private String desc;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull(message = "Date can't be empty.")
    @Future(message = "Task can't be in de past.")
    private LocalDateTime date;

    @OneToMany(cascade= CascadeType.ALL)
    private List<SubTask> subtasks;

    public Task(String title, String desc, LocalDateTime date, List<SubTask> subt) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.subtasks = subt;
    }

    public Task(String title, String description, LocalDateTime date) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.date = date;
        this.desc = description;
        this.subtasks = new ArrayList<>();
    }

    public Task() {
        this.id = UUID.randomUUID();
        this.subtasks = new ArrayList<>();
    }

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<SubTask> getSubtasks() {
        return subtasks;
    }

    public void addSubtasks(SubTask subtask) {
        subtasks.add(subtask);
    }
    public void setSubTasks(List<SubTask> subTasks) { this.subtasks = subTasks; }
}
