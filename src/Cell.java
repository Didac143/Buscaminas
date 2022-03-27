public class Cell {
    private int x;
    private int y;
    private int minesNearby;
    private boolean mine;
    private boolean flag;
    private boolean showing;

    public Cell(int y, int x) {
        this.x = x;
        this.y = y;
        this.minesNearby = 0;
        this.mine = false;
        this.flag = false;
        this.showing = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMinesNearby() {
        return minesNearby;
    }

    public void setMinesNearby(int minesNearby) {
        this.minesNearby = minesNearby;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isShowing() {
        return showing;
    }

    public void setShowing(boolean showing) {
        this.showing = showing;
    }

    @Override
    public String toString() {
        return mine ? "[#]" : "[" + String.valueOf(minesNearby) + "]";
    }
}
