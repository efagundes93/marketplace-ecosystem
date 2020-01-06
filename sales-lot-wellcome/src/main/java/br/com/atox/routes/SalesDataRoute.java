package br.com.atox.routes;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import br.com.atox.sale.aggregator.SaleSynthesisAggregatorStrategy;
import br.com.atox.sale.processor.SaleBeanTranslator;
import br.com.atox.sale.processor.SynthetizeSaleProcessor;


/**
 * <p> Declaração da rota que os dados de vendas devem seguir. </p>
 * @author Emiliano Fagundes
 * @since 2020-01-03
 * 
 * SalesDataRoute
 */
@ApplicationScoped
public class SalesDataRoute extends RouteBuilder {

    @Inject
    @ConfigProperty(name = "execution.kafka.url")
	String kafkaUrl;
  
    @Inject
    @ConfigProperty(name = "execution.kafka.topic.to.sale_data_read")
    String kafkaTopicToSaleDataRead;

    @Inject
    @ConfigProperty(name = "execution.sales_synthesis.chunk_size")
    Integer saleSynthesisChunkSize;
    
    @Inject
    SaleBeanTranslator saleProcessor;
    
    @Inject
    SynthetizeSaleProcessor synthetizeSaleProcessor;
 
    
    
    public static final String DIRECT_ROUTE_TO_SALES_DATA = "direct:onSaleReaded";

	String MONGODB_BULK_WRITE_SALE_SYNTHESIS = "mongodb:myDb?database=sales-ecosystem&collection=SaleSynthesis&operation=bulkWrite";


    @Override
    public void configure() throws Exception {
       
        from(DIRECT_ROUTE_TO_SALES_DATA)
            .process(saleProcessor)
            .marshal().json()
        .setHeader(KafkaConstants.KEY, constant(UUID.randomUUID().toString())) 
        .to(buildKafkaEndpoint())
        .process(synthetizeSaleProcessor)
            .aggregate(constant(true), new SaleSynthesisAggregatorStrategy())
            .completionSize(saleSynthesisChunkSize)
        .to(MONGODB_BULK_WRITE_SALE_SYNTHESIS);
    }  

    
    public String buildKafkaEndpoint(){
        return  "kafka:"+kafkaTopicToSaleDataRead+"?brokers="+kafkaUrl;
    }
}