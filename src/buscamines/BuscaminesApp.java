package buscamines;

import buscamines.BuscaminesContract.BuscaminesModel;
import buscamines.BuscaminesContract.BuscaminesPresenter;
import buscamines.BuscaminesContract.BuscaminesView;
import buscamines.impl.BuscaminesModelImpl;
import buscamines.impl.BuscaminesPresenterImpl;
import buscamines.impl.BuscaminesViewImpl;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.RadioMenuItemBuilder;
import javafx.scene.control.ToggleGroup;
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
        stage.setTitle("Menu Sample");
        Scene scene = new Scene(new VBox(), 400, 350);
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
        
        CheckMenuItem SDiez = new CheckMenuItem("10x10");
        CheckMenuItem SQuince = new CheckMenuItem("15x15");
        CheckMenuItem SVeinte = new CheckMenuItem("20x20");
        
        size.getItems().addAll(SDiez,SQuince,SVeinte);
        
        ToggleGroup myToggleGroup = new ToggleGroup();
        
        
        
        RadioMenuItem DFacil = RadioMenuItemBuilder.create().toggleGroup(myToggleGroup).text("Facil").build();
        RadioMenuItem DMedio =  RadioMenuItemBuilder.create().toggleGroup(myToggleGroup).text("Medio").build();
        RadioMenuItem DDificil = RadioMenuItemBuilder.create().toggleGroup(myToggleGroup).text("Dificil").build();
        
        difficulty.getItems().addAll(DFacil,DMedio,DDificil);
        
        RadioMenuItem SSonido = RadioMenuItemBuilder.create().toggleGroup(myToggleGroup).text("On").build();
        RadioMenuItem SOff = RadioMenuItemBuilder.create().toggleGroup(myToggleGroup).text("Off").build();
        sound.getItems().addAll(SSonido,SOff);
        
        MenuItem HComoJugar = new MenuItem("Como jugar");
        MenuItem HAutores = new MenuItem("Autores");
        
        help.getItems().addAll(HAutores,HComoJugar);
        
 
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar);
 
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
