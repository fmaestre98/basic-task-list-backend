package com.basictasklist.basictasklist;

import com.basictasklist.basictasklist.entities.Task;
import com.basictasklist.basictasklist.repositories.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class BasicTaskListApplicationIntegrationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void shouldReturnAllTasks() throws Exception {


        // Agregar algunas tareas a la base de datos utilizando el repositorio
        taskRepository.save(new Task(1, 1, "Tarea 1", false));
        taskRepository.save(new Task(1, 1, "Tarea 2", true));

        // Realizar una solicitud GET a la API REST para obtener todas las tareas
        mockMvc.perform(get("/api/tasks")).andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(new MediaType("application", "*+json"))).andExpect(jsonPath("$._embedded.tasks[0].description").value("Tarea 1")).andExpect(jsonPath("$._embedded.tasks[0].done").value(false)).andExpect(jsonPath("$._embedded.tasks[1].description").value("Tarea 2")).andExpect(jsonPath("$._embedded.tasks[1].done").value(true));

        taskRepository.deleteAll();
    }

    @Test
    public void shouldCreateNewTask() throws Exception {

        // Realizar una solicitud GET a la API REST para obtener todas las tareas
        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "                    \"user_id\": 1,\n" +
                                "                    \"description\": \"New Task\",\n" +
                                "                    \"done\": false\n" +
                                "                }"))
                .andExpect(status().isCreated());

        taskRepository.deleteAll();
    }


}

