import org.junit.Test;
import visualization.ConsoleVisualization;
import visualization.Visualization;
import world.World;
import world.WorldBuilder;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GameOfLifeTest{

    private Visualization visualization = new ConsoleVisualization();
    private World emptyWorld = new WorldBuilder().aPlane().build();

    @Test public void
    should_return_next_game_of_life_step() {
        assertThat(new GameOfLife(emptyWorld).next().print(visualization), equalTo(new World(null).print(visualization)));
    }

    @Test public void
    should_return_next_step_for_dead_world() {
        World deadWorld = new WorldBuilder().aPlane().withSize(2).allDead().build();
        assertThat(new GameOfLife(deadWorld).next().print(visualization), equalTo("d d\nd d"));
    }

    @Test public void
    should_use_passed_visualization_to_print() {
        ConsoleVisualization visualization = mock(ConsoleVisualization.class);
        new GameOfLife(emptyWorld).next().print(visualization);
        verify(visualization, times(1)).print();
    }


}
