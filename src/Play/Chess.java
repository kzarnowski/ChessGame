package Play;
import GUI.BoardGUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/* --------------------------------------------------------------
    TO DO:
        1) Naprawic canMove dla wszystkich figur, pozycjonowanie (vector?)
        2) Listeners - jak przerysowywać gui po wykonaniu ruchu?
        3) MouseEvents
 ------------------------------------------------------------- */





public class Chess extends Application {

    public static void main(String[] args) {
        launch(args);
        /*
        System.out.println("Let's play chess!");
        Game GameOfChess = new Game();
        GameOfChess.play();
        */
    }

    @Override
    public void start(Stage window) throws Exception {

        Game GameOfChess = new Game();

        GameOfChess.play();
    }
}
