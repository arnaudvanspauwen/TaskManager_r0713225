package be.ucll.taskmanager.model.service;

import be.ucll.taskmanager.dto.SubTaskDTO;
import be.ucll.taskmanager.dto.TaskDTO;
import be.ucll.taskmanager.service.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
@SpringBootTest
public class TaskServiceImplTest {

    @Autowired
    private TaskServiceImpl taskService;
    @Transactional
    @BeforeEach
    public void setUp() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Task1");
        taskDTO.setDesc("Lorum Ipsum");
        taskDTO.setDate(LocalDateTime.of(2021, 3, 10, 10, 0));
        taskDTO.setSubTasks(new ArrayList<SubTaskDTO>());
        taskService.addTask(taskDTO);
    }

    @Transactional
    @Test
    public void test_Get_Subtaks() {
        SubTaskDTO SubTaskDTO = new SubTaskDTO();
        SubTaskDTO.setTitle("Test");
        SubTaskDTO.setDescription("Test");
        SubTaskDTO.setId(taskService.getTasks().get(0).getId());
        taskService.addSubtask(taskService.getTasks().get(0).getId(),SubTaskDTO);
        assertEquals(taskService.getSubTasks(taskService.getTasks().get(0).getId()).size(), 1);
        taskService.addSubtask(taskService.getTasks().get(0).getId(),SubTaskDTO);
        assertEquals(taskService.getSubTasks(taskService.getTasks().get(0).getId()).size(), 2);
    }

    @Transactional
    @Test
    public void test_Get_Tasks() {
        List<TaskDTO> tasks = taskService.getTasks();
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
        TaskDTO task = tasks.get(0);
        assertNotNull(task);
        assertEquals("Task1", task.getTitle());
        assertEquals("Lorum Ipsum", task.getDesc());
        assertEquals(LocalDateTime.of(2021, 3, 10, 10, 0),task.getDate());
    }

    @Test
    public void test_Edit_Task() {
        TaskDTO TaskDTO = new TaskDTO();
        TaskDTO.setId(taskService.getTasks().get(0).getId());
        TaskDTO.setDesc("Aangepast");
        TaskDTO.setTitle("Aangepast");
        TaskDTO.setDate(LocalDateTime.of(2021,3,10,10,0));
        taskService.editTask(taskService.getTasks().get(0).getId(),TaskDTO);
        assertEquals(taskService.getTasks().get(0).getTitle(), TaskDTO.getTitle());
        assertEquals(taskService.getTasks().get(0).getId(), TaskDTO.getId());
        assertEquals(taskService.getTasks().get(0).getDesc(), TaskDTO.getDesc());
        assertEquals(taskService.getTasks().get(0).getDate(), TaskDTO.getDate());
    }
}
