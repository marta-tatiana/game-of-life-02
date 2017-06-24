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
}
