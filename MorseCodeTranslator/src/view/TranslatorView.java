package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import util.Utilities;

public class TranslatorView {

    //Declare all JavaFX elements
    private Label titleLabel;
    private Label enterLanguageLabel;
    private Label outputLanguageLabel;
    private Label arrowsLabel;
    private TextArea enterText;
    private TextArea outputText;

    private VBox root;

    /**
     * Constructor for the translator view.
     * The translator view will be where all the JavaFX visuals are
     */
    public TranslatorView(){

        //Initialize and modify the labels
        arrowsLabel = new Label("↓↑");
        arrowsLabel.setFont(Font.font(50));

        titleLabel = new Label("Morse Code Translator");
        enterLanguageLabel = new Label("Your input:");
        outputLanguageLabel = new Label("Translation: ");

        titleLabel.setFont(Font.font(25));

        //Initialize and modify the two text areas
        enterText = new TextArea();
        enterText.setPromptText("Enter your input here.");
        enterText.setPrefHeight(200);
        enterText.setWrapText(true);

        outputText = new TextArea();
        outputText.setEditable(false);
        outputText.setPrefHeight(200);
        outputText.setWrapText(true);

        //Create VBoxes for storing them in
        VBox titleBox = new VBox(5, titleLabel);
        titleBox.setAlignment(Pos.CENTER);

        VBox arrowBox = new VBox(1, arrowsLabel);
        arrowBox.setAlignment(Pos.CENTER);
        arrowBox.setTranslateY(10);

        VBox enterLanguageBox = new VBox(5, enterLanguageLabel, enterText);
        VBox outputLanguageBox = new VBox(5, outputLanguageLabel, outputText);

        //Make it so that when the user enters a letter, the translation happens in real-time
        enterText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Check if the new value is not empty
                if (!newValue.isEmpty()) {
                    outputText.setText(Utilities.Translate(enterText.getText()));
                }
                else {
                    outputText.setText("");
                }
            }
        });

        //Create the which will be transferred to the app during scene creation
        root = new VBox(titleBox, enterLanguageBox, arrowBox, outputLanguageBox);
        root.setPadding(new Insets(20));
    }

    /**
     * Give the root, for the main method
     * @return the root
     */
    public VBox getRoot() {
        return root;
    }

}
