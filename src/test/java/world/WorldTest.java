package world;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class WorldTest {

    @Test public void
    empty_world_should_equal_empty_world() {
        assertThat(new World(null).equals(new World(null)), is(true));
    }

    @Test public void
    world_with_values_should_equal_to_world_with_same_values() {
        boolean[][] values = new boolean[2][2];
        for (int i = 0; i < 2; ++i)
            for (int j = 0; j < 2; ++j)
                values[i][j] = World.DEAD;

        assertThat(new World(values).equals(new World(values)), is(true));
    }

    @Test public void
    should_correctly_count_live_neighbours() {
       World world = new WorldBuilder().withDimentions(5, 1).allDead().build();
       boolean[][] cells = world.getCells();
       for (int i = 0; i < world.getWidth(); ++i)
           for (int j = 0; j < world.getHeight(); ++j)
               assertThat(world.countLiveNeighboursOf(i,j), is(0));
    }

    @Test public void
    should_correctly_count_live_neighbours_of_all_live() {
        World world = new WorldBuilder().withDimentions(5, 1).allLive().build();
        boolean[][] cells = world.getCells();
        assertThat(world.countLiveNeighboursOf(0,0), is(1));
        assertThat(world.countLiveNeighboursOf(1, 0), is(2));
        assertThat(world.countLiveNeighboursOf(2, 0), is(2));
        assertThat(world.countLiveNeighboursOf(3, 0), is(2));
        assertThat(world.countLiveNeighboursOf(4, 0), is(1));
    }

    @Test public void
    should_correctly_count_live_neighbours_some_live_some_not() {
        World world = new WorldBuilder().withDimentions(3, 3).allDead()
                .withLiveCellAt(0,0)
                .withLiveCellAt(2, 0)
                .withLiveCellAt(0, 2)
                .withLiveCellAt(2, 2)
                .withLiveCellAt(1, 1)
                .build();
        boolean[][] cells = world.getCells();
        assertThat(world.countLiveNeighboursOf(0,0), is(1));
        assertThat(world.countLiveNeighboursOf(1, 0), is(3));
        assertThat(world.countLiveNeighboursOf(2, 0), is(1));
        assertThat(world.countLiveNeighboursOf(0, 1), is(3));
        assertThat(world.countLiveNeighboursOf(1, 1), is(4));
        assertThat(world.countLiveNeighboursOf(2, 1), is(3));
        assertThat(world.countLiveNeighboursOf(0, 2), is(1));
        assertThat(world.countLiveNeighboursOf(1, 2), is(3));
        assertThat(world.countLiveNeighboursOf(2, 2), is(1));
    }
}
