import java.util.Arrays;

public class Game extends Grid {
    private Difficulty difficulty;
    private boolean sound;
    private int totalMines;

    public Game(Difficulty difficulty, boolean sound, int GRID_SIZE) {
        super(GRID_SIZE);
        this.difficulty = difficulty;
        this.sound = sound;
        this.totalMines = 0;
        fillGridLayout();
        setMines();
        calcNearbyMines();
    }

    private void calcNearbyMines() {
        int totalMines = 0;

        for (int y = 0; y < getGRID_LAYOUT().length; y++) {
            for (int x = 0; x < getGRID_LAYOUT()[y].length; x++) {
                if (!getGRID_LAYOUT()[y][x].isMine()) {
                    for (int y2 = -1; y2 <= 1; y2++) {
                        for (int x2 = -1; x2 <= 1; x2++) {
                            if (y + y2 < 0 || y + y2 >= getGRID_SIZE() || x + x2 < 0 || x + x2 >= getGRID_SIZE())
                                continue;
                            if (getGRID_LAYOUT()[y + y2][x + x2].isMine()) totalMines++;
                        }
                    }
                }
                getGRID_LAYOUT()[y][x].setMinesNearby(totalMines);
                totalMines = 0;
            }
        }
    }

    private void fillGridLayout() {
        for (int y = 0; y < getGRID_LAYOUT().length; y++) {
            for (int x = 0; x < getGRID_LAYOUT()[y].length; x++) {
                getGRID_LAYOUT()[y][x] = new Cell(y, x);
            }
        }
    }

    private void setMines() {
        int totalMines = (int) ((Math.pow(getGRID_SIZE(), 2) * difficulty.get()) / 100);
        while (this.totalMines < totalMines) {
            int y = (int) (Math.random() * getGRID_SIZE()),
                    x = (int) (Math.random() * getGRID_SIZE());
            if (!getGRID_LAYOUT()[y][x].isMine()) {
                getGRID_LAYOUT()[y][x].setMine(true);
                incrementTotalMines();
            }
        }
    }

    public void printLayout() {
        System.out.println("  0  1  2  3  4  5  6  7  8  9");
        for (int y = 0; y < getGRID_LAYOUT().length; y++) {
            System.out.print(y);
            for (int x = 0; x < getGRID_LAYOUT()[y].length; x++) {
                Cell cell = getGRID_LAYOUT()[y][x];
                System.out.print(cell.isShowing() ? "[" + cell.getMinesNearby() + "]" : "[ ]");
            }
            System.out.println();
        }
//        for (Cell[] cells : getGRID_LAYOUT()) {
//            for (Cell cell : cells) {
//                System.out.print(cell.isShowing() ? cell.getMinesNearby() : "[ ]");
//            }
//            System.out.println();
//        }
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isSound() {
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public int getTotalMines() {
        return totalMines;
    }

    public void incrementTotalMines() {
        this.totalMines++;
    }
}
