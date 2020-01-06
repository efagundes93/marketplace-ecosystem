package br.com.atox.sales.customer;

import java.io.Serializable;
import java.util.Objects;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * Customer
 */
@RegisterForReflection
public class Customer implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6131378681163295471L;
    
    private String id;
    private String name;
    private String legalDocumentNumber;

    public Customer() {
    }

    public Customer(String id, String name, String legalDocumentNumber) {
        this.id = id;
        this.name = name;
        this.legalDocumentNumber = legalDocumentNumber;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLegalDocumentNumber() {
        return this.legalDocumentNumber;
    }

    public void setLegalDocumentNumber(String legalDocumentNumber) {
        this.legalDocumentNumber = legalDocumentNumber;
    }

    public Customer id(String id) {
        this.id = id;
        return this;
    }

    public Customer name(String name) {
        this.name = name;
        return this;
    }

    public Customer legalDocumentNumber(String legalDocumentNumber) {
        this.legalDocumentNumber = legalDocumentNumber;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Customer)) {
            return false;
        }
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(legalDocumentNumber, customer.legalDocumentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, legalDocumentNumber);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", legalDocumentNumber='" + getLegalDocumentNumber() + "'" +
            "}";
    }

}