package br.com.atox.marketplace.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

import br.com.atox.marketplace.sales.client.SalesClient;
import br.com.atox.marketplace.sales.domain.Sale;

/**
 * SalesResource
 */
@Path("/sales")
public class SalesResource {

    @Inject
    SalesClient salesClient;


    @GET
    @Path("/stream/last-created")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType("text/plain")
    Publisher<Sale> streamLastCreated(){
        return salesClient.streamLastCreated();
    }
    
}