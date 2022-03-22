package buscamines.impl;

import buscamines.BuscaminesContract;
import buscamines.BuscaminesContract.BuscaminesView;
import java.awt.Button;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 *
 * @author usuari
 */
public class BuscaminesViewImpl implements Initializable, BuscaminesView {

    private List<MyButton> buttons;

    public BuscaminesViewImpl(Stage stage) {
        initUI(stage);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    // UI
    private void initUI(Stage stage) {

    }

    @Override
    public void setPresenter(BuscaminesContract.BuscaminesPresenter p) {
        throw new RuntimeException("no implementat!");
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
            this.pos=pos;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

    }

}
