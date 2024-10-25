import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class HippodromeTest {


    private List<Horse> getListHorses(int countHorses) {
        List<Horse> result = new ArrayList<>();
        for (int i = 0; i < countHorses; i++) {
            String name = "Horse" + i;
            double speed = i;
            double distance = i + i;
            result.add(new Horse(name, speed, distance));
        }
        return result;
    }

    @ParameterizedTest
    @NullSource
    public void createHippodromeWithNullArgTest(List<Horse> horses) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Hippodrome hippodrome = new Hippodrome(horses);
                }
        );

        assertEquals(exception.getMessage(), "Horses cannot be null.");
    }

    @Test
    public void createHippodromeWithEmptyListTest() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Hippodrome hippodrome = new Hippodrome(new ArrayList<>());
                }
        );

        assertEquals(exception.getMessage(), "Horses cannot be empty.");
    }

    @Test
    public void getHorseTest() {
        List<Horse> expected = getListHorses(30);
        Hippodrome hippodrome = new Hippodrome(expected);

        assertEquals(expected, hippodrome.getHorses());
    }

    @Test
    public void moveTest() {
        List<Horse> horseList = getListHorses(50).stream()
                .map(horse -> Mockito.mock(Horse.class))
                .collect(Collectors.toList());

        Hippodrome hippodrome = new Hippodrome(horseList);

        hippodrome.move();

        horseList.forEach(horse -> Mockito.verify(horse).move());
    }

    @Test
    public void getWinnerTest() {
        List<Horse> horses = getListHorses(20);
        Horse horse = horses.stream()
                .max(Comparator.comparingDouble(Horse::getDistance))
                .get();

        Hippodrome hippodrome1 = new Hippodrome(horses);

        assertEquals(horse.getDistance(), hippodrome1.getWinner().getDistance());
    }
}
