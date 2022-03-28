package buscamines.impl;

import buscamines.BuscaminesContract;
import buscamines.BuscaminesContract.BuscaminesModel;
import buscamines.BuscaminesContract.BuscaminesPresenter;
import buscamines.BuscaminesContract.BuscaminesView;
import buscamines.impl.BuscaminesModelImpl.Dificult;
import java.util.Set;

public class BuscaminesPresenterImpl implements BuscaminesPresenter {
    private BuscaminesModel model;
    private BuscaminesView vista;


    @Override
    public void setModel(BuscaminesContract.BuscaminesModel m) {
        this.model = m;
    }

    @Override
    public void setView(BuscaminesContract.BuscaminesView v) {
        this.vista = v;
    }

    @Override
    public void toUncover(int pos) {
        model.play(pos);
        
    }


    @Override
    public Set<Integer> configSizes() {
         throw new RuntimeException("no implementat!");
    }

    @Override
    public void toRestart(int size, Dificult d) {
         model.start(size, d);
    }

}
