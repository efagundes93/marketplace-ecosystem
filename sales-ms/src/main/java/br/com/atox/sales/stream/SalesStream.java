package br.com.atox.sales.stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.atox.sales.domain.Sale;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;

/**
 * SalesStream
 */
@ApplicationScoped
public class SalesStream {

    private static final Logger log = LoggerFactory.getLogger(SalesStream.class);

    @Inject
    ObjectMapper objectMapper;
    
    @Incoming("sale-read") 
    @Outgoing("sales-stream")
    @Broadcast                                          
    public String consume(KafkaMessage<String, String> message) throws Exception{
      Sale sale = objectMapper.readValue(message.getPayload(), Sale.class);
      return sale.getSaleId().toString();

    }

}
