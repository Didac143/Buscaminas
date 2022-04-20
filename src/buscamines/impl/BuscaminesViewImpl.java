package buscamines.impl;

import buscamines.BuscaminesApp;
import buscamines.BuscaminesContract;
import buscamines.BuscaminesContract.BuscaminesModel;
import buscamines.BuscaminesContract.BuscaminesPresenter;
import buscamines.BuscaminesContract.BuscaminesView;
import buscamines.impl.BuscaminesModelImpl.Dificult;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.RadioMenuItemBuilder;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author usuari
 */
public class BuscaminesViewImpl implements Initializable, BuscaminesView {

    private List<MyButton> buttons;
    private BuscaminesPresenter presentador;
    private ToggleGroup sizeGroup, dificultyGroup;
    private String lastSizeSelected, lastDiffSelected;
    private GridPane gridPane;
    private Map<String, AudioClip> sounds;
    private boolean soundEnabled;

    public BuscaminesViewImpl(Stage stage, BuscaminesPresenter p) {
        lastSizeSelected = "10x10";
        lastDiffSelected = "facil";
        gridPane = new GridPane();
        buttons = new ArrayList<>();
        presentador = p;
        soundEnabled = true;
        sounds = new HashMap<String, AudioClip>() {
            {
                try {
                    put("click", (AudioClip) new File("src/buscamines/click.wav").toURL().getContent());
                    put("win", (AudioClip) new File("src/buscamines/win.wav").toURL().getContent());
                    put("boom", (AudioClip) new File("src/buscamines/explosionMine.wav").toURL().getContent());
                } catch (IOException ex) {
                    Logger.getLogger(BuscaminesViewImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
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

        stage.setMinHeight((screen.getHeight() * 60) / 100);
        stage.setMinWidth((screen.getHeight() * 60) / 100);

        stage.getIcons().add(new Image(this.getClass().getResource("/buscamines/mine.png").toString()));

        scene.getStylesheets().add("/buscamines/buttons.css");

        MenuBar menuBar = new MenuBar();

        Menu file = new Menu("File");
        Menu size = new Menu("Size");
        Menu difficulty = new Menu("Difficulty");
        Menu sound = new Menu("Sound");
        Menu help = new Menu("Help");

        menuBar.getMenus().addAll(file, size, difficulty, sound, help);

        MenuItem FExit = new MenuItem("Exit");

        FExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

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

        EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (presentador.isEnCurso()) {
                    onGameRestart();
                } else {
                    lastSizeSelected = ((RadioMenuItem) sizeGroup.getSelectedToggle()).getText().toLowerCase();
                    lastDiffSelected = ((RadioMenuItem) dificultyGroup.getSelectedToggle()).getText().toLowerCase();
                    initGame();
                }
            }
        };

        sDiez.setOnAction(eventHandler);
        sQuince.setOnAction(eventHandler);
        sVeinte.setOnAction(eventHandler);
        DFacil.setOnAction(eventHandler);
        DMedio.setOnAction(eventHandler);
        DDificil.setOnAction(eventHandler);

        ToggleGroup soundGroup = new ToggleGroup();

        RadioMenuItem SSonido = RadioMenuItemBuilder.create().toggleGroup(soundGroup).selected(true).text("On").build();
        RadioMenuItem SOff = RadioMenuItemBuilder.create().toggleGroup(soundGroup).text("Off").build();

        EventHandler<ActionEvent> eventHandlerSound = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                soundEnabled = ((RadioMenuItem) event.getSource()).getText().equals("On");
            }
        };

        SSonido.setOnAction(eventHandlerSound);
        SOff.setOnAction(eventHandlerSound);

        sound.getItems().addAll(SSonido, SOff);

        MenuItem HComoJugar = new MenuItem("Como jugar");
        HComoJugar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage comoJugar = new Stage();
                comoJugar.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox();

                dialogVbox.setSpacing(10);
                dialogVbox.setPadding(new Insets(20, 65, 20, 65));

                Label comoText = new Label("El objetivo es despejar todo el área \nsin detonar las minas.\nAl pulsar sobre las celdas, una zona \nse despeja y aparecen números \nque determinan la proximidad de\nlas minas. Por ejemplo, en las \nceldas contiguas a un [2] sólo habrá \ndos minas. ");

                comoText.setAlignment(Pos.CENTER);

                comoJugar.getIcons().add(new Image(this.getClass().getResource("/buscamines/mine.png").toString()));

                dialogVbox.getChildren().addAll(comoText);

                Scene dialogScene = new Scene(dialogVbox);

                comoJugar.setScene(dialogScene);
                comoJugar.show();

            }
        });

        MenuItem HAutores = new MenuItem("Autores");
        HAutores.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage autores = new Stage();
                autores.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox();

                dialogVbox.setSpacing(10);
                dialogVbox.setPadding(new Insets(20, 65, 20, 65));

                Label comoText = new Label("Didac Ortega y Arantxa Nevado");
                comoText.setAlignment(Pos.CENTER);

                autores.getIcons().add(new Image(this.getClass().getResource("/buscamines/mine.png").toString()));

                dialogVbox.getChildren().addAll(comoText);

                Scene dialogScene = new Scene(dialogVbox);

                autores.setScene(dialogScene);
                autores.show();

            }
        });

        help.getItems().addAll(HAutores, HComoJugar);

        initGame();

        sizeGroup.getToggles().stream()
                .filter(t -> ((RadioMenuItem) t).getText()
                .toLowerCase()
                .contains(lastSizeSelected))
                .findFirst()
                .get()
                .setSelected(true);

        dificultyGroup.getToggles().stream()
                .filter(t -> ((RadioMenuItem) t).getText()
                .toLowerCase()
                .contains(lastDiffSelected))
                .findFirst()
                .get()
                .setSelected(true);

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


        
        presentador.toRestart(size, difficulty);

    }

    @Override
    public void setPresenter(BuscaminesContract.BuscaminesPresenter p) {
        this.presentador = p;
    }

    @Override
    public void UnCovered(Map<Integer, Integer> boxesDescovered) {
        boxesDescovered.forEach((k, v) -> {
            MyButton boton = (MyButton) gridPane.getChildren().get(k);
            if (boton.isFlag()) {
                boton.setGraphic(null);
            }
            boton.setDisable(true);
            boton.setText(v > 0 ? String.valueOf(v) : "");
        });
    }

    @Override
    public void overGame(List<Integer> posMines) {
        if (soundEnabled) {
            sounds.get("boom").play();
        }
        posMines.forEach(p -> {
            ((MyButton) gridPane.getChildren().get(p)).setGraphic(
                    new ImageView(new Image("/buscamines/mine.png"))
            );
        });
        Stage perder = new Stage();
        perder.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox();

        dialogVbox.setSpacing(10);
        dialogVbox.setPadding(new Insets(20, 65, 20, 65));

        Label comoText = new Label("Has perdido\n" + "Total de minas:" + posMines.size());
        comoText.setAlignment(Pos.CENTER);

        perder.getIcons().add(new Image(this.getClass().getResource("/buscamines/mine.png").toString()));
        Button volverJugar = new Button("Volver a jugar");
        volverJugar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                initGame();
                perder.close();
            }
        });
        Button exit = new Button("Salir");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        volverJugar.setMaxWidth(Double.MAX_VALUE);
        exit.setMaxWidth(Double.MAX_VALUE);

        dialogVbox.getChildren().addAll(comoText, volverJugar, exit);

        Scene dialogScene = new Scene(dialogVbox);

        perder.setScene(dialogScene);
        perder.show();

        perder.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                initGame();

            }
        });
    }

    @Override
    public void win() {
        if (soundEnabled) {
            sounds.get("win").play();
        }
        Stage ganar = new Stage();
        ganar.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox();
        dialogVbox.setSpacing(10);
        dialogVbox.setPadding(new Insets(20, 65, 20, 65));
        ganar.getIcons().add(new Image(this.getClass().getResource("/buscamines/mine.png").toString()));
        ganar.setResizable(false);
        Label comoText = new Label("Has ganado\nOtra partida?");
        comoText.setAlignment(Pos.CENTER);

        Button siButt = new Button("Si");
        siButt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                initGame();
                ganar.close();
            }
        });
        Button noButt = new Button("No");
        noButt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);

            }
        });
        siButt.setMaxWidth(Double.MAX_VALUE);
        noButt.setMaxWidth(Double.MAX_VALUE);

        Scene dialogScene = new Scene(dialogVbox);
        dialogVbox.getChildren().addAll(comoText, siButt, noButt);
        ganar.setScene(dialogScene);
        ganar.show();

        ganar.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                initGame();

            }
        });
    }

    public void onGameRestart() {
        Stage reiniciar = new Stage();
        reiniciar.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox();
        dialogVbox.setSpacing(10);
        dialogVbox.setPadding(new Insets(20, 65, 20, 65));
//        AnchorPane anchorPuno = new AnchorPane();
//        AnchorPane anchorPdos = new AnchorPane();
        reiniciar.setResizable(false);
        reiniciar.getIcons().add(new Image(this.getClass().getResource("/buscamines/mine.png").toString()));

        Label otra = new Label("Reiniciar partida?");
        otra.setAlignment(Pos.CENTER);
        Button siButt = new Button("Si");
        siButt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lastSizeSelected = ((RadioMenuItem) sizeGroup.getSelectedToggle()).getText().toLowerCase();
                lastDiffSelected = ((RadioMenuItem) dificultyGroup.getSelectedToggle()).getText().toLowerCase();
                initGame();
                reiniciar.close();
            }
        });
        Button noButt = new Button("No");
        noButt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sizeGroup.getToggles().stream()
                        .filter(t -> ((RadioMenuItem) t).getText()
                        .toLowerCase()
                        .contains(lastSizeSelected))
                        .findFirst()
                        .get()
                        .setSelected(true);

                dificultyGroup.getToggles().stream()
                        .filter(t -> ((RadioMenuItem) t).getText()
                        .toLowerCase()
                        .contains(lastDiffSelected))
                        .findFirst()
                        .get()
                        .setSelected(true);
                reiniciar.close();

            }
        });

//        anchorPuno.getChildren().addAll(otra);
//        HBox hBox = new HBox(noButt,siButt);
//        anchorPdos.getChildren().addAll(hBox);
//hBox.setAlignment(Pos.CENTER_RIGHT);
//dialogVbox.setAlignment(Pos.CENTER_RIGHT);
//        anchorPane.getChildren().addAll(otra, siButt, noButt);
//        anchorPane.setTopAnchor(otra, 30.0);
//        anchorPane.setTopAnchor(siButt, 120.0);
//        anchorPane.setTopAnchor(noButt, 120.0);
//
//        anchorPane.setLeftAnchor(otra, 100.0);
//        anchorPane.setLeftAnchor(siButt, 100.0);
//        anchorPane.setLeftAnchor(noButt, 140.0);
        siButt.setMaxWidth(Double.MAX_VALUE);
        noButt.setMaxWidth(Double.MAX_VALUE);

        Scene dialogScene = new Scene(dialogVbox);
        dialogVbox.getChildren().addAll(otra, siButt, noButt);
        reiniciar.setScene(dialogScene);
        reiniciar.show();
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

        public MyButton getInstance() {
            return this;
        }

        private void initButton() {
            this.setMaxWidth(Double.POSITIVE_INFINITY);
            this.setMaxHeight(Double.POSITIVE_INFINITY);
            this.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    switch (event.getButton()) {
                        case PRIMARY:
                            if (!isFlag()) {
                                presentador.toUncover(pos);
                                if (soundEnabled) {
                                    sounds.get("click").play();
                                }
                            }
                            break;
                        case SECONDARY:
                            if (isFlag()) {
                                getInstance().setGraphic(null);
                                setFlag(false);
                            } else {
                                getInstance().setGraphic(
                                        new ImageView(new Image("/buscamines/flag.png"))
                                );
                                setFlag(true);
                            }

                            break;
                        default:

                    }

                }
            });
        }

    }

}
