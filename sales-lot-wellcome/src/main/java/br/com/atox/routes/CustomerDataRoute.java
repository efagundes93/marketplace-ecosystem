package br.com.atox.routes;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import br.com.atox.customer.processor.CustomerBeanTranslator;

/**
 *  Declaração da rota pela qual os dados de cliente devem passar
 * CustomerDataRoute
 */
@ApplicationScoped
public class CustomerDataRoute extends RouteBuilder {

    @ConfigProperty(name = "execution.kafka.url")
    public String kafkaUrl;
    
    @ConfigProperty(name = "execution.kafka.topic.to.customer_data_read")
	public String kafkaTopicToCustomerDataRead;

    @Inject
    CustomerBeanTranslator customerProcessor;

    
    public static final String DIRECT_ROUTE_TO_CUSTOMER_DATA = "direct:customerReceived";
    final String MONGODB_SAVE_CUSTOMER_DATA = "mongodb:myDb?database=sales-ecosystem&collection=CustomerReceivedData&operation=insert";

    @Override
    public void configure() throws Exception {
       from(DIRECT_ROUTE_TO_CUSTOMER_DATA)
        .process(customerProcessor)
            .to(MONGODB_SAVE_CUSTOMER_DATA)
                .setHeader(KafkaConstants.KEY, constant(UUID.randomUUID().toString())) 
                .marshal().json()
                    .to(buildKafkaEndpoint());
    }

    public String buildKafkaEndpoint(){
        return  "kafka:"+kafkaTopicToCustomerDataRead+"?brokers="+kafkaUrl;
    }
}