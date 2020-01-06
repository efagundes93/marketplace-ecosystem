package br.com.atox.seller.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

import br.com.atox.seller.domain.Seller;
import io.smallrye.reactive.messaging.annotations.Channel;

@Path("/seller")
public class SellerResource {

    @Channel("seller-created") Publisher<Seller> seller;

    @GET
    @Path("/stream/last-created")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType("text/plain")
    public Publisher<Seller> stream() {
        return seller;
    }
}