package com.example.supplychainmanagementsystem;

public class order {
    public static boolean placeOrder(String customerEmail, Product product){
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        String query= String.format("INSERT INTO orders(customer_id, product_id) VALUES((SELECT idcustomer FROM customer WHERE email = '%s'), %s)",customerEmail, product.getId());
        int rowCount = 0;
        try{
            rowCount = dataBaseConnection.excuteUpdateQuery(query);
        }catch(Exception e){
            e.printStackTrace();
        }
        return rowCount!=0 ;

    }
}

