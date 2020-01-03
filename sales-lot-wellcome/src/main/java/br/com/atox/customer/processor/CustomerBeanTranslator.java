package br.com.atox.customer.processor;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import br.com.atox.customer.domain.Customer;
import br.com.atox.utils.DataUtils;


/**
 * <p><Translator responsável por converter a String da linha lida
 * em um POJO customer./p>
 * @author Emiliano Fagundes
 * @since 2020-01
 * CustomerProcessor
 */
@ApplicationScoped
public class CustomerBeanTranslator implements Processor {
    
    @ConfigProperty(name = "execution.file.layout.separator")
    public String separator;
    
    @ConfigProperty(name = "execution.file.layout.customer.columns.qty")
    public Integer customerColumnsQty;
    
    @Override
    public void process(Exchange exchange) throws Exception {
        final String fileLine = exchange.getIn().getBody(String.class);
        final Customer customer = this.translate(fileLine);
        exchange.getIn().setBody(customer, Customer.class);

    }

    public Customer translate(String fileLine) throws Exception{

        final String[] splittedLine = fileLine.split(separator);
        if (splittedLine != null && splittedLine.length != customerColumnsQty) {
            throw new Exception("Layout da linha está em formato inválido.");
        }

        final String legalDocumentNumber = DataUtils.normalizeToNumeric().apply(splittedLine[1]);
        if (StringUtils.isBlank(legalDocumentNumber)) {
            throw new Exception("Documento inválido");
        }

        final String name = DataUtils.normalizeToString().apply(splittedLine[2]);
        if (StringUtils.isBlank(name)) {
            throw new Exception("Nome inválido");
        }

        final String businessArea = DataUtils.normalizeToString().apply(splittedLine[3]);
        if (StringUtils.isBlank(businessArea)) {
            throw new Exception("Area de atuação invalida");
        }
        
        Customer customer = new Customer()
                                .legalDocumentNumber(legalDocumentNumber)
                                .name(name)
                                .businessArea(businessArea);
                  
        return customer;
    }

    
}