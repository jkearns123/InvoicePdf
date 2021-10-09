/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoicepdf;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import util.Invoice;

/**
 *
 * @author jkear
 */
public class InvoicePdf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DocumentException, BadElementException, IOException {
        Invoice.createInvoice();
    }
    
}
