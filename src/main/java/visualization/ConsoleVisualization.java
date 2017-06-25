package visualization;

import world.World;

public class ConsoleVisualization implements Visualization {
    @Override
    public String print(World world) {
        boolean[][] cells = world.getCells();
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < world.getHeight(); ++i) {
            for (int j = 0; j < world.getWidth(); ++j) {
                result.append(cells[j][i] == World.DEAD ? ". " : "* ");
            }
            result.append("\n");
        }
        return result.toString().replace(" \n", "\n");
    }
}
