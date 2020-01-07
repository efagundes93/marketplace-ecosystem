package br.com.atox.marketplace.customer.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.SseElementType;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.reactivestreams.Publisher;

import br.com.atox.marketplace.customer.domain.Customer;

/**
 * CustomerClient
 */
@RegisterRestClient(configKey="customer-api")
public interface CustomerClient {

    @GET
    @Path("/{id}")
    @Produces("application/json")
    Customer getById(@PathParam String id);

    
    @GET
    @Path("/get-by-document/{legal-document-number}")
    @Produces("application/json")
    Customer getByDocument(@PathParam String id);


    @GET
    @Path("/stream/last-created")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType("text/plain")
    Publisher<Customer> streamLastCreated();

}