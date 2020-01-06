package br.com.atox.sales.adapter;

import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import br.com.atox.sales.domain.Sale;
import br.com.atox.sales.domain.SaleItem;
import br.com.atox.sales.etl.domain.SaleData;
import br.com.atox.sales.helper.Transformation;
import br.com.atox.sales.salesman.domain.Salesman;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;

/**
 * SalesStream
 */
@ApplicationScoped
public class SalesDataAdapterStream {

    @Inject
    ObjectMapper objectMapper;
    
    @Incoming("sale-read") 
    @Outgoing("sale-data-received")
    @Broadcast                                          
    public SaleData consumeAndDeserealize(KafkaMessage<String, String> message) throws Exception{
      SaleData sale = objectMapper.readValue(message.getPayload(), SaleData.class);
      return sale;

    }
        
    @Incoming("sale-data-received") 
    @Outgoing("sale-create-requested")
    @Broadcast                                          
    public Sale convert(SaleData saleData) throws Exception{
      Sale sale = receivedDataToSale().transform(saleData);
      return sale;

    }

    private Transformation<SaleData, Sale> receivedDataToSale() {
      return (saleData) -> {
          Sale sale = new Sale().id(saleData.getSaleId())
                                .saleOrigin("EXTERNAL")
                                .seller( new Salesman().name(saleData.getSalesmanName()));

          if (null == saleData.getItems() || saleData.getItems().isEmpty()) {
              return sale;
          }
          sale.items(new ArrayList<SaleItem>());
          saleData.getItems().stream()
                             .forEach(saleItemData -> {
                                  sale.getItems().add( new SaleItem().itemSequenceNumber(saleItemData.getId())
                                                                      .price(saleItemData.getPrice())
                                                                      .quantity(saleItemData.getQuantity()));
                             });
          return sale;
      };
  }
}
