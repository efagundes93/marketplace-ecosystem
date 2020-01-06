package br.com.atox.sales.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

import br.com.atox.sales.domain.Sale;
import io.smallrye.reactive.messaging.annotations.Channel;

@Path("/sales")
public class SalesResource{

    @Channel("sale-created") Publisher<Sale> sale;

    @GET
    @Path("/stream/last-created-id")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType("text/plain")
    public Publisher<Sale> stream() {
        return sale;
    }
}