package be.ucll.taskmanager.service;

import be.ucll.taskmanager.domain.SubTask;
import be.ucll.taskmanager.domain.Task;
import be.ucll.taskmanager.dto.SubTaskDTO;
import be.ucll.taskmanager.dto.TaskDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<TaskDTO> getTasks();

    void addTask(TaskDTO task);

    public TaskDTO getTaskDTOById(UUID id);

    public Task getTaskById(UUID id);

    public void editTask(UUID id,TaskDTO task);

    public void addSubtask(UUID mainTaskId, SubTaskDTO subtaskDTO);

    public void editTaskByTaskDTO(TaskDTO taskDTO);

    public List<SubTask> getSubTasks(UUID id);

   /* List<TaskDTO> getTasks();
    void addTask(TaskDTO task);

    public void getTaskDTOById(TaskDTO taskDTO);
    public TaskDTO getTaskDTOById(UUID id);

    public void editTask(Task task);
     void editTaskByTaskDTO(TaskDTO taskDTO);

    void editTask(UUID id, Task task);


    void createSubTask(UUID id, SubTask subtask);*/
}
