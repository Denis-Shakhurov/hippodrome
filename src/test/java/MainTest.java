import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainTest {

    /*@Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    public void mainTest() {
        try (MockedStatic<Main> mockedStatic = Mockito.mockStatic(Main.class)) {
            List<Horse> horses = List.of(
                    new Horse("Bucephalus", 2.4),
                    new Horse("Ace of Spades", 2.5),
                    new Horse("Zephyr", 2.6),
                    new Horse("Blaze", 2.7),
                    new Horse("Lobster", 2.8),
                    new Horse("Pegasus", 2.9),
                    new Horse("Cherry", 3)
            );
            Hippodrome hippodrome = new Hippodrome(horses);

            String[] args = new String[1];
            args[0] = hippodrome.toString();

            mockedStatic.verify(() -> Main.main(args));
        }
    }*/
}
