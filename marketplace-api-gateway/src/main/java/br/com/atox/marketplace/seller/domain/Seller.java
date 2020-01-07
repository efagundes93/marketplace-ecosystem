package br.com.atox.marketplace.seller.domain;

import java.io.Serializable;
import java.util.Objects;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * Seller
 */
@RegisterForReflection
public class Seller implements Serializable{

	/**
     *
     */
    private static final long serialVersionUID = -564509658019468299L;
    private String id;
    private String legalDocumentNumber;
	private String name;
    private Long salary;

    public Seller() {
    }

    public Seller(String id, String legalDocumentNumber, String name, Long salary) {
        this.id = id;
        this.legalDocumentNumber = legalDocumentNumber;
        this.name = name;
        this.salary = salary;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLegalDocumentNumber() {
        return this.legalDocumentNumber;
    }

    public void setLegalDocumentNumber(String legalDocumentNumber) {
        this.legalDocumentNumber = legalDocumentNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSalary() {
        return this.salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Seller id(String id) {
        this.id = id;
        return this;
    }

    public Seller legalDocumentNumber(String legalDocumentNumber) {
        this.legalDocumentNumber = legalDocumentNumber;
        return this;
    }

    public Seller name(String name) {
        this.name = name;
        return this;
    }

    public Seller salary(Long salary) {
        this.salary = salary;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Seller)) {
            return false;
        }
        Seller seller = (Seller) o;
        return Objects.equals(id, seller.id) && Objects.equals(legalDocumentNumber, seller.legalDocumentNumber) && Objects.equals(name, seller.name) && Objects.equals(salary, seller.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, legalDocumentNumber, name, salary);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", legalDocumentNumber='" + getLegalDocumentNumber() + "'" +
            ", name='" + getName() + "'" +
            ", salary='" + getSalary() + "'" +
            "}";
    }

}