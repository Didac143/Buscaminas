package buscamines.impl;

import buscamines.BuscaminesContract;
import buscamines.BuscaminesContract.BuscaminesPresenter;
import buscamines.BuscaminesContract.BuscaminesView;
import java.awt.Button;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
public class BuscaminesViewImpl implements Initializable, BuscaminesView {

    private List<MyButton> buttons;
    private BuscaminesPresenter presentador;

    public BuscaminesViewImpl(Stage stage) {
        initUI(stage);
        buttons = new ArrayList<MyButton>();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    // UI
    private void initUI(Stage stage) {
//todo lo grafico que esta en el main va aqui 
        stage.setTitle("Buscaminas");
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
        GridPane gridpane = new GridPane();

        help.getItems().addAll(HAutores, HComoJugar);
        SDiez.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        gridpane.add(new javafx.scene.control.Button("  "), i, j);
                    }

                }
            }

        });
        SQuince.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                for (int i = 0; i < 15; i++) {
                    for (int j = 0; j < 15; j++) {
                        gridpane.add(new javafx.scene.control.Button("  "), i, j);
                    }

                }
            }

        });
        SVeinte.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                for (int i = 0; i < 20; i++) {
                    for (int j = 0; j < 20; j++) {
                        gridpane.add(new javafx.scene.control.Button("  "), i, j);
                    }

                }
            }

        });
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar, gridpane);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void setPresenter(BuscaminesContract.BuscaminesPresenter p) {
       this.presentador = p;
    }

    @Override
    public void UnCovered(Map<Integer, Integer> boxesDescovered) {
        throw new RuntimeException("no implementat!");
    }

    @Override
    public void overGame(List<Integer> posMines) {
        throw new RuntimeException("no implementat!");
    }

    @Override
    public void win() {
        throw new RuntimeException("no implementat!");
    }

    class MyButton extends Button {

        private boolean flag;
        private int pos;

        public int getPos() {
            return pos;
        }

        public MyButton(int pos) {
            flag = false;
            this.pos = pos;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

    }

}
