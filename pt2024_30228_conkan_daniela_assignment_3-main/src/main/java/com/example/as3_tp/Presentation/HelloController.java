package com.example.as3_tp.Presentation;

import com.example.as3_tp.Model.Client;
import com.example.as3_tp.Model.Product;
import com.example.as3_tp.bussinessLogic.ClientBLL;
import com.example.as3_tp.bussinessLogic.ProductBLL;
import com.example.as3_tp.connection.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * main interface view
 * fxml interface for the user to interract with the database (operations on clients)
 */

public class HelloController {

    /**
     * colID,C2,C3...C6 are used as ids in fxml
     */
    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> C2;

    @FXML
    private TableColumn<?, ?> C3;

    @FXML
    private TableColumn<?, ?> C4;

    @FXML
    private TableColumn<?, ?> C5;
    @FXML
    private TableColumn<?, ?> C6;

    @FXML
        private Button btnAcctionsC;
        @FXML
        private Button btnAcctionsP;
        @FXML
        private Button btnCustomers;
        @FXML
        private Button btnNewOrder;
        @FXML
        private Button btnProducts;

    /**
     * we construct a table from multiple lines
     */

    @FXML
        private TableView<LinieTab> tabel;
        ProductBLL productBLL = new ProductBLL();
        ClientBLL clientBLL = new ClientBLL();
        @FXML
        private TextArea mentions;
        @FXML
        private TextField addAdress;
        @FXML
        private TextField addAge;
        @FXML
        private Button addButton;
        @FXML
        private TextField addID;
        @FXML
        private TextField addMail;
        @FXML
        private TextField addName;
        @FXML
        private Button delBtn;
        @FXML
        private TextField delID;
        @FXML
        private TextField upAddress;
        @FXML
        private TextField upAge;
        @FXML
        private Button upButton;
        @FXML
        private TextField upID;
        @FXML
        private TextField upMail;
        @FXML
        private TextField upName;

        ObservableList<LinieTab> listaRez = FXCollections.observableArrayList();

    /**
     * uses reflection to construct the header
     * @param entity any class in the Model
     * @param res the result set made by the statement called on the entity
     */
    void makeHeader(Object entity,ResultSet res) {

            Field[] f = entity.getClass().getDeclaredFields();
            String[] nameCols = new String[6];

            int i = 0;

            while (i < f.length) {
                nameCols[i] = f[i].getName();
                i++;
            }

            while (i < 6) {
                nameCols[i] = "-";
                i++;
            }

            colID.setText(nameCols[0]);
            C2.setText(nameCols[1]);
            C3.setText(nameCols[2]);
            C4.setText(nameCols[3]);
            C5.setText(nameCols[4]);
            C6.setText(nameCols[5]);

            try {
                while (res.next()) {

                    LinieTab linieTab = new LinieTab();
                    linieTab.setCel1(String.valueOf(res.getInt(1)));
                    linieTab.setCel2(res.getString(2));
                    linieTab.setCel3(res.getString(3));
                    linieTab.setCel4(res.getString(4));
                    if(entity instanceof Client)
                    linieTab.setCel5(String.valueOf(res.getInt(5)));

                    listaRez.add(linieTab);
                }
            } catch (SQLException e) {
                throw new RuntimeException("error " + e.getMessage(), e);
            }
            tabel.setItems(listaRez);
        }

    /**
     * method for handling user's clicks, it has additional logic to do operations on this specific table and displays the result
     * @param event
     */

        @FXML
        void handleClicks(ActionEvent event) {

            Connection connection = ConnectionFactory.getConnection();
            if(colID!=null && C2!=null && C3!=null &&  C4!=null && C5!=null) {
                colID.setCellValueFactory(new PropertyValueFactory<>("cel1"));
                C2.setCellValueFactory(new PropertyValueFactory<>("cel2"));
                C3.setCellValueFactory(new PropertyValueFactory<>("cel3"));
                C4.setCellValueFactory(new PropertyValueFactory<>("cel4"));
                C5.setCellValueFactory(new PropertyValueFactory<>("cel5"));
                C6.setCellValueFactory(new PropertyValueFactory<>("cel6"));
            }



            if (event.getSource() == btnProducts) {
                listaRez.clear();

                tabel.setItems(listaRez);

                ResultSet res = productBLL.findAll();

              makeHeader(new Product(),res);


            } else if (event.getSource() == btnCustomers) {
                listaRez.clear();

                tabel.setItems(listaRez);

                ResultSet res = clientBLL.findAll();

               makeHeader(new Client(),res);

            } else if (event.getSource() == btnAcctionsP) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("accProducts.fxml"));
                Parent root;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Stage stage = new Stage();
                stage.setTitle("Customers");
                stage.setScene(new Scene(root));
                stage.showAndWait();
            } else if (event.getSource() == btnAcctionsC) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Productsinterface.fxml"));
                Parent root;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Stage stage = new Stage();
                stage.setTitle("Products");
                stage.setScene(new Scene(root));
                stage.showAndWait();
            } else if (event.getSource() == btnNewOrder) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("addRecipe.fxml"));
                Parent root;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Stage stage = new Stage();
                stage.setTitle("Products");
                stage.setScene(new Scene(root));
                stage.showAndWait();
            } else if (event.getSource() == addButton) {
                int id2 = Integer.parseInt(addID.getText());
                String name = addName.getText();
                String mail = addMail.getText();
                String address = addAdress.getText();
                int age = Integer.parseInt(addAge.getText());
                Client client = new Client();
                client.setId(id2);
                client.setName(name);
                client.setMail(mail);
                client.setAddress(address);
                client.setAge(age);
                clientBLL.insert(client);
                mentions.setText("Client added successfully.");
            } else if (event.getSource() == upButton) {
                Client client2 = new Client();
                client2.setId(Integer.parseInt(upID.getText()));
                client2.setName(upName.getText());
                client2.setAddress(upAddress.getText());
                client2.setMail(upMail.getText());
                client2.setAge(Integer.parseInt(upAge.getText()));

                clientBLL.update(client2);
                mentions.setText("1 row has been updated.");
            } else if (event.getSource() == delBtn) {
                clientBLL.delete(Integer.parseInt(delID.getText()));
                mentions.setText("1 row has been deleted.");
            }
        }

    }