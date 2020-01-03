package br.com.atox.report.aggregattor;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

import br.com.atox.report.domain.FileReceivedReport;
import br.com.atox.report.domain.SalesmanDataReport;

/**
 * Agregador responsável por enriquecer no relatório os dados de 
 * vendedores.
 * SalesmanCountAggregator
 */
public class SalesmanCountAggregator implements AggregationStrategy {

    
    @Override
    public Exchange aggregate(Exchange original, Exchange resource) {
        FileReceivedReport report = original.getIn().getBody(FileReceivedReport.class);
        Long totalReaded = resource.getIn().getBody(Long.class);
       if(report.getSalesman() == null ) 
            report.setSalesman(new SalesmanDataReport());

        report.getSalesman().setTotalRecordsRead(totalReaded);
        original.getIn().setBody(report, FileReceivedReport.class);
        return original;
    }   
    
}