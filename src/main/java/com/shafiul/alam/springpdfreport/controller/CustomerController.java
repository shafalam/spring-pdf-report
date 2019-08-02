package com.shafiul.alam.springpdfreport.controller;

import com.shafiul.alam.springpdfreport.model.Customer;
import com.shafiul.alam.springpdfreport.repository.CustomerRepository;
import com.shafiul.alam.springpdfreport.service.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/pdf")
public class CustomerController {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping(value = "customers", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> customerReport() throws IOException{
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        ByteArrayInputStream bis = PDFGenerator.customerPDFReport(customers);


        HttpHeaders headers = new HttpHeaders();
        headers.add("Customer-Disposition","inline; filename=customers.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
