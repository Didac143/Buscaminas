package buscamines;

public class Box {

    private int pos;
    private boolean isUnCovered;
    private boolean isMine;
    private int minesNeighbours;

    public Box(int pos) {
        isUnCovered = false;
        isMine = false;
        minesNeighbours = -1;
        this.pos = pos;
    }

    public Box(int pos, boolean isMine) {
        this(pos);
        this.isMine = isMine;
    }

    public boolean getIsUnCovered() {
        return isUnCovered;
    }

    public void setIsUnCovered(boolean isUnCovered) {
        this.isUnCovered = isUnCovered;
    }

    public boolean getIsMine() {
        return isMine;
    }

    public void setIsMine(boolean isMine) {
        this.isMine = isMine;
    }

    public int getMinesNeighbours() {
        return minesNeighbours;
    }

    public void setMinesNeighbours(int minesNeighbours) {
        this.minesNeighbours = minesNeighbours;
    }

    @Override
    public String toString() {
        return "pos=" + pos + ", isUnCovered=" + isUnCovered + ", isMine=" + isMine + ", minesNeighbours=" + minesNeighbours;
    }

}
