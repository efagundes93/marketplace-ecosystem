package br.com.atox.sale.processor;

import java.util.ArrayList;
import java.util.Arrays;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import br.com.atox.sale.domain.Sale;
import br.com.atox.sale.domain.SaleItem;
import br.com.atox.utils.DataUtils;

/**
 * Translator responsável por converter String de linha lida do arquivo emm um
 * POJO do tipo Sale SaleProcessor
 */
@ApplicationScoped
public class SaleBeanTranslator implements Processor {

    @ConfigProperty(name = "execution.file.layout.sales.columns.qty")
    Integer salesColumnQty;

    @ConfigProperty(name = "execution.file.layout.separator")
    String separator;

    @Override
    public void process(Exchange exchange) throws Exception {

        final String fileLine = exchange.getIn().getBody(String.class);
        final Sale sale = this.translate(fileLine);
        exchange.getIn().setBody(sale, Sale.class);

    }

    public Sale translate(String fileLine) throws Exception {

        final String[] splittedLine = fileLine.split(separator);
        if (splittedLine != null && splittedLine.length != salesColumnQty) {
            throw new Exception("Linha com layout inválido");
        }

        final String saleId = DataUtils.normalizeToNumeric().apply(splittedLine[1]);
        if (StringUtils.isBlank(saleId)) {
            throw new Exception("Sale_id inválido");
        }
        final String salesmanName = DataUtils.normalizeToString().apply(splittedLine[3]);
        if (StringUtils.isBlank(salesmanName)) {
            throw new Exception("salesman_name inválido");
        }

        Sale sale = new Sale().saleId(Long.valueOf(saleId)).salesmanName(salesmanName).items(new ArrayList<SaleItem>());

        String saleItems = splittedLine[2];
        saleItems = saleItems.replace("[", "").replace("]", "");
        Arrays.asList(saleItems.split(",")).stream().forEach(saleItemStr -> {
            if (StringUtils.isBlank(saleItemStr)){
                return;
            }
            final String[] saleAttribute = saleItemStr.split("-");
            if (saleAttribute.length < 3) {
                return;
            }
            final String saleIdStr = DataUtils.normalizeToNumeric().apply(saleAttribute[0]);
            final String quantityStr = DataUtils.normalizeToNumeric().apply(saleAttribute[1]);
            final String priceStr = DataUtils.normalizeToNumeric().apply(saleAttribute[2]);

            final SaleItem saleItem = new SaleItem().id(Long.valueOf(saleIdStr))
                                                    .price(Long.valueOf(priceStr))
                                                    .quantity(Long.valueOf(quantityStr));
            sale.getItems().add(saleItem);
        });

        return sale;
    }
}