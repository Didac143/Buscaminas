package buscamines;

import buscamines.BuscaminesContract.BuscaminesModel;
import buscamines.BuscaminesContract.BuscaminesPresenter;
import buscamines.BuscaminesContract.BuscaminesView;
import buscamines.impl.BuscaminesModelImpl;
import buscamines.impl.BuscaminesPresenterImpl;
import buscamines.impl.BuscaminesViewImpl;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.RadioMenuItemBuilder;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author usuari
 */
public class BuscaminesApp extends Application {

    @Override
    public void start(Stage stage) {
        BuscaminesModel m = new BuscaminesModelImpl();
        BuscaminesPresenter p = new BuscaminesPresenterImpl();
        BuscaminesView v = new BuscaminesViewImpl(stage);
        
        p.setModel(m);
        p.setView(v);
        v.setPresenter(p);
    }

   

    public static void main(String[] args) {
        launch(args);
    }

}
