package be.ucll.taskmanager.controller;

import be.ucll.taskmanager.domain.SubTask;
import be.ucll.taskmanager.domain.Task;
import be.ucll.taskmanager.dto.SubTaskDTO;
import be.ucll.taskmanager.dto.TaskDTO;
import be.ucll.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService)
    {
        this.taskService = taskService;
    }

    /*@GetMapping("/error")
    public String error(Model model) {
        model.addAttribute("page", "Error");
        return "error";
    }*/

    @GetMapping("/")
    public String getredirect(Model model){
        model.addAttribute("tasks", taskService.getTasks());
        return "taskoverview";
    }

    @GetMapping("/tasks")
    public String getTasks(Model model){
        model.addAttribute("tasks", taskService.getTasks());
        return "taskoverview";
    }

    @GetMapping("/tasks/{id}")
    public String getTaskById(@PathVariable("id") UUID id, Model model){
        model.addAttribute("detail", taskService.getTaskById(id));
        return "taskdetails";
    }

    @PostMapping("/tasks/new")
    public String createNewTask(@ModelAttribute("task") @Valid TaskDTO task, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "tasknew";
        }
        taskService.addTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/new")
    public String getNewTaskForm(Model model){
        model.addAttribute("task", new TaskDTO());
        return "tasknew";
    }

    @GetMapping(path = "/tasks/edit/{id}")
    public String getEditTask(@PathVariable("id") UUID id, Model model){
        model.addAttribute("task", taskService.getTaskById(id));
        return "taskedit";
    }
    @PostMapping(path = "/tasks/edit/{id}")
    public String postEditTask(@PathVariable("id") UUID id, @ModelAttribute("task") @Valid TaskDTO task, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "taskedit";
        }
        taskService.editTask(id,task);
        return "redirect:/tasks/"+id;
    }

    @GetMapping(path = "/tasks/{id}/sub/create")
    public String createSubTaskGet(@PathVariable("id") UUID id,Model model){
        model.addAttribute("task", taskService.getTaskDTOById(id));
        model.addAttribute("subtask", new SubTask());
        return "tasksubtask";
    }

    @PostMapping(path = "/tasks/{id}/sub/create")
    public String createSubTaskPost(Model model, @PathVariable("id") UUID id, @ModelAttribute("subtask") @Valid SubTaskDTO subtask, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("task", taskService.getTaskDTOById(id));
            return "tasksubtask";
        }
        taskService.addSubtask(id, subtask);
        return "redirect:/tasks/"+id;
    }


}

