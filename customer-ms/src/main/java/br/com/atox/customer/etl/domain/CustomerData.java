package br.com.atox.customer.etl.domain;

import java.io.Serializable;
import java.util.Objects;

import br.com.atox.customer.domain.Customer;
import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * CustomerData
 */
@RegisterForReflection
public class CustomerData implements Serializable{

    
    /**
     *
     */
    private static final long serialVersionUID = -3628523315128328364L;
    private String legalDocumentNumber;
	private String name;
	private String businessArea;


	public CustomerData() {
	}

	public CustomerData(String legalDocumentNumber, String name, String businessArea) {
		this.legalDocumentNumber = legalDocumentNumber;
		this.name = name;
		this.businessArea = businessArea;
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

	public String getBusinessArea() {
		return this.businessArea;
	}

	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}

	public CustomerData legalDocumentNumber(String legalDocumentNumber) {
		this.legalDocumentNumber = legalDocumentNumber;
		return this;
	}

	public CustomerData name(String name) {
		this.name = name;
		return this;
	}

	public CustomerData businessArea(String businessArea) {
		this.businessArea = businessArea;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Customer)) {
			return false;
		}
		CustomerData customer = (CustomerData) o;
		return Objects.equals(legalDocumentNumber, customer.legalDocumentNumber) && Objects.equals(name, customer.name) && Objects.equals(businessArea, customer.businessArea);
	}

	@Override
	public int hashCode() {
		return Objects.hash(legalDocumentNumber, name, businessArea);
	}

	@Override
	public String toString() {
		return "{" +
			" legalDocumentNumber='" + getLegalDocumentNumber() + "'" +
			", name='" + getName() + "'" +
			", businessArea='" + getBusinessArea() + "'" +
			"}";
	}
}