package Play;
import GUI.BoardGUI;
import GUI.TileGUI;
import Pieces.*;


public class Tile {

    private final Vector position;
    private Piece piece;
    private TileGUI view;


    public Tile(Vector position, Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    //Getters
    public Piece getPiece() {
        return this.piece;
    }
    public Vector getPosition() {
        return this.position;
    }

    //Setters
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void deletePiece() {
        this.piece = null;
    }

    public boolean isOccupied() {
        return this.piece != null;
    }

    public void setView(TileGUI view) {
        this.view = view;
    }
}
