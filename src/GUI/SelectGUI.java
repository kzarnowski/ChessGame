package GUI;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SelectGUI {

    private ImageView view;

    public SelectGUI() {
        try {
            this.theme1();
        } catch (FileNotFoundException ex) {
            //
        }
    }

    public ImageView getView() {
        return view;
    }

    private void theme1() throws FileNotFoundException {
        this.view = new ImageView(new Image(new FileInputStream("src/GUI/images/board/select.png")));
        this.view.setFitWidth(80);
        this.view.setFitHeight(80);
    }

    public void display(int col, int row) {

    }

    public void remove() {

    }
}
