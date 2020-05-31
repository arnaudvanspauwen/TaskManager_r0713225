package be.ucll.taskmanager.model.domain;

import be.ucll.taskmanager.domain.SubTask;
import be.ucll.taskmanager.domain.Task;
import be.ucll.taskmanager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskTest {
    private TaskService taskService;
    private Task task = new Task();



    @Test
    public void test_Create_Task() {
        Task task = new Task("title", "desc",LocalDateTime.of(2021, 3, 10, 10, 0));

        assertEquals("title", task.getTitle());
        assertEquals("desc", task.getDesc());
        assertEquals(LocalDateTime.of(2021, 3, 10, 10, 0), task.getDate());
    }
    
    @Test
    public void test_Set_Title() {
        Task task = new Task();
        task.setTitle("title");
        assertEquals("title", task.getTitle());
    }

    @Test
    public void test_Get_Title() {
        Task task = new Task();
        task.setTitle("title");
        String title = task.getTitle();
        assertEquals("title", title);
    }

    @Test
    public void test_Set_Description() {
        Task task = new Task();
        task.setDesc("description");
        assertEquals("description", task.getDesc());
    }

    @Test
    public void test_Get_Description() {
        Task task = new Task();
        task.setDesc("description");
        String desc = task.getDesc();
        assertEquals("description", desc);
    }

    @Test
    public void test_Set_DueDate() {
        Task task = new Task();
        task.setDate(LocalDateTime.of(2021, 05, 05, 20, 20));
        assertEquals(LocalDateTime.of(2021, 05, 05, 20, 20), task.getDate());
    }

    @Test
    public void test_Get_DueDate() {
        Task task = new Task();
        task.setDate(LocalDateTime.of(2021, 05, 05, 20, 20));
        LocalDateTime dueDate = task.getDate();
        assertEquals(LocalDateTime.of(2021, 05, 05, 20, 20), dueDate);
    }

    @Test
    public void test_Set_Id(){
        UUID id = UUID.randomUUID();
        task.setId(id);
        UUID result = task.getId();
        assertNotNull(result);
        assertEquals(id,result);
    }

    @Test
    public void test_Set_SubTasks() {
        Task task = new Task();
        List<SubTask> subTasks = new ArrayList<SubTask>();
        SubTask subTask = new SubTask();
        subTask.setTitle("title");
        subTask.setDescription("description");
        subTasks.add(subTask);
        task.setSubTasks(subTasks);
        assertNotNull(task.getSubtasks());
        assertFalse(task.getSubtasks().size() == 0);
        assertEquals(subTask, task.getSubtasks().get(0));
    }

    @Test
    public void test_Get_SubTasks() {
        Task task = new Task();
        List<SubTask> subTasks = new ArrayList<SubTask>();
        SubTask subTask = new SubTask();
        subTask.setTitle("title");
        subTask.setDescription("description");
        subTasks.add(subTask);
        task.setSubTasks(subTasks);

        List<SubTask> subtasks = task.getSubtasks();;

        assertNotNull(subtasks);
        assertFalse(subtasks.size() == 0);
        assertEquals(subTask, subtasks.get(0));
    }

    @Test
    public void test_Add_SubTask() {
        Task task = new Task();
        List<SubTask> subTasks = new ArrayList<SubTask>();
        SubTask subTask = new SubTask();
        subTask.setTitle("title");
        subTask.setDescription("description");
        subTasks.add(subTask);

        task.setSubTasks(subTasks);

        assertNotNull(task.getSubtasks());
        assertFalse(task.getSubtasks().size() == 0);
        assertEquals(subTask, task.getSubtasks().get(0));
        assertEquals("title", task.getSubtasks().get(0).getTitle());
        assertEquals("description", task.getSubtasks().get(0).getDescription());
    }
}
