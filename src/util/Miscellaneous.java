/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author jkear
 */
public class Miscellaneous {
    
    public static String generateRandomDocId() {
        return UUID.randomUUID().toString();
    }

    public static String generateCurrentDate() {
        String currentDate;
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        currentDate = dateFormat.format(date);
        return currentDate;
    }
    
    public static int generateRandomBarcode(){
        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);
        return n;
    }
    
    public static String generateCustomerId(){
        Random rnd = new Random();
        int n = 6000 + rnd.nextInt(9000);
        return ("CUSID" + n);
    }
    
    public static String generateSaleId(){
        Random rnd = new Random();
        int n = 6000 + rnd.nextInt(9000);
        return ("SALID" + n);
    }
    
}
