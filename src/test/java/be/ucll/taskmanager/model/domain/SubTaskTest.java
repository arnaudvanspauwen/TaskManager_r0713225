package be.ucll.taskmanager.model.domain;

import be.ucll.taskmanager.domain.SubTask;
import be.ucll.taskmanager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SubTaskTest {
    private TaskService taskService;

    @Test
    public void test_Set_Id() {
        SubTask task = new SubTask();
        UUID id = UUID.randomUUID();
        task.setId(id);
        UUID result = task.getId();
        assertNotNull(result);
        assertEquals(id,result);
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
        assertEquals("description",task.getDescription());
    }

    @Test
    public void test_Get_Description() {
        SubTask task = new SubTask();
        task.setDescription("desc");
        String desc = task.getDescription();
        assertEquals("desc", desc);
    }

    @Test
    public void test_Constructor() {
        SubTask task = new SubTask("title", "desc");
        assertEquals("title", task.getTitle());
        assertEquals("desc", task.getDescription());
    }
}
