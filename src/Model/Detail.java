/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author jkear
 */
public class Detail {
    private String productName;
    private int barCode;
    private int quantity;
    private double price;

    public Detail() {
    }

    public Detail(String productName, int barCode, int quantity, double price) {
        this.productName = productName;
        this.barCode = barCode;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getBarCode() {
        return barCode;
    }

    public void setBarCode(int barCode) {
        this.barCode = barCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Detail{" + "productName=" + productName + ", barCode=" + barCode + ", quantity=" + quantity + ", price=" + price + '}';
    }
    
    
}
