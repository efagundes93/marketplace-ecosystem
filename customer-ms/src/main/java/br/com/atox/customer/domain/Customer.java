package br.com.atox.customer.domain;

import java.io.Serializable;
import java.util.Objects;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * Customer
 */
@RegisterForReflection
public class Customer implements Serializable{   

	/**
	 *
	 */
	private static final long serialVersionUID = 5494787791861725485L;
	private String id;
    private String legalDocumentNumber;
	private String name;
	private String businessArea;


	public Customer() {
	}

	public Customer(String id, String legalDocumentNumber, String name, String businessArea) {
		this.id = id;
		this.legalDocumentNumber = legalDocumentNumber;
		this.name = name;
		this.businessArea = businessArea;
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

	public String getBusinessArea() {
		return this.businessArea;
	}

	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}

	public Customer id(String id) {
		this.id = id;
		return this;
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
		return Objects.equals(id, customer.id) && Objects.equals(legalDocumentNumber, customer.legalDocumentNumber) && Objects.equals(name, customer.name) && Objects.equals(businessArea, customer.businessArea);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, legalDocumentNumber, name, businessArea);
	}

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", legalDocumentNumber='" + getLegalDocumentNumber() + "'" +
			", name='" + getName() + "'" +
			", businessArea='" + getBusinessArea() + "'" +
			"}";
	}
}