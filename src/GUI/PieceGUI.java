package GUI;

import Pieces.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PieceGUI {

    private ImageView view;
    private Piece piece;

    public PieceGUI(Piece piece) {
        try {
            this.piece = piece;
            theme1(piece);
            piece.setView(this);
        } catch(FileNotFoundException e) {
            //
        }

    }

    public ImageView getView() {
        return view;
    }

    private void theme1(Piece piece) throws FileNotFoundException {

        if (piece instanceof Pawn) {
            String source = piece.isWhite()
                    ? "src/GUI/images/wp.png"
                    : "src/GUI/images/bp.png";
            this.view = new ImageView(new Image(new FileInputStream(source)));
        } else if (piece instanceof Rook) {
            String source = piece.isWhite()
                    ? "src/GUI/images/wr.png"
                    : "src/GUI/images/br.png";
            this.view = new ImageView(new Image(new FileInputStream(source)));
        } else if (piece instanceof Knight) {
            String source = piece.isWhite()
                    ? "src/GUI/images/wn.png"
                    : "src/GUI/images/bn.png";
            this.view = new ImageView(new Image(new FileInputStream(source)));
        } else if (piece instanceof Bishop) {
            String source = piece.isWhite()
                    ? "src/GUI/images/wb.png"
                    : "src/GUI/images/bb.png";
            this.view = new ImageView(new Image(new FileInputStream(source)));
        } else if (piece instanceof Queen) {
            String source = piece.isWhite()
                    ? "src/GUI/images/wq.png"
                    : "src/GUI/images/bq.png";
            this.view = new ImageView(new Image(new FileInputStream(source)));
        } else if (piece instanceof King) {
            String source = piece.isWhite()
                    ? "src/GUI/images/wk.png"
                    : "src/GUI/images/bk.png";
            this.view = new ImageView(new Image(new FileInputStream(source)));
        }
        this.view.setFitHeight(70);
        this.view.setFitWidth(70);
    }
}
