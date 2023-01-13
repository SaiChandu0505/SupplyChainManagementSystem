package com.example.supplychainmanagementsystem;


import com.example.supplychainmanagementsystem.Login;
import com.example.supplychainmanagementsystem.Product;
import com.example.supplychainmanagementsystem.ProductDetails;
import com.example.supplychainmanagementsystem.order;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class SupplyChain extends Application {

    public static final int width = 700,height = 600,headerBar = 50 ;
    Pane bodyPane = new Pane();

    public static int bodyWidth,bodyHeight;
    Login login = new Login();

    ProductDetails productDetails = new ProductDetails();

    Button golbalLoginButton ;
    Label customerEmailLabel = null ;

    String customerEmail = null ;
    private  GridPane headerBar(){
        TextField searchText = new TextField();
        Button searchButton = new Button("search");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String productName = searchText.getText();
                productDetails.getproductsByName(productName);
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productDetails.getproductsByName(productName));

            }
        });
        golbalLoginButton = new Button("Log In");
        golbalLoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
                golbalLoginButton.setDisable(true);

            }
        });
        customerEmailLabel = new Label("WELCOME TO MY STORE");
        GridPane gridpane = new GridPane();
        gridpane.setMinSize(bodyPane.getMinWidth(),headerBar-10);
        gridpane.setVgap(5);
        gridpane.setHgap(5);
        gridpane.setStyle("-fx-background-color: #C0C0C0");
        gridpane.setAlignment(Pos.CENTER);

        gridpane.add(searchText,0,0);
        gridpane.add(searchButton,1,0);
        gridpane.add(golbalLoginButton,2,0);
        gridpane.add(customerEmailLabel,3,0);
        return gridpane ;
    }

    private GridPane loginPage(){
        Label emailLabel = new Label("Email");
        Label passwordLabel = new Label("Password");
        Label messageLabel = new Label("WELCOME TO MY WORLD");

        TextField emailTextField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email = emailTextField.getText();
                String password = passwordField.getText();
                //messageLabel.setText(email + " $$ " + password);
                if(login.customerLogin(email,password)){
                    messageLabel.setText("Login Successful");
                    customerEmail = email;
                    golbalLoginButton.setDisable(true);
                    customerEmailLabel.setText("welcome: " + customerEmail );
                    bodyPane.getChildren().add(productDetails.getAllproducts());
                }else{
                    messageLabel.setText("Account Not Found");
                    Button SignIn = new Button();
                }

            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        //gridPane.setStyle("-fx-background-color: #C0C0C0");
        gridPane.setAlignment(Pos.CENTER);


        gridPane.add(emailLabel,0,0);
        gridPane.add(emailTextField,1,0);
        gridPane.add(passwordLabel,0,1);
        gridPane.add(passwordField,1,1);
        gridPane.add(loginButton,1,2);
        gridPane.add(messageLabel,1,3);

        return gridPane ;
    }

    private  GridPane footerBar(){
        Button addToCartButton = new Button("Add to Cart");
        Button buyNowButton = new Button("Buy Now");
        Label messageLabel = new Label("");
        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product selectedProduct = productDetails.getSelectedProduct();
                if(order.placeOrder(customerEmail, selectedProduct)){
                    messageLabel.setText("Order Placed");
                }
                else{
                    messageLabel.setText("Order Failled");
                }
            }
        }) ;

        GridPane gridpane = new GridPane();
        gridpane.setMinSize(bodyPane.getMinWidth(),headerBar-10);
        gridpane.setVgap(5);
        gridpane.setHgap(50);
        gridpane.setStyle("-fx-background-color: #C0C0C0");
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setTranslateY(headerBar+height+5);
        gridpane.add(addToCartButton,0,0);
        gridpane.add(buyNowButton,1,0);
        gridpane.add(messageLabel,2,0);
        return gridpane ;
    }

    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width,height+2*headerBar);

        bodyPane.setMinSize(width,height);
        bodyPane.setTranslateY(headerBar);
        bodyPane.getChildren().addAll(productDetails.getAllproducts());
        root.getChildren().addAll(headerBar(),bodyPane,footerBar());
        return root ;
    }

    @Override
    public void start(Stage stage) throws IOException {
        // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}