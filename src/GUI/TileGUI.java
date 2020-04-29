package GUI;

import Play.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TileGUI  {

    private Tile tile;
    private ImageView view;

    public TileGUI(Tile tile) {
        try {
            this.tile = tile;
            this.theme1();
            this.tile.setView(this);
        } catch(FileNotFoundException e) {
            //
        }
    }

    private void theme1() throws FileNotFoundException {
        String source = (tile.getPosition().x + tile.getPosition().y) % 2 == 1
                ? "src/GUI/images/board/maple_d.png"
                : "src/GUI/images/board/maple_l.png";
        this.view = new ImageView(new Image(new FileInputStream(source)));
        this.view.setFitWidth(80);
        this.view.setFitHeight(80);
    }

    public ImageView getView() {
        return view;
    }
}
