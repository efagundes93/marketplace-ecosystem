package br.com.atox.sales.salesman.domain;

import java.io.Serializable;
import java.util.Objects;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * Salesman
 */
@RegisterForReflection
public class Salesman implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 4778717598422721291L;
    
    private String id;
    private String name;
    private String legalDocumentNumber;


    public Salesman() {
    }

    public Salesman(String id, String name, String legalDocumentNumber) {
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

    public Salesman id(String id) {
        this.id = id;
        return this;
    }

    public Salesman name(String name) {
        this.name = name;
        return this;
    }

    public Salesman legalDocumentNumber(String legalDocumentNumber) {
        this.legalDocumentNumber = legalDocumentNumber;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Salesman)) {
            return false;
        }
        Salesman salesman = (Salesman) o;
        return Objects.equals(id, salesman.id) && Objects.equals(name, salesman.name) && Objects.equals(legalDocumentNumber, salesman.legalDocumentNumber);
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
