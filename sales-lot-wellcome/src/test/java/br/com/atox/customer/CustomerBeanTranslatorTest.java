package br.com.atox.customer;

import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.atox.customer.domain.Customer;
import br.com.atox.customer.processor.CustomerBeanTranslator;
import io.quarkus.test.junit.QuarkusTest;

/**
 * CustomerBeanTranslatorTest
 */
@QuarkusTest
public class CustomerBeanTranslatorTest {

    @ConfigProperty(name = "execution.file.layout.separator")
    String separator;

    @Inject
    CustomerBeanTranslator translator;

    @Test
    public void translateOkTest() throws Exception {
        String legalDocumentNumber = RandomStringUtils.randomNumeric(11);
        String name = RandomStringUtils.randomAlphabetic(10);
        String businessArea = RandomStringUtils.randomAlphabetic(10); 
        final String receivedLine = new StringBuilder()
                                            .append("000")
                                            .append(separator)
                                            .append(legalDocumentNumber)
                                            .append(separator)                                            
                                            .append(name)                                      
                                            .append(separator)
                                            .append(businessArea)
                                            .toString();

        final Customer customer = translator.translate(receivedLine);
        Assertions.assertEquals(customer.getLegalDocumentNumber(), legalDocumentNumber);
        Assertions.assertEquals(customer.getName(), name.toUpperCase());
        Assertions.assertEquals(customer.getBusinessArea(), businessArea.toUpperCase());

    }


    @Test
    public void translateExceptionByEmptyDocument() throws Exception {
        String legalDocumentNumber = "";
        String name = RandomStringUtils.randomAlphabetic(10);
        String businessArea = RandomStringUtils.randomAlphabetic(10); 
        final String receivedLine = new StringBuilder()
                                            .append("000")
                                            .append(separator)
                                            .append(legalDocumentNumber)
                                            .append(separator)                                            
                                            .append(name)                                      
                                            .append(separator)
                                            .append(businessArea)
                                            .toString();

        Assertions.assertThrows(Exception.class, () -> translator.translate(receivedLine));

    }

    @Test
    public void translateExceptionByEmptyName() throws Exception {
        String legalDocumentNumber = RandomStringUtils.randomNumeric(11);
        String name = "";
        String businessArea = RandomStringUtils.randomAlphabetic(10); 
        final String receivedLine = new StringBuilder()
                                            .append("000")
                                            .append(separator)
                                            .append(legalDocumentNumber)
                                            .append(separator)                                            
                                            .append(name)                                      
                                            .append(separator)
                                            .append(businessArea)
                                            .toString();
        Assertions.assertThrows(Exception.class, () -> translator.translate(receivedLine));

    }
    @Test
    public void translateExceptionByBusinessArea() throws Exception {
        String legalDocumentNumber = RandomStringUtils.randomNumeric(11);
        String name = RandomStringUtils.randomAlphabetic(10); 
        String businessArea = "";
        final String receivedLine = new StringBuilder()
                                            .append("000")
                                            .append(separator)
                                            .append(legalDocumentNumber)
                                            .append(separator)                                            
                                            .append(name)                                      
                                            .append(separator)
                                            .append(businessArea)
                                            .toString();

        Assertions.assertThrows(Exception.class, () -> translator.translate(receivedLine));

    }


    @Test
    public void translateExceptionByInvalidLayout() throws Exception {
    
        final String receivedLine = new StringBuilder()
                                            .append("000")
                                            .append(separator)
                                            .append("legalDocumentNumber")
                                            .append(separator)                                            
                                            .append("name")                                      
                                            .append(separator)
                                            .append("area")
                                            .append(separator)
                                            .append("anything")
                                            .toString();

        Assertions.assertThrows(Exception.class, () -> translator.translate(receivedLine));

    }



}