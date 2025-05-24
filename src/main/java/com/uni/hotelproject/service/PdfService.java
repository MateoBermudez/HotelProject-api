package com.uni.hotelproject.service;

import com.uni.hotelproject.dto.BookingDTO;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public byte[] generateBookingPdf(BookingDTO booking) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Booking Details"));
            document.add(new Paragraph("Booking ID: " + booking.getId()));
            document.add(new Paragraph("Customer Name: " + booking.getCustomerName()));
            document.add(new Paragraph("Room Number: " + booking.getRoom().getRoomNumber()));
            document.add(new Paragraph("Start Date: " + booking.getStartDate()));
            document.add(new Paragraph("Confirmed: " + booking.isConfirmed()));
            document.add(new Paragraph("End Date: " + booking.getEndDate()));
            document.add(new Paragraph("Total Price: $" + booking.getTotalPrice()));

            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}