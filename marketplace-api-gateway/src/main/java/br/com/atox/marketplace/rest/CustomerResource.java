package br.com.atox.marketplace.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

import br.com.atox.marketplace.customer.client.CustomerClient;
import br.com.atox.marketplace.customer.domain.Customer;

/**
 * CustomerResource
 */
@Path("/customer")
public class CustomerResource {

    @Inject
    CustomerClient customerClient;

    @GET
    @Path("/stream/last-created")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType("text/plain")
    Publisher<Customer> streamLastCreated(){
        return customerClient.streamLastCreated();
    }

}