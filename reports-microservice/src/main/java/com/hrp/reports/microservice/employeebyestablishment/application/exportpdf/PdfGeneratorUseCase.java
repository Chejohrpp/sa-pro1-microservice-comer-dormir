package com.hrp.reports.microservice.employeebyestablishment.application.exportpdf;

import com.hrp.reports.microservice.common.annotation.UseCase;
import com.hrp.reports.microservice.employeebyestablishment.domain.Employee;
import com.hrp.reports.microservice.employeebyestablishment.infrastructure.inputports.GenerateEmployeeEstablishmentPdfInputPort;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@UseCase
public class PdfGeneratorUseCase implements GenerateEmployeeEstablishmentPdfInputPort {

    @Override
    public byte[] generateEmployeeReport(String establishment,List<Employee> employees) throws DocumentException, IOException {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();

        // Agregar título
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph title = new Paragraph("Employee Report by " + establishment, titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        document.add(new Paragraph("\n")); // Espacio entre título y tabla

        // Crear tabla con encabezados
        PdfPTable table = new PdfPTable(5); // 5 columnas
        table.setWidthPercentage(100);

        // Encabezados
        addTableHeader(table, "Username");
        addTableHeader(table, "First Name");
        addTableHeader(table, "Last Name");
        addTableHeader(table, "Email");
        addTableHeader(table, "Salary");

        // Añadir los datos de los empleados
        for (Employee employee : employees) {
            table.addCell(employee.getUsername());
            table.addCell(employee.getFirstName());
            table.addCell(employee.getLastName());
            table.addCell(employee.getEmail());
            table.addCell(String.valueOf(employee.getSalary()));
        }

        document.add(table);
        document.close();
        return baos.toByteArray();
    }

    private void addTableHeader(PdfPTable table, String headerTitle) {
        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        PdfPCell header = new PdfPCell();
        header.setPhrase(new Paragraph(headerTitle, headFont));
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(header);
    }
}
