package GUI;

import Pieces.Piece;
import Play.Chessboard;
import Play.Game;
import Play.Tile;
import Play.Vector;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.*;
import java.util.ArrayList;

public class BoardGUI {

    // all displayed elements
    private ArrayList<PieceGUI> pieces;
    private ArrayList<TileGUI> tiles;
    private ArrayList<SelectGUI> selects;

    private Game game;
    private Chessboard board;
    private Tile[][] squares;
    private GridPane grid;
    private Stage window;
    private Scene chessboard;
    public static final int TILE_SIZE = 80;

    // to store information about first click
    private boolean moveInProgress = false;
    private Vector moveFrom = null;
    private SelectGUI select = null;

    public BoardGUI(Game game) {
        this.pieces = new ArrayList<>();
        this.tiles = new ArrayList<>();
        this.selects = new ArrayList<>();
        this.board = game.getBoard();
        this.game = game;
        this.squares = game.getBoard().getSquares();
        this.grid = new GridPane();
        this.window = new Stage();
        this.window.setTitle("ChessGame");
        this.chessboard = new Scene(grid, 660, 660);
        this.window.setScene(chessboard);

        chessboard.setFill(Color.rgb(62,39,35));
        grid.setPadding(new Insets(10,10,10,10));
        grid.setAlignment(Pos.CENTER);

        draw();
        window.show();
    }


    private void draw() {
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    tiles.add(drawTile(x,y));
                    if (squares[x][y].isOccupied()) {
                        pieces.add(drawPiece(x,y));
                    }
                }
            }
    }

    public void removePiece(Piece piece) {
        grid.getChildren().remove(piece.getView().getView());
    }

    public void putPiece(Piece piece) {
        try {
            ImageView img = piece.getView().getView();
            GridPane.setValignment(img, VPos.CENTER);
            GridPane.setHalignment(img, HPos.CENTER);
            GridPane.setConstraints(img, piece.getPosition().x, piece.getPosition().y);
            grid.getChildren().add(img);
        } catch (Exception e) {
            //
        }
    }

    public void movePiece(Vector newPos) {

        Piece piece = squares[newPos.x][newPos.y].getPiece();
        removePiece(piece);
        putPiece(piece);
    }


    // create a very new piece on the grid
    private PieceGUI drawPiece(int col, int row) {
        PieceGUI piece = new PieceGUI(squares[col][row].getPiece());
        putPiece(squares[col][row].getPiece());
        piece.getView().addEventFilter(MouseEvent.MOUSE_CLICKED, eBoardClicked);
        return piece;
    }

    // create a very new tile on the grid
    private TileGUI drawTile(int col, int row) {
        TileGUI tile = new TileGUI(squares[col][row]);
        ImageView tileView = tile.getView();
        GridPane.setConstraints(tileView, col, row);
        grid.getChildren().add(tileView);
        tileView.addEventFilter(MouseEvent.MOUSE_CLICKED, eBoardClicked);
        return tile;
    }

    // create a very new select on the grid
    private SelectGUI drawSelect(int col, int row) {
        SelectGUI select = new SelectGUI();
        ImageView selectView = select.getView();
        GridPane.setValignment(selectView, VPos.CENTER);
        GridPane.setHalignment(selectView, HPos.CENTER);
        GridPane.setConstraints(selectView, col, row);
        grid.getChildren().add(selectView);
        return select;
    }

    // erase existing select from the grid
    private void eraseSelect(SelectGUI select) {
        this.select = null;
        grid.getChildren().remove(select.getView());
    }

    //--------------------------------------------------------------------------------

    private EventHandler<MouseEvent> eBoardClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            ImageView tmp = (ImageView)(e.getTarget());
            Vector pos = new Vector(GridPane.getColumnIndex(tmp), GridPane.getRowIndex(tmp));

            if (moveInProgress) {
                endMove(pos);
            } else {
                startMove(pos);
            }
        }

        // first click during move
        private void startMove(Vector pos) {
            // empty tile clicked
            if (!board.isOccupied(pos)) {
                return;
            }
            // piece clicked
            select = drawSelect(pos.x, pos.y);
            moveInProgress = true;
            moveFrom = pos;
        }

        // second click during move
        private void endMove(Vector pos) {
            game.move(moveFrom, pos);
            eraseSelect(select);
            moveFrom = null;
            moveInProgress = false;
        }
    };



}
