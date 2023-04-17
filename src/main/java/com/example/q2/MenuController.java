package com.example.q2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class MenuController {

    @FXML
    private GridPane grid;
    @FXML
    private Label [] types;

    @FXML
    private ComboBox<Integer> [] cbItems;

    @FXML
    private CheckBox [] checkBoxes;
    @FXML
    private Button orderBtn;
    private Order order;

    public void initialize() {
        Menu menu = new Menu(FilesHandler.getMenu());
        HashMap<mealCourse, ArrayList<Item>> menuItems = menu.getMenu();
        ArrayList<mealCourse> keys = new ArrayList<>(menuItems.keySet());
        types = new Label[keys.size()];

        int lastIndex = 0, colCounter = 0;
        for (int i = 0; i < keys.size(); i++) {
            types[i] = new Label(keys.get(i).name());
            types[i].setPrefSize(grid.getPrefWidth() / menu.getCounter() + 4, grid.getPrefHeight() / menu.getCounter() + 4);
            types[i].setId(keys.get(i).name());
            grid.add(types[i], colCounter, lastIndex);
            for (Item item : menuItems.get(keys.get(i))) {
                lastIndex++;
                Label tf = new Label(item.getDescription());
                tf.setPrefSize(grid.getPrefWidth() / menu.getCounter() + 4, grid.getPrefHeight() / menu.getCounter() + 4);
                grid.add(tf, colCounter, lastIndex);
                CheckBox cb = new CheckBox();
                colCounter++;
                grid.add(cb, colCounter, lastIndex);
                ComboBox<Integer> comboBox = new ComboBox<Integer>();
                comboBox.getItems().addAll(1,2,3,4,5,6);
                colCounter++;
                grid.add(comboBox, colCounter, lastIndex);
                lastIndex++;
                colCounter = 0;
            }

        }
    }
    @FXML
    void orderPressed(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Do you want to complete your order?");
        alert.setContentText("This action cannot be undone.");

        ButtonType confirm = new ButtonType("Confirm");
        ButtonType update = new ButtonType("Update");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(confirm, update, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == confirm){
            // do something
        } else if (result.get() == update) {
            // do something else
        } else {
            order = new Order();
        }
    }
}