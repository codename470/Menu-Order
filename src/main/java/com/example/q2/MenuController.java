package com.example.q2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class MenuController {

    @FXML
    private GridPane grid;
    @FXML
    private Label [] types;

    @FXML
    private ArrayList<ComboBox<Integer>> cbItems;
    private ArrayList<Item> itemsShown;

    @FXML
    private ArrayList<CheckBox> checkBoxes;
    private Order order;

    public void initialize() {
        Menu menu = new Menu(FilesHandler.getMenu());
        order = new Order();
        HashMap<mealCourse, ArrayList<Item>> menuItems = menu.getMenu();
        ArrayList<mealCourse> keys = new ArrayList<>(menuItems.keySet());
        types = new Label[keys.size()];
        checkBoxes = new ArrayList<>();
        cbItems = new ArrayList<>();
        itemsShown = new ArrayList<>();

        int lastIndex = 0, colCounter = 0;
        for (int i = 0; i < keys.size(); i++) {
            types[i] = new Label(keys.get(i).name());
            types[i].setPrefSize(grid.getPrefWidth() / menu.getCounter() + 4, grid.getPrefHeight() / menu.getCounter() + 4);
            types[i].setId(keys.get(i).name());

            grid.add(types[i], colCounter, lastIndex);
            for (Item item : menuItems.get(keys.get(i))) {
                itemsShown.add(item);
                lastIndex++;
                Label tf = new Label(item.getDescription());
                tf.setPrefSize(grid.getPrefWidth() / menu.getCounter() + 4, grid.getPrefHeight() / menu.getCounter() + 4);
                grid.add(tf, colCounter, lastIndex);
                Label price = new Label("Price: " + Double.toString(item.getPrice()));
                colCounter++;
                price.setPrefSize(grid.getPrefWidth() / menu.getCounter() + 4, grid.getPrefHeight() / menu.getCounter() + 4);
                grid.add(price, colCounter, lastIndex);
                CheckBox cb = new CheckBox();
                colCounter++;
                grid.add(cb, colCounter, lastIndex);
                ComboBox<Integer> comboBox = new ComboBox<Integer>();
                comboBox.getItems().addAll(1,2,3,4,5,6);
                comboBox.getSelectionModel().selectFirst();
                colCounter++;
                grid.add(comboBox, colCounter, lastIndex);
                cbItems.add(comboBox);
                checkBoxes.add(cb);
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
            addItemsToOrder();
            System.out.println(order.getItems());
            TextInputDialog td = new TextInputDialog();
            td.setTitle("please enter your name and ID like this:");
            td.setHeaderText("For example: \n\"Eran123456789\"");
            td.setContentText("Name: ");
            Optional<String> fileName = td.showAndWait();
            if (fileName.isPresent()){
                String file = fileName.get();
                if (file.matches("[a-zA-Z]+\\d{9}")) {
                    order.closeOrder(file);
                    resetMenu();
                }
                else {
                    JOptionPane.showConfirmDialog(null, "ID not in the Standards", "Error", JOptionPane.CLOSED_OPTION);
                }
            }
        } else if (result.get() == update) {
            // Not need to do anything
        } else {
            resetMenu();
        }
    }

    void addItemsToOrder(){
        int ls = itemsShown.size();
        System.out.println(itemsShown.size());
        for (int i =0; i< ls; i++){
            Item item = itemsShown.get(i);
            if (checkBoxes.get(i).isSelected()){
                order.addItem(new Item(item.getDescription(), item.getType().name(),  cbItems.get(i).getValue()));
            }
        }
    }

    void resetMenu(){
        for (CheckBox cb : checkBoxes){
            cb.setSelected(false);
        }
        for (ComboBox<Integer> cb : cbItems){
            cb.getSelectionModel().selectFirst();
        }
        order = new Order();
    }
}