package Play;

/*----------------------------------------
        TO DO
        1) usuwanie figury z gui po zbiciu
        2) king: roszada, bycie szachowanym
        3) lista ruchów
        4) pawn: bicie w przelocie (?)
        5) Observer
        6) Podświetlanie zaznaczonego grida
----------------------------------------*/

import GUI.BoardGUI;
import Pieces.Piece;
import Pieces.Rook;

public class Game {

    private BoardGUI gui;
    private Chessboard board;

    public Game () {
        this.board = new Chessboard();
        this.gui = new BoardGUI(this);
    }

    public Chessboard getBoard() {
        return board;
    }

    public void move(Vector start, Vector end) {
        try {
            Move m1 = new Move(this.board, start, end);
            m1.make();
            if (m1.isCapture())
                this.gui.removePiece(m1.getCaptured());
        } catch(IllegalArgumentException e) {
            System.out.println("Illegal move");
            return;
        }
        gui.movePiece(end);
    }

    public void play() {

        boolean xd = board.isLegalMove(new Vector(4,4), new Vector(4,4));
//
//        while (true) {
//            try {
//
//            }
//            catch(Exception e) {
//                System.out.println("Illegal move");
//                System.out.println(board.toString());
//            }
//        }


    }

    public void addPiece() {
        try {
            board.putPiece(new Rook(board, new Vector(4,4), Sides.WHITE));
        }
        catch(Exception e) {};
        gui.putPiece(board.getPiece(new Vector(4,4)));

    }
}


