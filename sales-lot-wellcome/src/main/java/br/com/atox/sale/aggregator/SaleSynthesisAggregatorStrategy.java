package br.com.atox.sale.aggregator;

import java.util.ArrayList;

import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.WriteModel;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.bson.Document;

import br.com.atox.sale.domain.SaleSynthesis;

/**
 * <p>Aggregador que converte os POJOs em documentos do MongoDB
 * para insersão através de Bulk</p>
 * @see {{https://docs.mongodb.com/manual/reference/method/js-bulk/}}
 * @author Emiliano Fagundes
 * SalesAggregatorStrategy
 */
public class SaleSynthesisAggregatorStrategy  implements AggregationStrategy {

    @Override
    public Exchange aggregate(final Exchange oldExchange, final Exchange newExchange) {
        final SaleSynthesis newBody = newExchange.getIn().getBody(SaleSynthesis.class);
        ArrayList<WriteModel<Document>> list = null;
        if (oldExchange == null) {
            list = new ArrayList<WriteModel<Document>>();
            list.add( new InsertOneModel<>( Document.parse(newBody.toString())));
            newExchange.getIn().setBody(list, ArrayList.class );
            return newExchange;
        } else {
            list = oldExchange.getIn().getBody(ArrayList.class);
            list.add( new InsertOneModel<>(Document.parse(newBody.toString())));
            return oldExchange;
        }
    }
}