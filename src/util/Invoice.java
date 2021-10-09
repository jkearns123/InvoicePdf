/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Model.Customer;
import Model.Detail;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import static util.Miscellaneous.generateRandomBarcode;

/**
 *
 * @author jkear
 */
public class Invoice {

    private static Customer customer = new Customer();
    private static ArrayList<Detail> details = new ArrayList<Detail>();
    private static Image logoImage;
    private static Paragraph paragraph;
    private static Paragraph paragraph2;

    public static void createInvoice() throws FileNotFoundException, DocumentException, BadElementException, IOException {

        testData();
//        System.out.println(customer.toString());
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("INVOICE_" + Miscellaneous.generateRandomDocId() + ".pdf"));

        addLogo();

        invoiceTable();
        
        grandTotalTable();
        
        document.setMargins(36, 36, 6, 36);
        document.open();
        document.add(logoImage);
        document.add(paragraph);
        document.add(paragraph2);
        document.close();

    }

    private static void testData() {
        addData(new Detail("Tomato", generateRandomBarcode(), 3, 60.00), Miscellaneous.generateCustomerId(), Miscellaneous.generateSaleId());
        addData(new Detail("Carrot", generateRandomBarcode(), 3, 120.00), Miscellaneous.generateCustomerId(), Miscellaneous.generateSaleId());
    }

    private static void addData(Detail detail, String generateCustomerId, String generateSaleId) {
        details.add(detail);
        customer.setCustomerId(Miscellaneous.generateCustomerId());
        customer.setSaleId(Miscellaneous.generateSaleId());
        customer.setDetails(details);

    }

    private static void addLogo() throws BadElementException, IOException {

        final int logoWidth = 100;
        final int logoHeight = 100;
        final String logoPath = "jade_logo.jpg";

        logoImage = Image.getInstance(logoPath);

        logoImage.scaleToFit(logoWidth, logoHeight);

        logoImage.setAlignment(Element.ALIGN_LEFT);
    }

    private static void invoiceTable() {
        final float[] columnWidths = {5.5f, 2f, 1.5f, 1.5f, 1.5f};
        final Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
        final Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);
        double orderTotal;
        double total = 0;
        
        PdfPTable table2 = new PdfPTable(columnWidths);
        table2.setWidthPercentage(90f);
        
        insertBlankRow(table2, "", Element.ALIGN_LEFT, 5, bfBold12);

        insertHeaderCell(table2, "Item", Element.ALIGN_LEFT, 1, bfBold12);
        insertHeaderCell(table2, "Barcode", Element.ALIGN_LEFT, 1, bfBold12);
        insertHeaderCell(table2, "Quantity", Element.ALIGN_LEFT, 1, bfBold12);
        insertHeaderCell(table2, "Price R", Element.ALIGN_LEFT, 1, bfBold12);
        insertHeaderCell(table2, "Total R", Element.ALIGN_LEFT, 1, bfBold12);
        table2.setHeaderRows(1);
        //insert an empty row
        insertHeaderCell(table2, "", Element.ALIGN_LEFT, 5, bfBold12);
        
        for (int i = 0; i < details.size(); i++) {
            insertDetailCell(table2, customer.getDetails().get(i).getProductName(), Element.ALIGN_LEFT, 1, bf12);
            insertDetailCell(table2, Integer.toString(customer.getDetails().get(i).getBarCode()), Element.ALIGN_LEFT, 1, bf12);
            insertDetailCell(table2, Integer.toString(customer.getDetails().get(i).getQuantity()), Element.ALIGN_LEFT, 1, bf12);
            insertDetailCell(table2, Double.toString(customer.getDetails().get(i).getPrice()), Element.ALIGN_LEFT, 1, bf12);
            orderTotal = customer.getDetails().get(i).getPrice() * customer.getDetails().get(i).getQuantity();
            total = total + orderTotal;
            insertDetailCell(table2, Double.toString(orderTotal), Element.ALIGN_LEFT, 1, bf12);
        }
    }
    
    private static void insertHeaderCell(PdfPTable table2, String text, int align, int colspan, Font font) {
        paragraph = new Paragraph();
        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        if (text.trim().equalsIgnoreCase("")) {
            cell.setMinimumHeight(10f);
        }
        table2.addCell(cell);
        paragraph.add(table2);

    }
    
    private static void insertDetailCell(PdfPTable table2, String text, int align, int colspan, Font font) {

        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        cell.setHorizontalAlignment(align);
        
        if (text.trim().equalsIgnoreCase("")) {
            cell.setMinimumHeight(10f);
        }
        table2.addCell(cell);

    }
    
    private static void insertBlankRow(PdfPTable table2, String text, int align, int colspan, Font font) {
        paragraph = new Paragraph();
        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setBorder(Rectangle.NO_BORDER);
        if (text.trim().equalsIgnoreCase("")) {
            cell.setMinimumHeight(10f);
        }
        table2.addCell(cell);
        paragraph.add(table2);

    }

    private static void grandTotalTable() {
        final float[] columnWidths = {5.5f};
        final Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
        
        PdfPTable table3 = new PdfPTable(columnWidths);
        table3.setWidthPercentage(90f);
        
        
//        insertBlankRow(table3, "",bfBold12);
        insertGrandTotal(table3, "Grand Total",bfBold12);
        insertGrandTotal(table3, Double.toString(grandTotal()) ,bfBold12);
        table3.setHeaderRows(1);
        
    }
    
    private static void insertGrandTotal(PdfPTable table3, String text,Font font) {
        paragraph2 = new Paragraph();
        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(1);
        if (text.trim().equalsIgnoreCase("")) {
            cell.setMinimumHeight(10f);
        }
        table3.addCell(cell);
        paragraph2.add(table3);

    }

    public static double grandTotal() {
        double grandTotal = 0;
        double price = 0;
        
        for (int i = 0; i < details.size(); i++) {
             
            price += customer.getDetails().get(i).getPrice() * customer.getDetails().get(i).getQuantity();
            grandTotal = price;
        };
        
        return grandTotal;
    }

}
