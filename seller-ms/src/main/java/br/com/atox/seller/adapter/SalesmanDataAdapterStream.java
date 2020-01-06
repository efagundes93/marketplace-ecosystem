package br.com.atox.seller.adapter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import br.com.atox.seller.domain.Seller;
import br.com.atox.seller.etl.domain.SalesmanData;
import br.com.atox.seller.helper.Transformation;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;

/**
 * SalesmanDataAdapterStream
 */
@ApplicationScoped
public class SalesmanDataAdapterStream {

    @Inject
    ObjectMapper objectMapper;
    
    @Incoming("salesman-read") 
    @Outgoing("salesman-data-received")
    @Broadcast                                          
    public SalesmanData consumeAndDeserealize(KafkaMessage<String, String> message) throws Exception{
        SalesmanData seller = objectMapper.readValue(message.getPayload(), SalesmanData.class);
      return seller;

    }
        
    @Incoming("salesman-data-received") 
    @Outgoing("seller-create-requested")
    @Broadcast                                          
    public Seller convert(SalesmanData salesmanData) throws Exception{
      Seller seller = receivedDataToSeller().transform(salesmanData);
      return seller;

    }

    private Transformation<SalesmanData, Seller> receivedDataToSeller() {
      return (salesman) -> {         
        return new Seller().name(salesman.getName())
                              .legalDocumentNumber(salesman.getLegalDocumentNumber())
                              .salary(salesman.getSalary());
      };
  }
}