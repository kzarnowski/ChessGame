package Play;
import Pieces.*;

public class BoardVisualizer {

    private static final String EMPTY_TILE = "  ";
    private static final String FRAME_SEGMENT = "--";
    private static final String TILE_SEGMENT = "|";
    private Chessboard board;

    public BoardVisualizer(Chessboard board) {
        this.board= board;
    }

    public String draw() {
        StringBuilder builder = new StringBuilder();
        for (int i = 8; i>= -1; i--) {
            if (i == 8) {
                builder.append(drawHeader());
            }
            builder.append(String.format("%3d: ", i));
            for (int j = 0; j <= 8; j++) {
                if (i < 0 || i > 7) {
                    builder.append(drawFrame(j <= 8));
                } else {
                    builder.append(TILE_SEGMENT);
                    if (j <= 7) {
                        builder.append(drawPiece(new Vector(j, i )));
                    }
                }
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

    private String drawHeader() {
        StringBuilder builder = new StringBuilder();
        builder.append(" y\\x ");
        for (int j = 0; j < 8; j++) {
            builder.append(String.format(" %2d", j));
        }
        builder.append(System.lineSeparator());
        return builder.toString();
    }

    private String drawFrame(boolean innerSegment) {
        if (innerSegment) {
            return FRAME_SEGMENT + FRAME_SEGMENT;
        } else {
            return FRAME_SEGMENT;
        }
    }

    private String drawPiece(Vector currentPosition) {
        StringBuilder builder = new StringBuilder();
        if (board.isOccupied(currentPosition)) {
            Object piece = board.getSquares()[currentPosition.x][currentPosition.y].getPiece();
            builder.append(((Piece) piece).getSide() == Sides.WHITE ? "w" : "b");

            if (piece instanceof Pawn) {
                builder.append("P");
            } else if (piece instanceof Rook) {
                builder.append("R");
            } else if (piece instanceof Knight) {
                builder.append("N");
            } else if (piece instanceof Bishop) {
                builder.append("B");
            } else if (piece instanceof Queen) {
                builder.append("Q");
            } else if (piece instanceof King) {
                builder.append("K");
            } else {
                builder.append(" ");
            }
        } else {
            builder.append(EMPTY_TILE);
        }
        return builder.toString();
    }
}
