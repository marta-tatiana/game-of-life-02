package world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

class ImmutablePair {
    final int first;
    final int second;

    public ImmutablePair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

public class World {

    public static final boolean DEAD = false;
    public static final boolean LIVE = true;
    private boolean[][] cells;

    public World(boolean[][] cells) {
        this.cells = cells;
    }

    public boolean equals(Object o) {
        if  (!(o instanceof World))
            return false;

        for (int i = 0; i < getWidth(); ++i)
            for (int j = 0; j < getHeight(); ++j) {
                if (!(cells[i][j] == ((World) o).cells[i][j]))
                    return false;
            }
        return true;
    }

    public int getHeight() {
        return getWidth() <= 0 ? 0 : cells[0].length;
    }

    public int getWidth() {
        return cells == null ? 0 : cells.length;
    }

    void markAsDead(int i, int j) {
        cells[i][j] = World.DEAD;
    }

    public void setCells(boolean[][] cells) {
        this.cells = cells;
    }

    public boolean[][] getCells() {
        return cells;
    }

    public void markAsLive(int i, int j) {
        cells[i][j] = World.LIVE;
    }

    public int countLiveNeighboursOf(int i, int j) {
       List<ImmutablePair> indexesToCheck = createListOfNeighbouringIndexes(i, j);
       List<ImmutablePair> validIndexes = filterInvalidIndexes(indexesToCheck);
       ImmutablePair acc = new ImmutablePair(0,0);
       return validIndexes.stream().reduce(acc, (counter, pair) ->
                 cells[pair.first][pair.second] == LIVE ?
                 new ImmutablePair(counter.first+1, 0) : counter)
             .first;
    }

    private List<ImmutablePair> createListOfNeighbouringIndexes(int i, int j) {
        List<ImmutablePair> indexes = new ArrayList<>();
        indexes.add(new ImmutablePair(i-1, j+1));
        indexes.add(new ImmutablePair(i-1, j));
        indexes.add(new ImmutablePair(i-1, j-1));
        indexes.add(new ImmutablePair(i, j+1));
        indexes.add(new ImmutablePair(i, j-1));
        indexes.add(new ImmutablePair(i+1, j+1));
        indexes.add(new ImmutablePair(i+1, j));
        indexes.add(new ImmutablePair(i+1, j-1));
        return indexes;
    }


    private List<ImmutablePair> filterInvalidIndexes(List<ImmutablePair> indexesToCheck) {
        return indexesToCheck.stream().filter(pair ->
                pair.first >= 0
                && pair.second >= 0
                && pair.first < getWidth()
                && pair.second < getHeight()).collect(Collectors.toList());

    }
}
