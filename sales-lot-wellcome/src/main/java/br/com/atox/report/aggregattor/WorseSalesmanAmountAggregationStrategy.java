package br.com.atox.report.aggregattor;

import java.util.ArrayList;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.bson.Document;

import br.com.atox.report.domain.FileReceivedReport;
import br.com.atox.report.domain.SalesDataReport;
import br.com.atox.report.domain.SalesmanPerformanceReport;

/**
 * Agregador responsável por enriquecer no relatório o
 * vendedor com menor montante vendido.
 * WrostSalesmanAmountAggregationStrategy
 */
public class WorseSalesmanAmountAggregationStrategy  implements AggregationStrategy {

    public Exchange aggregate(Exchange original, Exchange resource) {
        FileReceivedReport report = original.getIn().getBody(FileReceivedReport.class);
        Document[] resourceResponse = resource.getIn().getBody(Document[].class);
        Document responseDocument = resourceResponse[0];
        SalesmanPerformanceReport salesmanReport = new SalesmanPerformanceReport()
                                                            .classification("WORSE")
                                                            .criteria("SALES_AMOUNT")
                                                            .salesmanName(responseDocument.getString("_id"))
                                                            .totalAmount(  Long.valueOf(responseDocument.getInteger("totalAmount")))
                                                            .totalOfSales( Long.valueOf(responseDocument.getInteger("totalOfSales")));

        if(report.getSale() == null ) 
            report.setSale(new SalesDataReport());

        if(report.getSale().getSalemanPerformance() == null )
            report.getSale().setSalemanPerformance(new ArrayList<SalesmanPerformanceReport>());
        
        report.getSale().getSalemanPerformance().add(salesmanReport);
        original.getIn().setBody(report, FileReceivedReport.class);
        return original;
    }
}