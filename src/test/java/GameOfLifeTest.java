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
    private World emptyWorld = new WorldBuilder().withDimentions(0,0).build();

    @Test public void
    should_return_next_game_of_life_step() {
        assertThat(new GameOfLife(emptyWorld, visualization).next().print(), equalTo(visualization.print(new World(null))));
    }

    @Test public void
    should_return_next_step_for_dead_world() {
        World deadWorld = new WorldBuilder().withDimentions(2, 2).allDead().build();
        assertThat(new GameOfLife(deadWorld, visualization).next().print(), equalTo(". .\n. .\n"));
    }

    @Test public void
    should_return_world_with_correct_dimentions() {
        World deadWorld = new WorldBuilder().withDimentions(2, 5).allDead().build();
        assertThat(new GameOfLife(deadWorld, visualization).next().print(), equalTo(". .\n. .\n. .\n. .\n. .\n"));
    }

    @Test public void
    should_use_passed_visualization_to_print() {
        ConsoleVisualization visualization = mock(ConsoleVisualization.class);
        new GameOfLife(emptyWorld, visualization).next().print();
        verify(visualization, times(1)).print(emptyWorld);
    }

    @Test public void
    should_die_if_starts_from_single_live_cell() {
        World withOneLiveCell = new WorldBuilder().withDimentions(3, 3).allDead().withLiveCellAt(0,0).build();
        String nextStep = new GameOfLife(withOneLiveCell, visualization).next().print();
        assertThat(nextStep, equalTo(". . .\n. . .\n. . .\n"));
    }

    @Test public void
    should_die_if_has_more_than_3_neighbours() {
        World withOneLiveCell = new WorldBuilder().withDimentions(3, 3).allLive().build();
        String nextStep = new GameOfLife(withOneLiveCell, visualization).next().print();
        assertThat(nextStep, equalTo("* . *\n. . .\n* . *\n"));
    }

    @Test public void
    should_become_live_if_has_3_neighbours() {
        World withOneLiveCell = new WorldBuilder().withDimentions(3, 3).allDead()
                .withLiveCellAt(0, 0)
                .withLiveCellAt(0, 1)
                .withLiveCellAt(0 ,2)
                .withLiveCellAt(1, 1)
                .build();
        String nextStep = new GameOfLife(withOneLiveCell, visualization).next().print();
        assertThat(nextStep, equalTo("* * .\n* * .\n* * .\n"));
    }

    @Test public void
    should_work_for_two_steps_in_a_row() {
        World withOneLiveCell = new WorldBuilder().withDimentions(3, 3).allDead()
                .withLiveCellAt(0, 0)
                .withLiveCellAt(0, 1)
                .withLiveCellAt(0 ,2)
                .withLiveCellAt(1, 1)
                .build();
        String nextStep = new GameOfLife(withOneLiveCell, visualization).next().next().print();
        assertThat(nextStep, equalTo("* * .\n. . *\n* * .\n"));
    }



}
