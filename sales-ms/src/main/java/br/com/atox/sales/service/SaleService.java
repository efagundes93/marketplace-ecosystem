package br.com.atox.sales.service;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import br.com.atox.sales.domain.Sale;
import io.smallrye.reactive.messaging.annotations.Broadcast;

/**
 * SaleService
 */
@ApplicationScoped
public class SaleService {

    
    @Incoming("sale-create-requested") 
    @Outgoing("sale-created")
    @Broadcast                                          
    public Sale create(Sale sale) throws Exception{
      //TODO validate and save data in mongoDb.
      return sale;

    }

}