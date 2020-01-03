package br.com.atox.salesman.repository;

import javax.enterprise.context.ApplicationScoped;

import br.com.atox.salesman.domain.Salesman;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

/**
 * SalesmanDataRepository
 */
@ApplicationScoped
public class SalesmanDataRepository implements PanacheMongoRepository<Salesman>{

    
}