package br.com.atox.routes;

import javax.enterprise.context.ApplicationScoped;

import com.mongodb.client.model.Sorts;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mongodb.MongoDbConstants;

/**
 * <p>Declaração da rota que busca da base de dados a venda com maior valor </p>
 * @author Emiliano Fagundes
 * FindMostExpensiveSaleRoute
 */
@ApplicationScoped
public class FindMostExpensiveSaleRoute extends RouteBuilder{

    public static final String ROUTE_TO_FIND_MOST_EXPENSIVE_SALE = "direct:onMostExpensiveSaleRequested";
    private final String MONGODB_FIND_MOST_EXPANSIVE_SALE = "mongodb:myDb?database=sales-ecosystem&collection=SaleSynthesis&operation=findAll";
    
    @Override
    public void configure() throws Exception {
        
        from(ROUTE_TO_FIND_MOST_EXPENSIVE_SALE)
            .setHeader(MongoDbConstants.SORT_BY).constant(Sorts.descending("totalPrice"))
            .setHeader(MongoDbConstants.LIMIT).constant(1)
            .to(MONGODB_FIND_MOST_EXPANSIVE_SALE);
         
    }
}