package visualization;

import org.junit.Test;
import world.World;
import world.WorldBuilder;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConsoleVisualisationTest {

    @Test public void
    should_print_world_with_correct_dimentions() {
        World deadWorld = new WorldBuilder().withDimentions(2, 5).allDead().build();
        assertThat(new ConsoleVisualization().print(deadWorld), equalTo(". .\n. .\n. .\n. .\n. .\n"));
    }

    @Test public void
    should_print_live_cell() {
        World withOneLiveCell = new WorldBuilder().withDimentions(2, 5).allDead().withLiveCellAt(1,3).build();
        assertThat(new ConsoleVisualization().print(withOneLiveCell), equalTo(". .\n. .\n. .\n. *\n. .\n"));
    }

    @Test public void
    should_print_empty_world() {
        World deadWorld = new WorldBuilder().withDimentions(0, 0).allDead().build();
        assertThat(new ConsoleVisualization().print(deadWorld), equalTo(""));
    }
}
