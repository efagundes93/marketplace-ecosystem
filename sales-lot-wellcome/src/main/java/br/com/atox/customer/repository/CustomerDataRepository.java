package br.com.atox.customer.repository;

import javax.enterprise.context.ApplicationScoped;

import br.com.atox.customer.domain.Customer;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

/**
 * <p>Repositório para dados relativos aos clientes</p>
 * CustomerDataRepository
 */
@ApplicationScoped
public class CustomerDataRepository implements PanacheMongoRepository<Customer> {
    
}