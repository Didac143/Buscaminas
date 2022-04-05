package buscamines.impl;

import buscamines.Box;
import buscamines.BuscaminesContract;
import buscamines.BuscaminesContract.BuscaminesModelListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BuscaminesModelImpl implements BuscaminesContract.BuscaminesModel {

    public enum Dificult {
        EASY, MEDIUM, HARD;
    }

    private Set<BuscaminesModelListener> listeners;
    private Box[][] grid;
    private List<Integer> posMines;
    private Map<Dificult, Integer> dificultPercents;
    private Map<Integer, Integer> posToUncover;
    private int remaining;
    private int size;
    private Dificult difficulty;
    private int pos; //position to discover

    @Override
    public void start(int size, Dificult d) {
        this.size = size;
        this.difficulty = d;
        this.grid = new Box[size][size];
        this.posMines = new ArrayList<>();
        this.posToUncover = new HashMap<>();
        this.remaining = 0; // TODO
        initGrid();
        setMines();
        calcNearbyMines();
    }

    private void initGrid() {
        fillGridLayout();
    }

    private void fillGridLayout() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                grid[y][x] = new Box(x * size + y);
            }
        }
    }

    private void setMines() {
        int totalMines = (int) ((Math.pow(size, 2) * dificultPercents.get(difficulty)) / 100);
        while (getTotalMines() < totalMines) {
            int y = (int) (Math.random() * size),
                    x = (int) (Math.random() * size);
            if (!grid[y][x].getIsMine()) {
                grid[y][x].setMine(true);
                posMines.add(x * size + y);
            }
        }
    }

    private void calcNearbyMines() {
        int totalMines = 0;

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (!grid[y][x].getIsMine()) {
                    for (int y2 = -1; y2 <= 1; y2++) {
                        for (int x2 = -1; x2 <= 1; x2++) {
                            if (y + y2 < 0 || y + y2 >= size || x + x2 < 0 || x + x2 >= size) {
                                continue;
                            }
                            if (grid[y + y2][x + x2].getIsMine()) {
                                totalMines++;
                            }
                        }
                    }
                }
                grid[y][x].setMinesNeighbours(totalMines);
                totalMines = 0;
            }
        }
    }

    @Override
    public void play(int pos) {
        this.pos = pos;
        if (!grid[getRow()][getCol()].getIsMine()) {
            unCoverCells(getRow(), getCol());
            
        } else {
            listeners.forEach(l -> l.overEvent(posMines));
        }
        
    }

    private void unCoverCells(int y, int x) {
        Box current = grid[y][x];

        if (current.getIsUnCovered()) {
            return;
        }

        current.setIsUnCovered(true);
        posToUncover.put(current.getPos(), current.getMinesNeighbours());

        for (int y2 = -1; y2 <= 1; y2++) {
            for (int x2 = -1; x2 <= 1; x2++) {
                if (y + y2 < 0 || y + y2 >= size || x + x2 < 0 || x + x2 >= size) {
                    continue;
                }
                unCoverCells(y + y2, x + x2);
            }
        }

    }

    @Override
    public boolean addListener(BuscaminesModelListener listener) {
        return listeners.add(listener);
    }

    @Override
    public boolean removeListener(BuscaminesContract.BuscaminesModelListener listener) {
        return listeners.remove(listener);
    }

    @Override
    public Map<Integer, Integer> toUnCovered() {
        return posToUncover;
    }

    @Override
    public int getRemaining() {
        throw new RuntimeException("no implementat!");
    }

    @Override
    public int getTotalMines() {
        throw new RuntimeException("no implementat!");
    }

    @Override
    public boolean isOver() {
        throw new RuntimeException("no implementat!");
    }

    @Override
    public Set<Integer> getSizes() {
        throw new RuntimeException("no implementat!");
    }

    private int getRow() {
        return pos / size;
    }

    private int getCol() {
        return pos % size;
    }

}
