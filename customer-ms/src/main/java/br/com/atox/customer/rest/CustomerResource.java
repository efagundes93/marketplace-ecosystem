package br.com.atox.customer.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

import br.com.atox.customer.domain.Customer;
import io.smallrye.reactive.messaging.annotations.Channel;

@Path("/customer")
public class CustomerResource {

    @Channel("customer-created") Publisher<Customer> customer;

    @GET
    @Path("/stream/last-created")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType("text/plain")
    public Publisher<Customer> stream() {
        return customer;
    }
}