package br.com.atox.customer.adapter;

import java.util.ArrayList;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import br.com.atox.customer.domain.Customer;
import br.com.atox.customer.etl.domain.CustomerData;
import br.com.atox.customer.helper.Transformation;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;

/**
 * CustomerDataAdapterStream
 */
public class CustomerDataAdapterStream {

    @Inject
    ObjectMapper objectMapper;
    
    @Incoming("customer-read") 
    @Outgoing("customer-data-received")
    @Broadcast                                          
    public CustomerData consumeAndDeserealize(KafkaMessage<String, String> message) throws Exception{
        CustomerData customer = objectMapper.readValue(message.getPayload(), CustomerData.class);
      return customer;

    }
        
    @Incoming("customer-data-received") 
    @Outgoing("customer-create-requested")
    @Broadcast                                          
    public Customer convert(CustomerData customerData) throws Exception{
      Customer customer = receivedDataToCustomer().transform(customerData);
      return customer;

    }

    private Transformation<CustomerData, Customer> receivedDataToCustomer() {
      return (customerData) -> {         
        return new Customer().name(customerData.getName())
                              .legalDocumentNumber(customerData.getLegalDocumentNumber())
                               .businessArea(customerData.getBusinessArea());
      };
  }
}