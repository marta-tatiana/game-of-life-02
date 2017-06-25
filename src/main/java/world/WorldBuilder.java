package world;

public class WorldBuilder {

    private World world;

    public WorldBuilder allDead() {
        for (int i = 0; i < world.getHeight(); ++i)
            for (int j = 0; j < world.getWidth(); ++j)
                world.markAsDead(j, i);

        return this;
    }

    public World build() {
        return world;
    }

    public WorldBuilder withDimentions(int width, int height) {
        this.world = new World(new boolean[width][height]);
        return this;
    }

    public WorldBuilder withLiveCellAt(int i, int j) {
        world.markAsLive(i, j);
        return this;
    }

    public WorldBuilder allLive() {
        for (int i = 0; i < world.getWidth(); ++i)
            for (int j = 0; j < world.getHeight(); ++j)
                world.markAsLive(i, j);

        return this;
    }
}
