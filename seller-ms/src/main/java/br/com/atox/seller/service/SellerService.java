package br.com.atox.seller.service;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import br.com.atox.seller.domain.Seller;
import io.smallrye.reactive.messaging.annotations.Broadcast;

/**
 * SellerService
 */
@ApplicationScoped
public class SellerService {

       
    @Incoming("seller-create-requested") 
    @Outgoing("seller-created")
    @Broadcast                                          
    public Seller create(Seller seller) throws Exception{
      //TODO validate and save data in mongoDb.
      return seller;
      
    }
}