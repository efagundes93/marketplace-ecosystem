package br.com.atox.sale.processor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import br.com.atox.sale.domain.Sale;
import br.com.atox.sale.domain.SaleSynthesis;

/**
 * <p>Sintetizador dos dados de venda</p>
 * @author Emiliano Fagundes
 * SynthetizeSaleProcessor
 */
@ApplicationScoped
public class SynthetizeSaleProcessor implements Processor {

    @Inject
    ObjectMapper mapper;

    @Override
    public void process(Exchange exchange) throws Exception {
        final String payload = exchange.getIn().getBody(String.class);
        Sale sale = mapper.readValue(payload, Sale.class);
        final SaleSynthesis saleSynthesis = new SaleSynthesis().saleId(sale.getSaleId())
                .salesmanName(sale.getSalesmanName()).totalPrice(Sale.calculateTotal().apply(sale));

        exchange.getIn().setBody(saleSynthesis, SaleSynthesis.class);
    }
}