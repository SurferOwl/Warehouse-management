package com.example.as3_tp.Presentation;

import com.example.as3_tp.Model.Bill;
import com.example.as3_tp.Model.IdUtil;
import com.example.as3_tp.Model.Product;
import com.example.as3_tp.Model.Recipe;
import com.example.as3_tp.bussinessLogic.ClientBLL;
import com.example.as3_tp.bussinessLogic.OrderBLL;
import com.example.as3_tp.bussinessLogic.ProductBLL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * fxml interface for the user to interract with the database (operations on orders and Log table)
 */

public class ControllerRecipe {

    @FXML
    private Button addRecipe;

    @FXML
    private TextField idCantitate;

    @FXML
    private TextField idCumparator;

    @FXML
    private TextField idObject;

    @FXML
    private TextArea mentions;

    @FXML
    private Button placeRecipe;

    private final ProductBLL productBLL = new ProductBLL();

    private final ClientBLL clientBLL = new ClientBLL();

    /**
     * method for handling user's clicks, it has additional logic to do operations on this specific table and displays the result
     * @param event
     */

    @FXML
    void handleClicks(ActionEvent event) {
        float price = 0.0f;
        int cantitateRep = 0;
        int nextId = IdUtil.readId();
        Bill bill = null;

        if (event.getSource() == addRecipe) {
            String cantitate = idCantitate.getText();
            String object = idObject.getText();
            int productID = Integer.parseInt(object);
            int clientID = Integer.parseInt(idCumparator.getText());

            ResultSet rezClient = clientBLL.findById(clientID);
            ResultSet rezProd = productBLL.findById(productID);
            try {
                if(rezClient.next()){
                    if (rezProd.next()) {
                        int cantitateProd = rezProd.getInt(4);
                        if (cantitateProd < Integer.parseInt(cantitate) || cantitateProd <= 0) {
                            mentions.setText("Cantitate prea mica.\n");
                            System.out.println("Cantitate prea mica.\n");
                        } else {
                            int cantUp = cantitateProd - Integer.parseInt(cantitate);
                            Product product = new Product();
                            product.setId(productID);
                            product.setName(rezProd.getString(2));
                            product.setPrice(rezProd.getFloat(3));
                            product.setStock(cantUp);
                            productBLL.update(product);

                            OrderBLL order = new OrderBLL();

                            Recipe recipe = new Recipe(nextId, Integer.parseInt(cantitate), rezProd.getFloat(3), rezClient.getString(4));
                            bill = new Bill(nextId,Integer.parseInt(cantitate),rezProd.getFloat(3),rezClient.getString(4));
                            order.insert(recipe);

                            nextId++;

                            IdUtil.writeId(nextId);

                            mentions.setText("Ai adaugat produsul " + product.getName() + " cu succes.\n");
                            System.out.println("Ai adaugat produsul " + product.getName() + " cu succes.\n");
                            price += Integer.parseInt(cantitate) * product.getPrice();
                            cantitateRep += Integer.parseInt(cantitate);
                        }
                    } else {
                        mentions.setText("Nu s-a gasit niciun produs cu ID-ul specificat.\n");
                        System.out.println("Nu s-a gasit niciun produs cu ID-ul specificat.\n");
                    }
                } else {
                    mentions.setText("Nu s-a gasit niciun client cu ID-ul specificat.\n");
                    System.out.println("Nu s-a gasit niciun client cu ID-ul specificat.\n");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (rezProd != null) rezProd.close();
                    if (rezClient != null) rezClient.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else if (event.getSource() == placeRecipe) {


            OrderBLL order = new OrderBLL();
            try {
                order.insertBillRec();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            mentions.setText("Comanda plasata!");
        }
    }}
