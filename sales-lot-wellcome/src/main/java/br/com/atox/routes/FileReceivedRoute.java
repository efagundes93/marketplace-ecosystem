package br.com.atox.routes;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import br.com.atox.configuration.SystemConfiguration;
/**
 *  Declaração da rota macro pela qual os dados do
 * 	arquivo devem ser processados.
 *  @author Emiliano Fagundes
 */
@ApplicationScoped
public class FileReceivedRoute extends RouteBuilder {
	
	@ConfigProperty(name = "execution.file.input_subdirectory")
	public String inputSubdir;

	@ConfigProperty(name = "execution.file.error_subdirectory")
	public String errorSubdir;
	
	@ConfigProperty(name = "execution.file.layout.new_line")
	public String newLine;

	@ConfigProperty(name = "execution.file.layout.salesman.id")
	public String salesmanLayoutId;
	
	@ConfigProperty(name = "execution.file.layout.customer.id")
	public String customerLayoutId;
	
	@ConfigProperty(name = "execution.file.layout.sales.id")
	public String saleLayoutId;

	@Override
	public void configure() throws Exception {
		
		from(buildFileReceivedEndpoint())
			.log("Arquivo recebido!")
			.onCompletion()
				.log("Processamento finalizado!")
				.to(ExecutionAnalysisRoute.ROUTE_TO_EXECUTION_ANALISIS)
				.end()
			.split()
			.tokenize(newLine)
			.streaming()
			.parallelProcessing()
			.choice()
				.when(body().startsWith(salesmanLayoutId))
					.to(SalesmanDataRoute.DIRECT_ROUTE_TO_SALESMAN_DATA)
				.when(body().startsWith(customerLayoutId))
					.to(CustomerDataRoute.DIRECT_ROUTE_TO_CUSTOMER_DATA)
				.when(body().startsWith(saleLayoutId))	
					.to(SalesDataRoute.DIRECT_ROUTE_TO_SALES_DATA)
				.otherwise()
					.to(buildFileErrorOutputEndpoint());
	}

	public String buildInputPath(){
		return SystemConfiguration.HOME_PATH + inputSubdir;
	}
		
	public String buildErrorPath(){
		return SystemConfiguration.HOME_PATH + errorSubdir;
	}

	private String buildFileReceivedEndpoint(){
		return "file://"+buildInputPath()+"?antInclude=**/*.dat&charset=utf-8&move=processed";
	}

	private String buildFileErrorOutputEndpoint(){
		return "file://"+ buildErrorPath() + "?fileName=${file:name.noext}UNRECOGNIZED.dat&fileExist=Append";
	}
}
