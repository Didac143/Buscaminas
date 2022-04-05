package buscamines.impl;

import buscamines.Box;
import buscamines.BuscaminesContract;
import buscamines.BuscaminesContract.BuscaminesModelListener;
import buscamines.BuscaminesContract.BuscaminesPresenter;
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
    private int size;
    private Dificult dificult;
    private int pos; //position to discover
    private BuscaminesPresenter presentador;

    /**
     * 
     * @param size
     * @param d 
     * 
     * es demana reinicia el joc amb la configuració rebuda
     */
    
    @Override
    public void start(int size, Dificult d) {
        presentador.toRestart(size, d);
    }

/**
 * 
 * @param pos 
 * es demana realitzar la jugada de descobrir la casella amb la posició passada per paràmetre.
 */    
    
    @Override
    public void play(int pos) {
        throw new RuntimeException("no implementat!");
    }

    @Override
    public void setPresenter(BuscaminesPresenter p) {
        this.presentador = p;
    }

    
  
    @Override
    public boolean addListener(BuscaminesModelListener listener) {
        throw new RuntimeException("no implementat!");
    }

    @Override
    public boolean removeListener(BuscaminesContract.BuscaminesModelListener listener) {
        throw new RuntimeException("no implementat!");
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
        
        
        return posUncover;
    }

    /**
     * 
     * @return 
     * es demana la quantitat de caselles que falten per descobrir (per si es vol 
        mostrar a la vista).
     */
    
    @Override
    public int getRemaining() {
        throw new RuntimeException("no implementat!");
    }

    /**
     * 
     * @return 
     * es demana el total de mines (per si es vol mostrar a la vista)
     */
    
    @Override
    public int getTotalMines() {
        throw new RuntimeException("no implementat!");
    }
/**
 * 
 * @return 
 * el presentador consulta si el joc ha finalitzat.
 */
    @Override
    public boolean isOver() {
        throw new RuntimeException("no implementat!");
    }

    @Override
    public Set<Integer> getSizes() {
        throw new RuntimeException("no implementat!");
    }

    private int getFile() {
        return pos / size;
    }

    private int getCol() {
        return pos % size;
    }

}
