package world;

import visualization.Visualization;

public class World {

    static final boolean DEAD = false;
    static final boolean LIVE = true;
    private boolean[][] cells;

    public World(boolean[][] cells) {
        this.cells = cells;
    }

    public String print(Visualization visualization) {
        return visualization.print();
    }

    World allDead() {
        return this;
    }

    public boolean equals(Object o) {
        return (o instanceof World) && ((World)o).cells == this.cells;
    }

    int getHeight() {
        return getWidth() <= 0 ? 0 : cells[0].length;
    }

    int getWidth() {
        return cells == null ? 0 : cells.length;
    }

    void markAsDead(int i, int j) {
        cells[i][j] = World.DEAD;
    }

    void setCells(boolean[][] cells) {
        this.cells = cells;
    }

    boolean[][] getCells() {
        return cells;
    }
}
