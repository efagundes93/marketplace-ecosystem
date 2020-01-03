package br.com.atox.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/sales")
public class SalesResource {

    @Inject
    @Channel("sales-stream") Publisher<Sale> sales; 

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType("text/plain")
    public Publisher<sale> stream() {
        return sales;
    }
}