package com.shafiul.alam.springpdfreport;

import com.shafiul.alam.springpdfreport.model.Customer;
import com.shafiul.alam.springpdfreport.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringPdfReportApplication implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringPdfReportApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(customerRepository.count() == 0){
            customerRepository.saveAll(Arrays.asList(
                    new Customer("Shaf", "Alam"),
                    new Customer("Henry", "Hagards"),
                    new Customer("Jules", "Vernes"),
                    new Customer("Arthur", "Clark")
            ));
        }
    }
}
