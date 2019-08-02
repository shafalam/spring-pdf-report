package com.shafiul.alam.springpdfreport.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.shafiul.alam.springpdfreport.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

public class PDFGenerator {
    public static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);

    public static ByteArrayInputStream customerPDFReport(List<Customer> customers){
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            // creating a link between document and the ByteArrayOutputStream
            PdfWriter.getInstance(document, out);
            document.open();

            // Add Text to PDF file ->
            Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            Paragraph para = new Paragraph( "Customer Table", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(3);

            //Adding pdf table header
            Stream.of("ID","First Name", "Last Name").forEach(headerTitle -> {
                        PdfPCell header = new PdfPCell();
                        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                        header.setBackgroundColor(BaseColor.GRAY);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(headerTitle,headerFont));
                        table.addCell(header);
                    }
                    );

            for (Customer customer:customers){
                PdfPCell idCell = new PdfPCell(new Phrase(customer.getId().toString()));
                table.addCell(idCell);

                PdfPCell firstNameCell = new PdfPCell(new Phrase(customer.getFirstName().toString()));
                table.addCell(firstNameCell);

                PdfPCell lastNameCell = new PdfPCell(new Phrase(customer.getLastName().toString()));
                table.addCell(lastNameCell);
            }

            document.add(table);
            document.close();
        }catch (DocumentException de){
            logger.error(de.toString());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
