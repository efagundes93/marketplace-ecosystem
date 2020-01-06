package br.com.atox.customer.service;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import br.com.atox.customer.domain.Customer;
import io.smallrye.reactive.messaging.annotations.Broadcast;

/**
 * CustomerService
 */
@ApplicationScoped
public class CustomerService {

    @Incoming("customer-create-requested") 
    @Outgoing("customer-created")
    @Broadcast                                          
    public Customer create(Customer customer) throws Exception{
      //TODO validate and save data in mongoDb.
      return customer;

    }

    
}