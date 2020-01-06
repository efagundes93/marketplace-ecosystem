package br.com.atox.sale.domain;

import java.io.Serializable;
import java.util.Objects;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class SaleItem implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 7215048161972004568L;
	private Long id;
	private Long quantity;
	private Long price;

	public SaleItem() {
	}

	public SaleItem(Long id, Long quantity, Long price) {
		this.id = id;
		this.quantity = quantity;
		this.price = price;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getPrice() {
		return this.price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public SaleItem id(Long id) {
		this.id = id;
		return this;
	}

	public SaleItem quantity(Long quantity) {
		this.quantity = quantity;
		return this;
	}

	public SaleItem price(Long price) {
		this.price = price;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof SaleItem)) {
			return false;
		}
		SaleItem saleItem = (SaleItem) o;
		return Objects.equals(id, saleItem.id) && Objects.equals(quantity, saleItem.quantity) && Objects.equals(price, saleItem.price);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, quantity, price);
	}

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", quantity='" + getQuantity() + "'" +
			", price='" + getPrice() + "'" +
			"}";
	}


	
}
