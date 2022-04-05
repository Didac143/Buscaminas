package buscamines.impl;

import buscamines.BuscaminesContract;
import buscamines.BuscaminesContract.BuscaminesPresenter;
import buscamines.BuscaminesContract.BuscaminesView;
import buscamines.impl.BuscaminesModelImpl.Dificult;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.RadioMenuItemBuilder;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
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
    private ToggleGroup sizeGroup, dificultyGroup;
    private GridPane gridPane;

    public BuscaminesViewImpl(Stage stage) {
        gridPane = new GridPane();
        buttons = new ArrayList<>();
        initUI(stage);
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
        
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        stage.setMaxHeight((screen.getHeight() * 98) / 100);
        stage.setMaxWidth((screen.getWidth() * 50) / 100);

        MenuBar menuBar = new MenuBar();

        Menu file = new Menu("File");
        Menu size = new Menu("Size");
        Menu difficulty = new Menu("Difficulty");
        Menu sound = new Menu("Sound");
        Menu help = new Menu("Help");

        menuBar.getMenus().addAll(file, size, difficulty, sound, help);

        MenuItem FExit = new MenuItem("Exit");

        file.getItems().addAll(FExit);

        this.sizeGroup = new ToggleGroup();

        RadioMenuItem sDiez = RadioMenuItemBuilder.create().toggleGroup(sizeGroup).selected(true).text("10x10").build();
        RadioMenuItem sQuince = RadioMenuItemBuilder.create().toggleGroup(sizeGroup).text("15x15").build();
        RadioMenuItem sVeinte = RadioMenuItemBuilder.create().toggleGroup(sizeGroup).text("20x20").build();

        size.getItems().addAll(sDiez, sQuince, sVeinte);

        this.dificultyGroup = new ToggleGroup();

        RadioMenuItem DFacil = RadioMenuItemBuilder.create().toggleGroup(dificultyGroup).selected(true).text("Facil").build();
        RadioMenuItem DMedio = RadioMenuItemBuilder.create().toggleGroup(dificultyGroup).text("Medio").build();
        RadioMenuItem DDificil = RadioMenuItemBuilder.create().toggleGroup(dificultyGroup).text("Dificil").build();

        difficulty.getItems().addAll(DFacil, DMedio, DDificil);

        EventHandler eventHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                initGame();
            }
        };

        sDiez.addEventHandler(EventType.ROOT, eventHandler);
        sQuince.addEventHandler(EventType.ROOT, eventHandler);
        sVeinte.addEventHandler(EventType.ROOT, eventHandler);
        DFacil.addEventHandler(EventType.ROOT, eventHandler);
        DMedio.addEventHandler(EventType.ROOT, eventHandler);
        DDificil.addEventHandler(EventType.ROOT, eventHandler);

        ToggleGroup soundGroup = new ToggleGroup();

        RadioMenuItem SSonido = RadioMenuItemBuilder.create().toggleGroup(soundGroup).selected(true).text("On").build();
        RadioMenuItem SOff = RadioMenuItemBuilder.create().toggleGroup(soundGroup).text("Off").build();

        sound.getItems().addAll(SSonido, SOff);

        MenuItem HComoJugar = new MenuItem("Como jugar");
        MenuItem HAutores = new MenuItem("Autores");

        help.getItems().addAll(HAutores, HComoJugar);

        initGame();

        ((VBox) scene.getRoot()).getChildren().addAll(menuBar, gridPane);
        stage.setScene(scene);
        stage.show();
    }

    public void initGame() {
        int size = Integer.valueOf(((RadioMenuItem) sizeGroup.getSelectedToggle()).getText().split("x")[0]);
        Dificult difficulty = null;
        switch (((RadioMenuItem) dificultyGroup.getSelectedToggle()).getText().toLowerCase()) {
            case "facil":
                difficulty = Dificult.EASY;
                break;
            case "medio":
                difficulty = Dificult.MEDIUM;
                break;
            case "dificil":
                difficulty = Dificult.HARD;
                break;
        }

        List<Node> nodes = gridPane.getChildren();
        nodes.removeAll(nodes);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gridPane.add(new MyButton(i * size + j), j, i);
            }
        }

        ColumnConstraints cc = new ColumnConstraints(20, 150, 100);
        cc.setHgrow(Priority.ALWAYS);

        RowConstraints rc = new RowConstraints(20, 150, 100);
        rc.setVgrow(Priority.ALWAYS);

        List<ColumnConstraints> columnConstraintses = gridPane.getColumnConstraints();
        List<RowConstraints> rowConstraintses = gridPane.getRowConstraints();

        columnConstraintses.removeAll(columnConstraintses);
        rowConstraintses.removeAll(rowConstraintses);
        for (int i = 0; i < size; i++) {
            columnConstraintses.add(cc);
            rowConstraintses.add(rc);
        }

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
            initButton();
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        private void initButton() {
            this.setMaxWidth(Double.POSITIVE_INFINITY);
            this.setMaxHeight(Double.POSITIVE_INFINITY);
        }

    }

}
