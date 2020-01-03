package main.java.br.com.atox.stream;

import br.com.atox.sale.domain.Sale;

/**
 * SalesStream
 */
@ApplicationScoped
public class SalesStream {

    @Incoming("sale-data-read")                                 
    @Outgoing("sales-stream")                         
    @Broadcast  
    public Sale process(String saleJson) {
      
        return new Sale();
    }
    
}