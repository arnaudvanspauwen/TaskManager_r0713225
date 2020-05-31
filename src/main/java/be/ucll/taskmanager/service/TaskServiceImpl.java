package be.ucll.taskmanager.service;

import be.ucll.taskmanager.domain.SubTask;
import be.ucll.taskmanager.domain.Task;
import be.ucll.taskmanager.dto.SubTaskDTO;
import be.ucll.taskmanager.dto.TaskDTO;
import be.ucll.taskmanager.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepo repo;

    @Autowired
    public TaskServiceImpl(TaskRepo repo){
        this.repo = repo;
    }

    @Override
    public List<TaskDTO> getTasks() {
        return repo.findAll().stream().map(h -> {
            TaskDTO dto = new TaskDTO();
            dto.setId(h.getId());
            dto.setTitle(h.getTitle());
            dto.setDesc(h.getDesc());
            dto.setDate(h.getDate());

            dto.setSubTasks(h.getSubtasks()
                    .stream().map(s -> {
                        SubTaskDTO subtaskDTO = new SubTaskDTO();
                        subtaskDTO.setId(s.getId());
                        subtaskDTO.setTitle(s.getTitle());
                        subtaskDTO.setDescription(s.getDescription());

                        return subtaskDTO;
                    }).collect(Collectors.toList())
            );
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTaskDTOById(UUID id) {
        Task task = repo.getOne(id);
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDesc(task.getDesc());
        dto.setDate(task.getDate());

        dto.setSubTasks(task.getSubtasks()
                .stream().map(s -> {
                    SubTaskDTO subtaskDTO = new SubTaskDTO();
                    subtaskDTO.setId(s.getId());
                    subtaskDTO.setTitle(s.getTitle());
                    subtaskDTO.setDescription(s.getDescription());

                    return subtaskDTO;
                }).collect(Collectors.toList())
        );

        return dto;
    }


    @Override
    public void addTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDesc(taskDTO.getDesc());
        task.setDate(taskDTO.getDate());

        repo.save(task);

    }
    @Override
    @Transactional
    public void editTaskByTaskDTO(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDesc(taskDTO.getDesc());
        task.setDate(taskDTO.getDate());

        repo.save(task);
    }


    @Override
    public void editTask(UUID id, TaskDTO task){
        Task t = repo.getOne(id);
        t.setId(task.getId());
        t.setDesc(task.getDesc());
        t.setDate(task.getDate());
        t.setTitle(task.getTitle());
        repo.save(t);
    }


    @Override
    public List<SubTask> getSubTasks(UUID id) {
        Task t = repo.getOne(id);
        List<SubTask> subtasks = new ArrayList<>();
        subtasks = t.getSubtasks();
        return subtasks;
    }

   @Override
   public Task getTaskById(UUID id) {

       return repo.getOne(id);
   }

   @Override
   @Transactional
   public void addSubtask(UUID mainTaskId, SubTaskDTO subtaskDTO) {
       SubTask subtask = new SubTask();
       subtask.setTitle(subtaskDTO.getTitle());
       subtask.setDescription(subtaskDTO.getDescription());

       Task task = getTaskById(mainTaskId);

       task.addSubtasks(subtask);

       repo.save(task);
   }
}
