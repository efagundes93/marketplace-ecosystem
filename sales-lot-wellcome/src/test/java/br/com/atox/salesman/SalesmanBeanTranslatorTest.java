package br.com.atox.salesman;

import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.atox.salesman.domain.Salesman;
import br.com.atox.salesman.processor.SalesmanBeanTranslator;
import io.quarkus.test.junit.QuarkusTest;

/**
 * SalesmanBeanTranslatorTest
 */
@QuarkusTest
public class SalesmanBeanTranslatorTest {

    @ConfigProperty(name = "execution.file.layout.separator")
    public String separator;

    @Inject
    SalesmanBeanTranslator translator;
    
    public void translateOkTest() throws Exception{
        String legalDocumentNumber = RandomStringUtils.randomNumeric(11);
        String name = RandomStringUtils.randomAlphabetic(10);
        String salary = RandomStringUtils.randomNumeric(7); 

        final String receivedLine = new StringBuilder()
                                            .append("000")
                                            .append(separator)
                                            .append(legalDocumentNumber)
                                            .append(separator)                                            
                                            .append(name)                                      
                                            .append(separator)
                                            .append(salary)
                                            .toString();
                                            
        final Salesman salesman = translator.translate(receivedLine);
        Assertions.assertEquals(salesman.getLegalDocumentNumber(), legalDocumentNumber);
        Assertions.assertEquals(salesman.getName(), name.toUpperCase());
        Assertions.assertEquals(salesman.getSalary(), Long.parseLong(salary));
    }


    
    @Test
    public void translateExceptionByEmptyDocument() throws Exception {
        String legalDocumentNumber = "";
        String name = RandomStringUtils.randomAlphabetic(10);
        String salary = RandomStringUtils.randomNumeric(7);  
        final String receivedLine = new StringBuilder()
                                            .append("000")
                                            .append(separator)
                                            .append(legalDocumentNumber)
                                            .append(separator)                                            
                                            .append(name)                                      
                                            .append(separator)
                                            .append(salary)
                                            .toString();

        Assertions.assertThrows(Exception.class, () -> translator.translate(receivedLine));

    }

    @Test
    public void translateExceptionByEmptyName() throws Exception {
        String legalDocumentNumber = RandomStringUtils.randomNumeric(14);
        String name = "";
        String salary = RandomStringUtils.randomNumeric(7);  

        final String receivedLine = new StringBuilder()
                                            .append("000")
                                            .append(separator)
                                            .append(legalDocumentNumber)
                                            .append(separator)                                            
                                            .append(name)                                      
                                            .append(separator)
                                            .append(salary)
                                            .toString();

        Assertions.assertThrows(Exception.class, () -> translator.translate(receivedLine));

    }

    
    @Test
    public void translateExceptionByEmptySalary() throws Exception {
        String legalDocumentNumber = RandomStringUtils.randomNumeric(14);
        String name = RandomStringUtils.randomAlphabetic(10);
        String salary = "";  

        final String receivedLine = new StringBuilder()
                                            .append("000")
                                            .append(separator)
                                            .append(legalDocumentNumber)
                                            .append(separator)                                            
                                            .append(name)                                      
                                            .append(separator)
                                            .append(salary)
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
                                            .append("salary")
                                            .append(separator)
                                            .append("anything")
                                            .toString();

        Assertions.assertThrows(Exception.class, () -> translator.translate(receivedLine));

    }


}