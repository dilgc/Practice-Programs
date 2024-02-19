
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * Main class for the project, which allows the user to pick out items, calculates their subtotal, tip, tax, and total.
 *
 * @author Charles Dilger
 */

public class TipCalculatorApp extends Application {

    //Initialize the labels
    private Label mainTitleLabel;
    private Label addItemTitleLabel;
    private Label orderTitleLabel;
    private Label tipTitleLabel;

    //Initialize other JavaFX elements
    private ListView<String> itemsListView;

    private Slider tipSlider;

    private Button addToOrderButton;
    private Button orderButton;

    private TextArea currentOrderTextArea;

    //Initialize the customer object for this session
    private Customer currentCustomer;

    private Order selectedItem;

    /*
     * Start method creates a JavaFX window with all elements needed to obtain the users order and calculate their total
     *
     * @param primaryStage a Stage window object
     */
    public void start(Stage primaryStage){

        //Create the observable lists for the names and prices of the food selection
        ObservableList<String> stringObservableList = FXCollections.observableArrayList("Cheeseburger", "Pizza",
                "Steak", "French Fries", "Waffle Fries", "Onion Rings", "Pepsi", "Dr Pepper", "Sprite", "Mountain Dew");
        ObservableList<Double> doubleObservableList = FXCollections.observableArrayList(4.50, 22.99, 11.99, 4.50,
                4.50, 5.50, 2.99, 2.99, 1.99, 1.50);

        //Create the customer object
        currentCustomer = new Customer();

        //Declare the labels
        mainTitleLabel = new Label("Order Now!");
        addItemTitleLabel = new Label("Add Item: ");
        orderTitleLabel = new Label("Current Order: ");
        tipTitleLabel = new Label("Tip (%): ");

        //Create a list view to hold the observable list of strings
        itemsListView = new ListView<>(stringObservableList);

        //Create and modify the slider for tips
        tipSlider = new Slider(15, 25, 15);
        tipSlider.setShowTickLabels(true);
        tipSlider.setShowTickMarks(true);
        tipSlider.setMajorTickUnit(5);
        tipSlider.setSnapToTicks(true);
        tipSlider.setMinorTickCount(4);

        /*
         * Create the "Add to Order" button. This button takes the selected item(s) from the list view and adds them to the order
         *
         * @param event the event representing the action of pressing the add to order button
         */
        addToOrderButton = new Button("Add to Order");
        addToOrderButton.setOnAction(event -> {
            try{
                ObservableList<String> itemNames = itemsListView.getSelectionModel().getSelectedItems();
                for(String s : itemNames){
                    int index = stringObservableList.indexOf(s);
                    addToOrder(new Order(stringObservableList.get(index), doubleObservableList.get(index)));
                }
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null,
                        "Please enter food", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        /*
         * Create the "Confirm Order" button. This button takes in all the user information and completes the calculations before popping up the results in a joptionpane
         *
         * @param event the event representing the action of pressing the confirm order button
         */
        orderButton = new Button("Confirm Order");
        orderButton.setOnAction(event -> {
            currentCustomer.setTipPercent(tipSlider.getValue());
            JOptionPane.showMessageDialog(null, currentCustomer.getReceipt(), "Order Complete!", JOptionPane.INFORMATION_MESSAGE);
        });

        //Create the text area that will contain the order
        currentOrderTextArea = new TextArea();
        currentOrderTextArea.setEditable(false);
        currentOrderTextArea.setMaxWidth(250);
        currentOrderTextArea.setPrefHeight(400);

        //Format the application by stuffing elements in VBoxes and HBoxes and adding dividers
        VBox addToOrderButtonBox = new VBox(1, addToOrderButton);
        addToOrderButtonBox.setAlignment(Pos.CENTER);
        VBox orderNowVBox = new VBox(1, mainTitleLabel);
        orderNowVBox.setAlignment(Pos.CENTER);
        VBox addItemVBox = new VBox(3, addItemTitleLabel, itemsListView, addToOrderButtonBox);
        VBox orderVBox = new VBox(1, orderTitleLabel, currentOrderTextArea);
        orderVBox.setAlignment(Pos.TOP_RIGHT);
        VBox bottomApp = new VBox(5, tipTitleLabel, tipSlider, orderButton);
        bottomApp.setAlignment(Pos.CENTER);

        Line line = new Line(250, 0, 250, 350);
        HBox mainHBox = new HBox(10, addItemVBox, line, orderVBox);


        //Create the grid pane and set the stage
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        gridPane.add(orderNowVBox, 0, 0);
        gridPane.add(mainHBox, 0, 1);
        gridPane.add(bottomApp, 0, 2);

        Scene scene = new Scene(gridPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Order App by Charles Dilger");
        primaryStage.show();

    }

    /*
     * Method to add the item to the customer object.
     *
     * @param order takes in the item to add to the customer object
     */
    public void addToOrder(Order order){
        currentCustomer.addCustomerItem(order);
        currentOrderTextArea.setText(currentCustomer.toString());
    }

    /*
     * Main method, ignored by correctly deployed JavaFX program
     */
    public static void main(String[] args) {
        launch(args);
    }
}