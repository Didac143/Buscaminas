public class Grid {
    private final int GRID_SIZE;
    private final Cell[][] GRID_LAYOUT;

    public Grid(int GRID_SIZE) {
        this.GRID_SIZE = GRID_SIZE;
        this.GRID_LAYOUT = new Cell[GRID_SIZE][GRID_SIZE];
    }

    public int getGRID_SIZE() {
        return GRID_SIZE;
    }

    public Cell[][] getGRID_LAYOUT() {
        return GRID_LAYOUT;
    }
}
