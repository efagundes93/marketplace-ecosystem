package br.com.atox.report.processor;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import br.com.atox.report.domain.FileReceivedReport;
import br.com.atox.report.domain.SalesmanPerformanceReport;

/**
 * Responsável pela criação dos dados que serão gravados no relatório de saida.
 * @author Emiliano Fagundes
 * ReportBuilderProcessor
 */
@ApplicationScoped
public class ReportBuilderProcessor implements Processor {
    
    
    @ConfigProperty(name = "execution.file.layout.separator")
    public String separator;
    
    @Override
    public void process(Exchange exchange) throws Exception {
            FileReceivedReport report = exchange.getIn().getBody(FileReceivedReport.class);
            Long totalCustomerRead = report.getCustomer().getTotalRecordsRead();
            long totalSalesmanRead = report.getSalesman().getTotalRecordsRead();
            Long mostExpansiveSaleId = report.getSale().getMostExpensiveSale().getSaleId();
            SalesmanPerformanceReport worseSalesmanPerformance = report.getSale().getSalemanPerformance().stream().filter(f -> f.getClassification().equals("WORSE")).findFirst().orElse(null);
            
            String reportToSaveInFile = new StringBuilder()
                                                    .append(totalCustomerRead)
                                                    .append(separator)
                                                    .append(totalSalesmanRead)
                                                    .append(separator)
                                                    .append(mostExpansiveSaleId)
                                                    .append(separator)
                                                    .append(worseSalesmanPerformance == null ? "NaN" :worseSalesmanPerformance.getSalesmanName())
                                                    .toString();

            exchange.getIn().setBody(reportToSaveInFile, String.class);
    }

    
}