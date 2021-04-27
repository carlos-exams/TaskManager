package com.mimacom.taskmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mimacom.taskmanager.controllers.TaskController;
import com.mimacom.taskmanager.dto.request.TaskCreateDto;
import com.mimacom.taskmanager.dto.request.TaskEditDto;
import com.mimacom.taskmanager.dto.response.TaskDto;
import com.mimacom.taskmanager.persistence.model.Task;
import com.mimacom.taskmanager.persistence.model.TaskStatus;
import com.mimacom.taskmanager.services.ITaskService;
import com.mimacom.taskmanager.services.ITaskStatusService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.thymeleaf.util.StringUtils;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TaskController.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TaskControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITaskService taskService;

    @MockBean
    private ITaskStatusService taskStatusService;

    Date creationDate;
    TaskStatus mockStatus;

    Task mockTask;
    List<Task> mockTaskList;

    TaskDto mockTaskDto;
    List<TaskDto> mockTaskDtoList;

    @Before
    public void setUp() throws Exception
    {
        Calendar cal = Calendar.getInstance();
        cal.set(2021, Calendar.JANUARY, 10);

        creationDate = cal.getTime();

        mockStatus = new TaskStatus(1, creationDate, creationDate, "Created","This is a new task");

        mockTask = new Task(1, creationDate, creationDate,"ReturnedTask","ReturnedDescription", mockStatus);
        mockTaskList = new ArrayList<Task>(Arrays.asList(mockTask));

        mockTaskDto = new TaskDto(mockTask);
        mockTaskDtoList = new ArrayList<TaskDto>(Arrays.asList(mockTaskDto));
    }

    @Test
    public void retrieveTask() throws Exception {

        Mockito.when(taskService.findAll()).thenReturn(mockTaskList);

        mockMvc.perform(get("/task"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(mockTaskList.size())));
    }

    @Test
    public void createTask() throws Exception {

        TaskCreateDto taskCreateDto = new TaskCreateDto("NewTask","newDescription",1);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(taskCreateDto);

        Mockito.when(taskService.create(Mockito.any(Task.class))).thenReturn(true);
        Mockito.when(taskStatusService.findById(Mockito.any(Integer.class))).thenReturn(Optional.ofNullable(mockStatus));

        mockMvc.perform(post("/task").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void createTaskFailsTaskStatusIdNotRecognised() throws Exception {

        TaskCreateDto taskCreateDto = new TaskCreateDto("NewTask","newDescription",1);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(taskCreateDto);

        Mockito.when(taskService.create(Mockito.any(Task.class))).thenReturn(true);

        String error = mockMvc.perform(post("/task").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest())
                .andReturn().getResolvedException().getMessage();

        assertTrue(StringUtils.contains(error, "Provided Task Status Doesn´t exist"));
    }

    @Test
    public void editTaskChangingDescription() throws Exception {

        TaskEditDto editCreateDto = new TaskEditDto(1,"new description",null);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(editCreateDto);

        Mockito.when(taskService.update(Mockito.any(Task.class))).thenReturn(true);
        Mockito.when(taskService.findById(Mockito.any(Integer.class))).thenReturn(Optional.ofNullable(mockTask));
        Mockito.when(taskStatusService.findById(Mockito.any(Integer.class))).thenReturn(Optional.ofNullable(mockStatus));

        mockMvc.perform(put("/task").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());
    }
    @Test
    public void editTaskErrorTaskNotFound() throws Exception {
        TaskEditDto editCreateDto = new TaskEditDto(1,"new description",null);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(editCreateDto);

        Mockito.when(taskService.update(Mockito.any(Task.class))).thenReturn(true);
        Mockito.when(taskService.findById(Mockito.any(Integer.class))).thenReturn(Optional.empty());
        Mockito.when(taskStatusService.findById(Mockito.any(Integer.class))).thenReturn(Optional.ofNullable(mockStatus));

        String error = mockMvc.perform(put("/task").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest())
                .andReturn().getResolvedException().getMessage();

        assertTrue(StringUtils.contains(error, "Provided Task Doesn´t exist"));
    }

    @Test
    public void editTaskErrorTaskStatusNotFound() throws Exception {
        TaskEditDto editCreateDto = new TaskEditDto(1,"new description",2);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(editCreateDto);

        Mockito.when(taskService.update(Mockito.any(Task.class))).thenReturn(true);
        Mockito.when(taskService.findById(Mockito.any(Integer.class))).thenReturn(Optional.ofNullable(mockTask));
        Mockito.when(taskStatusService.findById(Mockito.any(Integer.class))).thenReturn(Optional.empty());

        String error = mockMvc.perform(put("/task").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest())
                .andReturn().getResolvedException().getMessage();

        assertTrue(StringUtils.contains(error, "Provided Task Status Doesn´t exist"));
    }

}
