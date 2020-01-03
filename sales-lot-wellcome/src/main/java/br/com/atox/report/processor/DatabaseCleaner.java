package br.com.atox.report.processor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import br.com.atox.customer.repository.CustomerDataRepository;
import br.com.atox.sale.repository.SaleSynthesisRepository;
import br.com.atox.salesman.repository.SalesmanDataRepository;

/**
 * Responsável por limpar as bases de dados e deixa-las aptas 
 * a próxima execução.
 * DatabaseCleaner
 */
@ApplicationScoped
public class DatabaseCleaner implements Processor {

    @Inject
    SaleSynthesisRepository saleSynthesisRepository;

    @Inject
    CustomerDataRepository customerDataRepository;

    @Inject
    SalesmanDataRepository salesmanDataRepository;


    @Override
    public void process(Exchange exchange) throws Exception {
        saleSynthesisRepository.deleteAll();
        customerDataRepository.deleteAll();
        salesmanDataRepository.deleteAll();

    }

    
}