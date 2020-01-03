package br.com.atox.salesman.processor;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import br.com.atox.salesman.domain.Salesman;
import br.com.atox.utils.DataUtils;

/**
 * SalesmanBeanTranslator
 */
@ApplicationScoped
public class SalesmanBeanTranslator implements Processor {

    @ConfigProperty(name = "execution.file.layout.separator")
    public String separator;
    
    @ConfigProperty(name = "execution.file.layout.salesman.columns.qty")
    public Integer salesmanColumnsQty;
    
    @Override
    public void process(final Exchange exchange) throws Exception {
        final String fileLine = exchange.getIn().getBody(String.class);
        final Salesman salesman = translate(fileLine);
        exchange.getIn().setBody(salesman, Salesman.class);
    }

    public Salesman translate(String fileLine) throws Exception {
        final String[] splittedLine = fileLine.split(separator);
        if (splittedLine != null && splittedLine.length != salesmanColumnsQty) {
            throw new Exception("Linha com layout inválido.");
        }

        final String legalDocumentNumber = DataUtils.normalizeToNumeric().apply(splittedLine[1]);
        if (StringUtils.isBlank(legalDocumentNumber)) {
            throw new Exception("Número do documento inválido");
        }

        final String name = DataUtils.normalizeToString().apply(splittedLine[2]);
        if (StringUtils.isBlank(name)) {
            throw new Exception("Nome inválido");
        }

        final String salaryString = DataUtils.normalizeToNumeric().apply(splittedLine[3]);
        if (StringUtils.isBlank(salaryString)) {
            throw new Exception("Salário inválido");
        }

        final Salesman salesman = new Salesman().legalDocumentNumber(legalDocumentNumber)
                                          .name(name)
                                          .salary(Long.valueOf(salaryString));
        return salesman;
    }
}