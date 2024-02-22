package main;

import view.TranslatorView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.Utilities;
import view.TranslatorView;

/**
 * Main class for the project, which allows the user to enter input in morse or english,
 * and it will automatically use regex to determine the language inputted by the user and
 * translate it respectively.
 *
 * @author Charles Dilger
 */
public class TranslatorApp extends Application {

    /**
     * The main method, ignored by the JavaFX program
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start method which creates the window and puts the translator view in it.
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        System.out.println(Utilities.TranslateWordToMorse("abcdef"));
        TranslatorView translatorView = new TranslatorView();
        VBox root = translatorView.getRoot();
        Scene scene = new Scene(root, 500, 600);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();


    }
}