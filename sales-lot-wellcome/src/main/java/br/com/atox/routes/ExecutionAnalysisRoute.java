package br.com.atox.routes;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import br.com.atox.configuration.SystemConfiguration;
import br.com.atox.report.aggregattor.CustomerRegistryCountAggregator;
import br.com.atox.report.aggregattor.MostExpensiveSaleAggregator;
import br.com.atox.report.aggregattor.SalesmanCountAggregator;
import br.com.atox.report.aggregattor.WorseSalesmanAmountAggregationStrategy;
import br.com.atox.report.aggregattor.WorseSalesmanQuantityAggregationStrategy;
import br.com.atox.report.domain.FileReceivedReport;
import br.com.atox.report.processor.DatabaseCleaner;
import br.com.atox.report.processor.ReportBuilderProcessor;

/**
 * Rota pela qual os relatórios de execução serão gerados.
 * ExecutionAnalysisRoute
 */
@ApplicationScoped
public class ExecutionAnalysisRoute extends RouteBuilder {
    
    @ConfigProperty(name = "execution.file.output_subdirectory")
    public String outputSubdir;

    @Inject
    ReportBuilderProcessor reportBuilderProcessor;

    @Inject
    DatabaseCleaner databaseCleaner;

    public static final String ROUTE_TO_EXECUTION_ANALISIS = "direct:onFileProcessed";
    final String MONGODB_COUNT_CUSTOMER = "mongodb:myDb?database=sales-ecosystem&collection=CustomerReceivedData&operation=count";
    final String MONGODB_COUNT_SALESMAN = "mongodb:myDb?database=sales-ecosystem&collection=SalesmanReceivedData&operation=count"; 


    @Override
    public void configure() throws Exception {

        from(ROUTE_TO_EXECUTION_ANALISIS)
           .setBody(constant(new FileReceivedReport()))
           .pipeline()
                .enrich(MONGODB_COUNT_CUSTOMER,
                        new CustomerRegistryCountAggregator())
                .enrich(MONGODB_COUNT_SALESMAN,
                        new SalesmanCountAggregator())
                .enrich(FindMostExpensiveSaleRoute.ROUTE_TO_FIND_MOST_EXPENSIVE_SALE, new MostExpensiveSaleAggregator())
                .enrich(FindWorseSalesmanByAmountRoute.ROUTE_TO_FIND_WORSE_SALESMAN_BY_AMOUNT,
                        new WorseSalesmanAmountAggregationStrategy())
                .enrich(FindWorseSalesmanByQuantityRoute.ROUTE_TO_FIND_WORSE_SALESMAN_BY_QUANTITY,
                        new WorseSalesmanQuantityAggregationStrategy())
                .marshal()
                .json(JsonLibrary.Jackson, FileReceivedReport.class)
                .to(buildFileOutputEndpointToAnalyticalReport())
                        .unmarshal().json(JsonLibrary.Jackson, FileReceivedReport.class)
                        .process(reportBuilderProcessor)
                        .to(buildFileOutputEndpointToRequiredReport())
                         .process(databaseCleaner);
    }   

    private String buildOutputPath(){
            return SystemConfiguration.HOME_PATH + outputSubdir ;
    }

    private String buildFileOutputEndpointToAnalyticalReport(){
        return  "file://"+buildOutputPath() +  "?fileName=${file:name.noext}-ANALYTICAL-REPORT.json";
    }

    
    private String buildFileOutputEndpointToRequiredReport(){
        return  "file://"+buildOutputPath() +  "?fileName=${file:name.noext}.done.dat";
    }
}