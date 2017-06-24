package world;

public class WorldBuilder {

    private World world;

    public WorldBuilder aPlane() {
        this.world = new World(null);
        return this;
    }

    public WorldBuilder withSize(int i) {
        return this;
    }

    public WorldBuilder allDead() {
        for (int i = 0; i < world.getHeight(); ++i)
            for (int j = 0; j < world.getWidth(); ++j)
                world.markAsDead(j, i);

        return this;
    }

    public World build() {
        return world;
    }

    WorldBuilder withDimentions(int width, int height) {
        boolean[][] cells = new boolean[width][height];
        world.setCells(cells);
        return this;
    }
}
