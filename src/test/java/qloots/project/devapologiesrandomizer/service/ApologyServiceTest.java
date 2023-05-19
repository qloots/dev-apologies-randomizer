package qloots.project.devapologiesrandomizer.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import qloots.project.devapologiesrandomizer.entity.Apology;
import qloots.project.devapologiesrandomizer.repository.ApologyRepository;
import qloots.project.devapologiesrandomizer.utils.RandomUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ApologyServiceTest {

    @Mock
    private ApologyRepository apologyRepository;

    @Mock
    private RandomUtil randomUtil;

    @InjectMocks
    private ApologyService apologyService;

    private static final Apology apology1 = new Apology();
    private static final Apology apology2 = new Apology();
    private static final Apology apology3 = new Apology();

    @BeforeAll
    static void init() {
        apology1.setHttpCode(418);
        apology1.setTag("TEST 1");
        apology1.setMessage("I am a teapot!");

        apology2.setHttpCode(500);
        apology2.setTag("TEST 2");
        apology2.setMessage("It works on my machine");

        apology3.setHttpCode(404);
        apology3.setTag("TEST 3");
        apology3.setMessage("Developer not found");
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllApologies() {
        // Arrange
        List<Apology> expectedApologies = new ArrayList<>();
        expectedApologies.add(apology1);
        when(apologyRepository.findAll()).thenReturn(expectedApologies);

        // Act
        List<Apology> actualApologies = apologyService.getAllApologies();

        // Assert
        assertEquals(expectedApologies, actualApologies);
        verify(apologyRepository, times(1)).findAll();
    }

    @Test
    void getRandomApologyWithExistingApologies() {
        // Arrange
        List<Apology> allApologies = new ArrayList<>();
        allApologies.add(apology1);
        allApologies.add(apology2);
        allApologies.add(apology3);

        Apology expectedApology = apology2;

        when(apologyService.getAllApologies()).thenReturn(allApologies);
        when(apologyService.getApologyByHttpCode(anyInt())).thenReturn(expectedApology);
        when(randomUtil.getRandomInteger(anyInt(), anyInt())).thenReturn(500);

        // Act
        Apology randomApology = apologyService.getRandomApology();

        // Assert
        assertEquals(expectedApology.getHttpCode(), randomApology.getHttpCode());
        assertEquals(expectedApology.getTag(), randomApology.getTag());
        assertEquals(expectedApology.getMessage(), randomApology.getMessage());
    }

    @Test
    void getRandomApologyWithNoApologies() {
        // Arrange
        when(apologyService.getAllApologies()).thenReturn(new ArrayList<>());

        // Act
        Apology randomApology = apologyService.getRandomApology();

        // Assert
        assertEquals(418, randomApology.getHttpCode());
        assertEquals("NOT IN DATABASE", randomApology.getTag());
        assertEquals("I am a Tea Pot", randomApology.getMessage());
        verify(randomUtil, never()).getRandomInteger(anyInt(), anyInt());
    }

    @Test
    void createApology() {
        // Arrange
        Apology expectedApology = apology1;
        when(apologyRepository.save(expectedApology)).thenReturn(expectedApology);

        // Act
        Apology createdApology = apologyService.createApology(expectedApology);

        // Assert
        assertEquals(expectedApology, createdApology);
        verify(apologyRepository, times(1)).save(expectedApology);
    }

    @Test
    void getApologyByHttpCode() {
        // Arrange
        int httpCode = 404;
        Apology expectedApology = apology3;
        when(apologyRepository.findByHttpCode(httpCode)).thenReturn(expectedApology);

        // Act
        Apology actualApology = apologyService.getApologyByHttpCode(httpCode);

        // Assert
        assertEquals(expectedApology, actualApology);
        verify(apologyRepository, times(1)).findByHttpCode(httpCode);
    }
}