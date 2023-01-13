package com.example.supplychainmanagementsystem;

import com.example.supplychainmanagementsystem.SupplyChain;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProductDetails {
    public TableView<Product> productTable;

    public Pane getAllproducts(){

        TableColumn id = new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

//        ObservableList<Product> data = FXCollections.observableArrayList();
//        data.add(new Product(1,"HP",85400));
//        data.add(new Product(1,"Lenovo",65400));
        ObservableList<Product> products = Product.getAllProducts();

        productTable = new TableView<>();
        productTable.setItems(products);
        productTable.getColumns().addAll(id, name, price);
        productTable.setMinSize(SupplyChain.width,SupplyChain.height);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Pane tablePane = new Pane();
        tablePane.setMinSize(SupplyChain.width,SupplyChain.height);
        tablePane.setStyle("-fx-background-color: #C0C0C0");
        tablePane.getChildren().add(productTable);
        return tablePane;
    }
    public Pane getproductsByName(String productName){

        TableColumn id = new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

//        ObservableList<Product> data = FXCollections.observableArrayList();
//        data.add(new Product(1,"HP",85400));
//        data.add(new Product(1,"Lenovo",65400));
        ObservableList<Product> products = Product.getProductsByName(productName);

        productTable = new TableView<>();
        productTable.setItems(products);
        productTable.getColumns().addAll(id, name, price);
        productTable.setMinSize(SupplyChain.width,SupplyChain.height);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Pane tablePane = new Pane();
        tablePane.setMinSize(SupplyChain.width,SupplyChain.height);
        tablePane.setStyle("-fx-background-color: #C0C0C0");
        tablePane.getChildren().add(productTable);
        return tablePane;
    }
    public Product getSelectedProduct(){
        try{
            Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
            return selectedProduct;

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}