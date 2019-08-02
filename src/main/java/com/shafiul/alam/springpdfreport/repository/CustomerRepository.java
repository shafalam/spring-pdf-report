package com.shafiul.alam.springpdfreport.repository;

import com.shafiul.alam.springpdfreport.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
