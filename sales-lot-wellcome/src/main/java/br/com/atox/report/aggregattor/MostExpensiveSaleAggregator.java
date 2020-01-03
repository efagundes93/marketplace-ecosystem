package br.com.atox.report.aggregattor;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.bson.Document;

import br.com.atox.report.domain.FileReceivedReport;
import br.com.atox.report.domain.SalesDataReport;
import br.com.atox.sale.domain.SaleSynthesis;

/**
 * Agregador responsável por enriquecer no relatporio os dados 
 * da venda mais cara.
 * MostExpensiveSaleAggregator
 */
public class MostExpensiveSaleAggregator implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange original, Exchange resource) {
        FileReceivedReport report = original.getIn().getBody(FileReceivedReport.class);
        Document[] resourceResponse = resource.getIn().getBody(Document[].class);
        if(null == resourceResponse || resourceResponse.length == 0){
            return original;
        }
        Document responseDocument = resourceResponse[0];
     
        if(report.getSale() == null ) 
            report.setSale(new SalesDataReport());
        report.getSale().setMostExpensiveSale(new SaleSynthesis()
                                                        .saleId(Long.valueOf(responseDocument.getInteger("saleId")))
                                                        .salesmanName(responseDocument.getString("salesmanName"))
                                                        .totalPrice(Long.valueOf(responseDocument.getInteger("totalPrice")))

        );
        original.getIn().setBody(report, FileReceivedReport.class);
        return original;
    }

    

    
}