/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author jkear
 */
public class Customer {
    
    private String customerId;
    private String saleId;
    private ArrayList<Detail> details;

    public Customer() {
    }

    public Customer(String customerId, String saleId, ArrayList<Detail> details) {
        this.customerId = customerId;
        this.saleId = saleId;
        this.details = details;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public ArrayList<Detail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<Detail> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", saleId=" + saleId + ", details=" + details + '}';
    }
    
    
    
}
