import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class HorseTest {

    @ParameterizedTest
    @NullSource
    public void createHorseWithNameIsNullTest(String name) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse(name, 1.0, 3.0);
                }
        );

        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    public void createHorseWithNotCorrectNameTest(String name) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse(name, 2.0, 8.0);
                }
        );

        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void createHorseWithSpeedNegativeTest() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse("Tony", -2.0, 10.0);
                }
        );

        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void createHorseWithDistanceNegativeTest() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse("Tony", 2.0, -10.0);
                }
        );

        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void createHorseWithSpeedAndDistanceNegativeTest() {
        Throwable expected = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse("Star", -1.0, -3.0);
                }
        );
    }

    @Test
    public void createHorseWithNameAndSpeedAndDistanceWrongValueTest() {
        Throwable expected = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse("", -1.0, -3.0);
                }
        );
    }

    @Test
    public void getNameSpeedDistanceTest() {
        Horse horse = new Horse("Maya", 3.5, 23.4);

        assertAll("Equals object Horse",
                () -> assertEquals("Maya", horse.getName()),
                () -> assertEquals(3.5, horse.getSpeed()),
                () -> assertEquals(23.4, horse.getDistance()));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.8, 0.9})
    void moveTest(double arg) {

        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(arg);
            Horse horse = new Horse("Tony", 5.0, 12.0);

            horse.move();
            assertEquals(12.0 + 5.0 * arg, horse.getDistance());
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
}
