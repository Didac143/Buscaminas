package buscamines;

import buscamines.BuscaminesContract.BuscaminesModel;
import buscamines.BuscaminesContract.BuscaminesPresenter;
import buscamines.BuscaminesContract.BuscaminesView;
import buscamines.impl.BuscaminesModelImpl;
import buscamines.impl.BuscaminesPresenterImpl;
import buscamines.impl.BuscaminesViewImpl;
import java.io.IOException;
import javafx.application.Application;
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
        stage.setTitle("Buscaminas by Ara");
        Scene scene = new Scene(new VBox(), 400, 400);
        scene.setFill(Color.OLDLACE);

        MenuBar menuBar = new MenuBar();

        Menu file = new Menu("File");
        Menu size = new Menu("Size");
        Menu difficulty = new Menu("Difficulty");
        Menu sound = new Menu("Sound");
        Menu help = new Menu("Help");

        menuBar.getMenus().addAll(file, size, difficulty, sound, help);

        MenuItem FExit = new MenuItem("Exit");

        file.getItems().addAll(FExit);

        ToggleGroup GSize = new ToggleGroup();

        RadioMenuItem SDiez = RadioMenuItemBuilder.create().toggleGroup(GSize).selected(true).text("10x10").build();
        RadioMenuItem SQuince = RadioMenuItemBuilder.create().toggleGroup(GSize).text("15x15").build();
        RadioMenuItem SVeinte = RadioMenuItemBuilder.create().toggleGroup(GSize).text("20x20").build();

        size.getItems().addAll(SDiez, SQuince, SVeinte);

        ToggleGroup GDifi = new ToggleGroup();

        RadioMenuItem DFacil = RadioMenuItemBuilder.create().toggleGroup(GDifi).selected(true).text("Facil").build();
        RadioMenuItem DMedio = RadioMenuItemBuilder.create().toggleGroup(GDifi).text("Medio").build();
        RadioMenuItem DDificil = RadioMenuItemBuilder.create().toggleGroup(GDifi).text("Dificil").build();

        difficulty.getItems().addAll(DFacil, DMedio, DDificil);

        ToggleGroup GSonido = new ToggleGroup();

        RadioMenuItem SSonido = RadioMenuItemBuilder.create().toggleGroup(GSonido).selected(true).text("On").build();
        RadioMenuItem SOff = RadioMenuItemBuilder.create().toggleGroup(GSonido).text("Off").build();

        sound.getItems().addAll(SSonido, SOff);

        MenuItem HComoJugar = new MenuItem("Como jugar");
        MenuItem HAutores = new MenuItem("Autores");

        help.getItems().addAll(HAutores, HComoJugar);

        GridPane gridpane = new GridPane();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gridpane.add(new Button("  "), i, j);
            }

        }

        ((VBox) scene.getRoot()).getChildren().addAll(menuBar, gridpane);

        stage.setScene(scene);
        stage.show();
    }

    public static void diez() {
        GridPane gridpane = new GridPane();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gridpane.add(new Button(""), i, j);
            }

        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
