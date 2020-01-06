package br.com.atox.configuration;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
/**
 * CamelSetup
 */
@ApplicationScoped
public class CamelSetup {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CamelSetup.class);

    @Inject
    CamelContext camelContext;

    @Produces
    public ProducerTemplate buildCamelProducerTemplate() throws Exception {
        return camelContext.createProducerTemplate();
    }

    void onStart(@Observes StartupEvent ev) {
        //runtime.addProperty("started", "true");
        LOGGER.info("Camel Runtime started");
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("Camel Runtime stopped");
    }

}