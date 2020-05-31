package be.ucll.taskmanager.model.DTO;

import be.ucll.taskmanager.domain.SubTask;
import be.ucll.taskmanager.dto.SubTaskDTO;
import be.ucll.taskmanager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SubTaskDTOTest {
    private TaskService taskService;

    @Test
    public void test_Set_Id() {
        SubTaskDTO task = new SubTaskDTO();
        UUID uuid= UUID.randomUUID();
        task.setId(uuid);
        assertEquals(uuid, task.getId());
    }

    @Test
    public void test_Get_Id() {
        SubTaskDTO task = new SubTaskDTO();
        task.getId();

        UUID uuid= UUID.randomUUID();
        task.setId(uuid);
        assertEquals(uuid, task.getId());
    }


    @Test
    public void test_Set_Title() {
        SubTask task = new SubTask();
        task.setTitle("title");
        assertEquals("title", task.getTitle());
    }

    @Test
    public void test_Get_Title() {
        SubTask task = new SubTask();
        task.setTitle("title");
        String title = task.getTitle();
        assertEquals("title", title);
    }

    @Test
    public void test_Set_Description() {
        SubTask task = new SubTask();
        task.setDescription("description");
        assertEquals("description", task.getDescription());
    }

    @Test
    public void test_Get_Description() {
        SubTask task = new SubTask();
        task.setDescription("description");
        String desc = task.getDescription();
        assertEquals("description", desc);
    }
}
