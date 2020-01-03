package br.com.atox.salesman.domain;

import java.io.Serializable;
import java.util.Objects;

import io.quarkus.mongodb.panache.MongoEntity;

@MongoEntity(collection = "SalesmanReceivedData")
public class Salesman implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2001510848749867311L;
	private String legalDocumentNumber;
	private String name;
	private Long salary;
	public Salesman() {
		super();
	}

	public Salesman(String legalDocumentNumber, String name, Long salary) {
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

	public Salesman legalDocumentNumber(String legalDocumentNumber) {
		this.legalDocumentNumber = legalDocumentNumber;
		return this;
	}

	public Salesman name(String name) {
		this.name = name;
		return this;
	}

	public Salesman salary(Long salary) {
		this.salary = salary;
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
		return Objects.equals(legalDocumentNumber, salesman.legalDocumentNumber) && Objects.equals(name, salesman.name) && Objects.equals(salary, salesman.salary);
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
