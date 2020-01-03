package br.com.atox.customer.domain;

import java.util.Objects;

import io.quarkus.mongodb.panache.MongoEntity;
/**
 * <p> Entidade de dominio dos dados de clientes <p>
 * @author Emiliano Thomas Fagundes
 */
@MongoEntity(collection = "CustomerReceivedData")
public class Customer {

	private String legalDocumentNumber;
	private String name;
	private String businessArea;


	public Customer() {
	}

	public Customer(String legalDocumentNumber, String name, String businessArea) {
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

	public Customer legalDocumentNumber(String legalDocumentNumber) {
		this.legalDocumentNumber = legalDocumentNumber;
		return this;
	}

	public Customer name(String name) {
		this.name = name;
		return this;
	}

	public Customer businessArea(String businessArea) {
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
		Customer customer = (Customer) o;
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
