package buscamines.impl;

import buscamines.Box;
import buscamines.BuscaminesContract;
import buscamines.BuscaminesContract.BuscaminesModelListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BuscaminesModelImpl implements BuscaminesContract.BuscaminesModel {

    public enum Dificult {
        EASY, MEDIUM, HARD;
    }

    private Set<BuscaminesModelListener> listeners = new HashSet<>();
    private Box[][] grid;
    private List<Integer> posMines = new ArrayList<>();
    private Map<Dificult, Integer> dificultPercents = new HashMap<Dificult, Integer>() {
            {
                put(Dificult.EASY, 10);
                put(Dificult.MEDIUM, 15);
                put(Dificult.HARD, 20);
            }
        };
    private Map<Integer, Integer> posToUncover = new HashMap<>();
    private int remaining;
    private int size;
    private Dificult difficulty;
    private int pos; //position to discover

    /**
     * 
     * @param size
     * @param d 
     * 
     * es demana reinicia el joc amb la configuració rebuda
     */
    
    @Override
    public void start(int size, Dificult d) {


        this.size = size;
        this. difficulty = d;


        System.out.println(size + " - " + d);
        this.size = size;
        this.difficulty = d; 
        this.grid = new Box[size][size];
        initGrid();
        setMines();
        calcNearbyMines();
    }

    private void initGrid() {
        fillGridLayout();
        setMines();
        calcNearbyMines();
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
        this.remaining = (int) Math.pow(size, 2) - totalMines;
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

/**
 * 
 * @param pos 
 * es demana realitzar la jugada de descobrir la casella amb la posició passada per paràmetre.
 */    
    
    @Override
    public void play(int pos) {
        this.pos = pos;
        this.posToUncover.clear();
        System.out.println(pos + " - " + size);
        System.out.println(String.format("[%d][%d] -> %d ", getRow(), getCol(), pos));
        if (!grid[getCol()][getRow()].getIsMine()) {
            unCoverCells(getCol(), getRow());

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
        remaining--;
        
        if (current.getMinesNeighbours() > 0) {
            return;
        }

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
        System.out.println(listeners);
        return listeners.add(listener);
    }

    @Override
    public boolean removeListener(BuscaminesContract.BuscaminesModelListener listener) {
        return listeners.remove(listener);
    }

    /**
     * 
     * @return 
     * es demana les caselles a descobrir. Torna un Map amb
       key=posició de la casella a descobrir i value=quantitat de caselles al 
       seu voltant amb mina.

     */
    
    @Override
    public Map<Integer, Integer> toUnCovered() {

        Map posUncover = null;
        
        

        return posToUncover;
    }

    /**
     * 
     * @return 
     * es demana la quantitat de caselles que falten per descobrir (per si es vol 
        mostrar a la vista).
     */
    
    @Override
    public int getRemaining() {
        return remaining;
    }

    /**
     * 
     * @return 
     * es demana el total de mines (per si es vol mostrar a la vista)
     */
    
    @Override
    public int getTotalMines() {
        return posMines.size();
    }
/**
 * 
 * @return 
 * el presentador consulta si el joc ha finalitzat.
 */
    @Override
    public boolean isOver() {
        return posMines.contains(pos) || remaining == 0;
    }

    @Override
    public Set<Integer> getSizes() {
        return new HashSet<>(Arrays.asList(10, 15, 20));
    }

    private int getRow() {
        System.out.println(pos + " - " + size);
        return pos / size;
    }

    private int getCol() {
        return pos % size;
    }

}
