package buscamines.impl;
import buscamines.Box;
import buscamines.BuscaminesContract;
import buscamines.BuscaminesContract.BuscaminesModelListener;
import buscamines.BuscaminesContract.BuscaminesPresenter;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BuscaminesModelImpl implements BuscaminesContract.BuscaminesModel {

    private Set<BuscaminesModelListener> listeners;
    private Box[][] grid;    public enum Dificult {
        EASY, MEDIUM, HARD;    }
    private List<Integer> posMines;
    private Map<Dificult, Integer> dificultPercents;
    private int size;
    private Dificult dificult;
    private int pos; //position to discover

    @Override
    public void start(int size, Dificult d) {
        throw new RuntimeException("no implementat!");
    }

    @Override
    public void play(int pos) {
        throw new RuntimeException("no implementat!");
    }

    @Override
    public void setPresenter(BuscaminesPresenter p) {
        throw new RuntimeException("no implementat!");
    }

    @Override
    public boolean addListener(BuscaminesModelListener listener) {
        throw new RuntimeException("no implementat!");
    }

    @Override
    public boolean removeListener(BuscaminesContract.BuscaminesModelListener listener) {
        throw new RuntimeException("no implementat!");
    }

    @Override
    public Map<Integer, Integer> toUnCovered() {
        throw new RuntimeException("no implementat!");
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
    
    private int getFile(){
        return pos/size;
    }
    private int getCol(){
        return pos%size;
    }


}
