package com.example.as3_tp.Presentation;

import com.example.as3_tp.Model.Product;
import com.example.as3_tp.bussinessLogic.ProductBLL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * fxml interface for the user to interract with the database (operations on products table)
 */

public class ControllerProducts {
    private final ProductBLL productBLL = new ProductBLL();

    @FXML
    private TextField addName;

    @FXML
    private TextField addPrice1;

    @FXML
    private TextField addStock1;

    @FXML
    private Button addButton;

    @FXML
    private TextField addID;

    @FXML
    private Button delBtn;

    @FXML
    private TextField delID;

    @FXML
    private TextArea mentions;

    @FXML
    private TextField upPrice;

    @FXML
    private Button upButton;

    @FXML
    private TextField upID;

    @FXML
    private TextField upName;

    @FXML
    private TextField upStock;

    /**
     * method for handling user's clicks, it has additional logic to do operations on this specific table and displays the result
     * @param event
     */

    @FXML
    void handleClicks(ActionEvent event) {
        if (event.getSource() == addButton) {

            int id = 0;
            String name = "";
            float price = 0.0f;
            int stock = 0;

            if (!addPrice1.getText().isEmpty() && !addID.getText().isEmpty() && !addName.getText().isEmpty() && !addStock1.getText().isEmpty()) {
                id = Integer.parseInt(addID.getText());
                name = addName.getText();
                price = Float.parseFloat(addPrice1.getText());
                stock = Integer.parseInt(addStock1.getText());
            }


            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setPrice(price);
            product.setStock(stock);

            productBLL.insert(product);
            mentions.setText("Product added successfully.");

        } else if (event.getSource() == upButton) {
            int id = Integer.parseInt(upID.getText());
            String name = upName.getText();
            float price = Float.parseFloat(upPrice.getText());
            int stock = Integer.parseInt(upStock.getText());

            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setPrice(price);
            product.setStock(stock);
            productBLL.update(product);

            mentions.setText("Product updated successfully.");

        } else if (event.getSource() == delBtn) {
            int id = Integer.parseInt(delID.getText());
            productBLL.delete(id);
            mentions.setText("Product deleted successfully.");
        }
    }}