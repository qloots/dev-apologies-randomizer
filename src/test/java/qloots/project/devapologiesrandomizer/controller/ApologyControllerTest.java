package qloots.project.devapologiesrandomizer.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import qloots.project.devapologiesrandomizer.entity.Apology;
import qloots.project.devapologiesrandomizer.service.ApologyService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ApologyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ApologyService apologyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ApologyController apologyController = new ApologyController(apologyService);
        mockMvc = MockMvcBuilders.standaloneSetup(apologyController).build();
    }

    @Test
    void getAllApologies() throws Exception {
        // Given
        Apology apology1 = new Apology();
        apology1.setHttpCode(418);
        apology1.setTag("TEST 1");
        apology1.setMessage("I am a teapot!");
        Apology apology2 = new Apology();
        apology2.setHttpCode(500);
        apology2.setTag("TEST 2");
        apology2.setMessage("It works on my machine");
        List<Apology> apologies = Arrays.asList(apology1, apology2);

        when(apologyService.getAllApologies()).thenReturn(apologies);

        // When & Then
        mockMvc.perform(get("/api/apologies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].httpCode").value(apology1.getHttpCode()))
                .andExpect(jsonPath("$[0].tag").value(apology1.getTag()))
                .andExpect(jsonPath("$[0].message").value(apology1.getMessage()))
                .andExpect(jsonPath("$[1].httpCode").value(apology2.getHttpCode()))
                .andExpect(jsonPath("$[1].tag").value(apology2.getTag()))
                .andExpect(jsonPath("$[1].message").value(apology2.getMessage()));

        verify(apologyService, times(1)).getAllApologies();
        verifyNoMoreInteractions(apologyService);
    }

    @Test
    void getApologyByHttpCode() throws Exception {
        // Given
        int httpCode = 418;
        Apology apology = new Apology();
        apology.setHttpCode(httpCode);
        apology.setTag("TEST");
        apology.setMessage("I am a teapot!");

        when(apologyService.getApologyByHttpCode(httpCode)).thenReturn(apology);

        // When & Then
        mockMvc.perform(get("/api/apologies/{httpCode}", httpCode))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.httpCode").value(apology.getHttpCode()))
                .andExpect(jsonPath("$.tag").value(apology.getTag()))
                .andExpect(jsonPath("$.message").value(apology.getMessage()));

        verify(apologyService, times(1)).getApologyByHttpCode(httpCode);
        verifyNoMoreInteractions(apologyService);
    }

    @Test
    void getRandomApology() throws Exception {
        // Given
        Apology apology = new Apology();
        apology.setHttpCode(418);
        apology.setTag("TEST");
        apology.setMessage("I am a teapot!");

        when(apologyService.getRandomApology()).thenReturn(apology);

        // When & Then
        mockMvc.perform(get("/api/apologies/random"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.httpCode").value(apology.getHttpCode()))
                .andExpect(jsonPath("$.tag").value(apology.getTag()))
                .andExpect(jsonPath("$.message").value(apology.getMessage()));

        verify(apologyService, times(1)).getRandomApology();
        verifyNoMoreInteractions(apologyService);
    }

    @Test
    void createApology() throws Exception {
        // Given
        Apology apology = new Apology();
        apology.setHttpCode(418);
        apology.setTag("TEST");
        apology.setMessage("I am a teapot!");

        when(apologyService.createApology(any(Apology.class))).thenReturn(apology);

        // When & Then
        mockMvc.perform(post("/api/apologies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "httpCode": 418,
                                    "tag": "TEST",
                                    "message": "I am a teapot!"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.httpCode").value(apology.getHttpCode()))
                .andExpect(jsonPath("$.tag").value(apology.getTag()))
                .andExpect(jsonPath("$.message").value(apology.getMessage()));

        verify(apologyService, times(1)).createApology(any(Apology.class));
        verifyNoMoreInteractions(apologyService);
    }
}