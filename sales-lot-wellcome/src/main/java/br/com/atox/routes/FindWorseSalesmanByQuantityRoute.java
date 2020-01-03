package br.com.atox.routes;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import org.bson.Document;

/**
 * FindWorseSalesmanByQuantityRoute
 */
@ApplicationScoped
public class FindWorseSalesmanByQuantityRoute extends RouteBuilder{

    public static final String ROUTE_TO_FIND_WORSE_SALESMAN_BY_QUANTITY = "direct:onWorseSalesmanQuantityResquested";
    private final String MONGODB_FIND_WORSE_SALESMAN_BY_QUANTITY = "mongodb:myDb?database=sales-ecosystem&collection=SaleSynthesis&operation=aggregate";
    @Override
    public void configure() throws Exception {
        List<Document> aggregate = Arrays.asList(
          new Document("$group", new Document("_id", "$salesmanName")
                                        .append("totalAmount", new Document("$sum", "$totalPrice"))
                                        .append("totalOfSales", new Document("$sum", 1))),
          new Document("$sort", new Document("totalOfSales", 1)),
          new Document("$limit", 1)
        );
        
        from(ROUTE_TO_FIND_WORSE_SALESMAN_BY_QUANTITY)
            .setBody().constant(aggregate)
            .to(MONGODB_FIND_WORSE_SALESMAN_BY_QUANTITY);
    }

    
}