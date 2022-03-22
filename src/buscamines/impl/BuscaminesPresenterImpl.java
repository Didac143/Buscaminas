package buscamines.impl;

import buscamines.BuscaminesContract;
import buscamines.BuscaminesContract.BuscaminesPresenter;
import buscamines.impl.BuscaminesModelImpl.Dificult;
import java.util.Set;

public class BuscaminesPresenterImpl implements BuscaminesPresenter {

    @Override
    public void setModel(BuscaminesContract.BuscaminesModel m) {
        throw new RuntimeException("no implementat!");
    }

    @Override
    public void setView(BuscaminesContract.BuscaminesView v) {
        throw new RuntimeException("no implementat!");
    }

    @Override
    public void toUncover(int pos) {
        throw new RuntimeException("no implementat!");
    }


    @Override
    public Set<Integer> configSizes() {
         throw new RuntimeException("no implementat!");
    }

    @Override
    public void toRestart(int size, Dificult d) {
         throw new RuntimeException("no implementat!");
    }

}
