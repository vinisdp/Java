/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vini_
 */
public class GerarPDF {

    public static void GerarPDF(String relatorio, String path, String data) throws DocumentException, IOException {
        File dir = new File("./relatorios");
        if (!dir.exists()) {
            dir.mkdir();
        }
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("./relatorios/" + path));
            document.open();
            Font f = new Font(Font.FontFamily.COURIER, 20, Font.BOLD);
            Font fCorpo = new Font(Font.FontFamily.COURIER,12, Font.BOLD);
            document.add(new Paragraph("Relatorio " + data,f));
            document.add(new Paragraph(relatorio, fCorpo));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GerarPDF.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            document.close();
            File file = new File("./relatorios/" + path);
            Desktop.getDesktop().open(file);
        }

    }
}
