package buscamines;

import buscamines.BuscaminesContract.BuscaminesModel;
import buscamines.BuscaminesContract.BuscaminesPresenter;
import buscamines.BuscaminesContract.BuscaminesView;
import buscamines.impl.BuscaminesModelImpl;
import buscamines.impl.BuscaminesPresenterImpl;
import buscamines.impl.BuscaminesViewImpl;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author usuari
 */
public class BuscaminesApp extends Application {

    @Override
    public void start(Stage stage) {
        BuscaminesModel m = new BuscaminesModelImpl();
        BuscaminesPresenter p = new BuscaminesPresenterImpl(m);
        BuscaminesView v = new BuscaminesViewImpl(stage, p);
        p.setView(v);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
