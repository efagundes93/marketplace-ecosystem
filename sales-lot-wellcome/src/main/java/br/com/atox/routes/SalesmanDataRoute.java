package br.com.atox.routes;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import br.com.atox.salesman.processor.SalesmanBeanTranslator;

/**
 * Declaração da rota pela qual os dados de vendedores deve passar.
 * SalesmanDataRoute
 */
@ApplicationScoped
public class SalesmanDataRoute  extends RouteBuilder {

    @ConfigProperty(name = "execution.kafka.url")
	public String kafkaUrl;

    @ConfigProperty(name = "execution.kafka.topic.to.salesman_data_read")
	public String kafkaTopicToSalesmanDataRead;
      
    @Inject
    SalesmanBeanTranslator salesmanProcessor;
    
    public static final String DIRECT_ROUTE_TO_SALESMAN_DATA = "direct:salesmanReceived";
    final String MONGODB_SAVE_SALESMAN_DATA = "mongodb:myDb?database=sales-ecosystem&collection=SalesmanReceivedData&operation=insert";
   
    @Override
    public void configure() throws Exception {
        from(DIRECT_ROUTE_TO_SALESMAN_DATA)
            .process(salesmanProcessor)
            .to(MONGODB_SAVE_SALESMAN_DATA)
            .setHeader(KafkaConstants.KEY, constant(UUID.randomUUID().toString())) 
            .to(buildKafkaEndpoint());
    }    

    public String buildKafkaEndpoint(){
        return  "kafka:"+kafkaTopicToSalesmanDataRead+"?brokers="+kafkaUrl;
    }
}