package world;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class WorldBuilderTest {

    @Test public void
    should_return_empty_plane() {
        assertThat(new WorldBuilder().aPlane().build(), equalTo(new World(null)));
    }

    @Test public void
    should_return_world_with_correct_sizes() {
        World newWorld = new WorldBuilder().aPlane().withDimentions(10, 5).build();
        assertThat(newWorld.getHeight(), is(5));
        assertThat(newWorld.getWidth(), is(10));
    }

    @Test public void
    should_return_dead_world() {
        World newWorld = new WorldBuilder().aPlane().withDimentions(9, 4).allDead().build();
        boolean[][] cells = newWorld.getCells();
        for (int i = 0; i < newWorld.getWidth(); ++i)
            for (int j = 0; j < newWorld.getHeight(); ++j)
                assertThat(cells[i][j], is(World.DEAD));
    }
}
