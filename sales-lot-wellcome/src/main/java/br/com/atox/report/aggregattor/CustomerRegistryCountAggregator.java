package br.com.atox.report.aggregattor;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

import br.com.atox.report.domain.CustomerDataReport;
import br.com.atox.report.domain.FileReceivedReport;

/**
 * Agregador responsável por enriquecer no relatório os dados
 * de cliente.
 * CustomerRegistryCountAggregator
 */
public class CustomerRegistryCountAggregator implements AggregationStrategy {

    
    @Override
    public Exchange aggregate(Exchange original, Exchange resource) {
        FileReceivedReport report = original.getIn().getBody(FileReceivedReport.class);
        Long totalReaded = resource.getIn().getBody(Long.class);
       if(report.getCustomer() == null ) 
            report.setCustomer(new CustomerDataReport());

        report.getCustomer().setTotalRecordsRead(totalReaded);
        original.getIn().setBody(report, FileReceivedReport.class);
        return original;
    }
    
}