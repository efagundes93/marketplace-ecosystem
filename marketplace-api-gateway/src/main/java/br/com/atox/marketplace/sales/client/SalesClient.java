package br.com.atox.marketplace.sales.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.SseElementType;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.reactivestreams.Publisher;

import br.com.atox.marketplace.sales.domain.Sale;

/**
 * SalesClient
 * 
 */
@RegisterRestClient(configKey="sales-api")
public interface SalesClient {

    @GET
    @Path("/{id}")
    @Produces("application/json")
    Sale getById(@PathParam String id);


    @GET
    @Path("/stream/last-created")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType("text/plain")
    Publisher<Sale> streamLastCreated();
    
}