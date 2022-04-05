package buscamines;

import buscamines.BuscaminesContract.BuscaminesModel;
import buscamines.impl.BuscaminesModelImpl.Dificult;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author usuari
 */
public interface BuscaminesContract {

    interface BuscaminesGame {

        // methods that can be called by presenter
        void start(int size, Dificult d);

        Map<Integer, Integer> toUnCovered();

        Set<Integer> getSizes();

        int getRemaining();

        int getTotalMines();

        boolean isOver();

        void play(int pos);

    }

    //the model contract     
    interface BuscaminesModel extends BuscaminesGame {

        boolean addListener(BuscaminesModelListener listener);

        boolean removeListener(BuscaminesModelListener listener);
    }

    //the presenter contract     
    interface BuscaminesPresenter {

        void setModel(BuscaminesModel m);

        void setView(BuscaminesView v);

        // methods called by view 
        void toUncover(int pos);

        Set<Integer> configSizes();

        void toRestart(int size, Dificult d);

    }

    //the view contract     
    interface BuscaminesView {

        void setPresenter(BuscaminesPresenter p);

        // methods called by presenter
        void UnCovered(Map<Integer, Integer> boxesToUnCovered);

        void overGame(List<Integer> posMines);

        void win();
    }

    interface BuscaminesModelListener {
        //when the game is over the listeners are notified. 

        void overEvent(List<Integer> posMines);

        void winEvent();
    }

}
