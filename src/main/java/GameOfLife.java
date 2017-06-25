import visualization.Visualization;
import world.World;

class GameOfLife {

    private Visualization visualization;
    private World world;

    GameOfLife(World startingWorld, Visualization visualization) {
        this.visualization = visualization;
        this.world = startingWorld;
    }

    GameOfLife next() {
        boolean[][] oldCells = world.getCells();
        boolean[][] newCells = new boolean[world.getWidth()][world.getHeight()];
        for (int i = 0; i < world.getWidth(); ++i) {
            for (int j = 0; j < world.getHeight(); ++j) {
                int numOfLiveNeighbours = world.countLiveNeighboursOf(i, j);
                if (oldCells[i][j] == World.LIVE) {
                    if (numOfLiveNeighbours < 2 || numOfLiveNeighbours > 3) {
                        newCells[i][j] = World.DEAD;
                    } else {
                        newCells[i][j] = World.LIVE;
                    }
                } else {
                    if (numOfLiveNeighbours == 3) {
                        newCells[i][j] = World.LIVE;
                    } else {
                        newCells[i][j] = World.DEAD;
                    }

                }
            }
        }
        this.world.setCells(newCells);
        return this;
    }

    String print() {
        return visualization.print(world);
    }
}
