package qloots.project.devapologiesrandomizer.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RandomUtilTest {

    @Test
    void testGetRandomInteger() {
        // Given
        RandomUtil randomUtil = new RandomUtil();
        int minimum = 1;
        int maximum = 10;

        // When
        int randomInteger = randomUtil.getRandomInteger(maximum, minimum);

        // Then
        assertTrue(randomInteger >= minimum && randomInteger <= maximum);
    }
}