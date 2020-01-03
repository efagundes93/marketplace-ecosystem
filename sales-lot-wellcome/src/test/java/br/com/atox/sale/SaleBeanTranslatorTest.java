package br.com.atox.sale;

import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.atox.sale.domain.Sale;
import br.com.atox.sale.domain.SaleItem;
import br.com.atox.sale.processor.SaleBeanTranslator;
import io.quarkus.test.junit.QuarkusTest;

/**
 * SaleBeanTranslatorTest
 */
@QuarkusTest
public class SaleBeanTranslatorTest {

 
    @ConfigProperty(name = "execution.file.layout.separator")
    String separator;

    @Inject
    SaleBeanTranslator translator;

    @Test
    public void translateOkTest() throws Exception {
        
        String saleId = RandomStringUtils.randomNumeric(10);
        String itemId = "0";
        String itemQuantity = RandomStringUtils.randomNumeric(1);
        String itemPrice = RandomStringUtils.randomNumeric(3);
        String salesmanName = RandomStringUtils.randomAlphabetic(10);
        final String receivedLine = new StringBuilder()
                                            .append("001")
                                            .append(separator)
                                            .append(saleId)
                                            .append(separator)
                                            .append("[")
                                            .append(itemId)
                                            .append("-")
                                            .append(itemQuantity)
                                            .append("-")
                                            .append(itemPrice)
                                            .append("]")
                                            .append(separator)
                                            .append(salesmanName)
                                            .toString();

        final Sale sale  = translator.translate(receivedLine);
        Assertions.assertEquals(sale.getSaleId(), Long.valueOf(saleId));
        Assertions.assertEquals(sale.getSalesmanName(), salesmanName.toUpperCase());
        Assertions.assertEquals(sale.getItems().size(), 1);

        SaleItem saleItem = sale.getItems().get(0);

        Assertions.assertEquals(saleItem.getId(), Long.valueOf(itemId));
        Assertions.assertEquals(saleItem.getPrice(), Long.valueOf(itemPrice));
        Assertions.assertEquals(saleItem.getQuantity(), Long.valueOf(itemQuantity));
       
    }

    @Test()
    public void translateExceptionBySaleIdEmpty()  {
        

        final String receivedLine = new StringBuilder()
                                            .append("001")
                                            .append(separator)
                                            .append(" ")
                                            .append(separator)
                                            .append("str")
                                            .append(separator)
                                            .append("str")
                                            .toString();

        
        Assertions.assertThrows(Exception.class, () -> translator.translate(receivedLine));        
       
    }

    @Test()
    public void translateExceptionBySaleIdNotNumeric()  {
        

        final String receivedLine = new StringBuilder()
                                            .append("001")
                                            .append(separator)
                                            .append("A")
                                            .append(separator)
                                            .append("str")
                                            .append(separator)
                                            .append("str")
                                            .toString();

        
        Assertions.assertThrows(Exception.class, () -> translator.translate(receivedLine));        
       
    }

    @Test()
    public void translateExceptionBySalesmanEmpty()  {
        

        final String receivedLine = new StringBuilder()
                                            .append("001")
                                            .append(separator)
                                            .append("1")
                                            .append(separator)
                                            .append("[]")
                                            .append(separator)
                                            .append("")
                                            .toString();

        
        Assertions.assertThrows(Exception.class, () -> translator.translate(receivedLine));        
       
    }


    @Test()
    public void translateExceptionByColumnSize()  {
        

        final String receivedLine = new StringBuilder()
                                            .append("001")
                                            .append(separator)
                                            .append("str")
                                            .append(separator)
                                            .append("str")
                                            .append(separator)
                                            .append("str")
                                            .append(separator)
                                            .append("str")
                                            .toString();

        
        Assertions.assertThrows(Exception.class, () -> translator.translate(receivedLine));        
       
    }

}