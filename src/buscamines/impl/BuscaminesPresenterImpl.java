package buscamines.impl;

import buscamines.BuscaminesContract;
import buscamines.BuscaminesContract.BuscaminesModel;
import buscamines.BuscaminesContract.BuscaminesPresenter;
import buscamines.BuscaminesContract.BuscaminesView;
import buscamines.impl.BuscaminesModelImpl.Dificult;
import java.util.List;
import java.util.Set;

public class BuscaminesPresenterImpl implements BuscaminesPresenter, BuscaminesContract.BuscaminesModelListener {

    private BuscaminesModel model;
    private BuscaminesView vista;

    public BuscaminesPresenterImpl(BuscaminesModel model) {
        this.model = model;
        model.addListener(this);
    }

    @Override
    public void setModel(BuscaminesContract.BuscaminesModel m) {
        this.model = m;
        model.addListener(this);
    }

    @Override
    public void setView(BuscaminesContract.BuscaminesView v) {
        this.vista = v;
    }

    @Override
    public void toUncover(int pos) {
        model.play(pos);
        vista.UnCovered(model.toUnCovered());
    }

    @Override
    public Set<Integer> configSizes() {
        return model.getSizes();
    }

    @Override
    public void toRestart(int size, Dificult d) {
        model.start(size, d);
    }

    @Override
    public void overEvent(List<Integer> posMines) {
        vista.overGame(posMines);
    }

    @Override
    public void winEvent() {
        vista.win();
    }

    @Override
    public boolean isEnCurso() {
        return model.isEnCurso();
    }

}
