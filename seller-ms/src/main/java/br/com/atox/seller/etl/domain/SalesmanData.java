package br.com.atox.seller.etl.domain;

import java.util.Objects;

/**
 * SalesmanData
 */
public class SalesmanData {

    private String legalDocumentNumber;
	private String name;
    private Long salary;
    

    public SalesmanData() {
    }

    public SalesmanData(String legalDocumentNumber, String name, Long salary) {
        this.legalDocumentNumber = legalDocumentNumber;
        this.name = name;
        this.salary = salary;
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

    public SalesmanData legalDocumentNumber(String legalDocumentNumber) {
        this.legalDocumentNumber = legalDocumentNumber;
        return this;
    }

    public SalesmanData name(String name) {
        this.name = name;
        return this;
    }

    public SalesmanData salary(Long salary) {
        this.salary = salary;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SalesmanData)) {
            return false;
        }
        SalesmanData salesmanData = (SalesmanData) o;
        return Objects.equals(legalDocumentNumber, salesmanData.legalDocumentNumber) && Objects.equals(name, salesmanData.name) && Objects.equals(salary, salesmanData.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(legalDocumentNumber, name, salary);
    }

    @Override
    public String toString() {
        return "{" +
            " legalDocumentNumber='" + getLegalDocumentNumber() + "'" +
            ", name='" + getName() + "'" +
            ", salary='" + getSalary() + "'" +
            "}";
    }

}