package br.com.atox.sales.etl.domain;

import java.io.Serializable;
import java.util.Objects;

public class SaleItemData implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 7215048161972004568L;
	private Long id;
	private Long quantity;
	private Long price;

	public SaleItemData() {
	}

	public SaleItemData(Long id, Long quantity, Long price) {
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

	public SaleItemData id(Long id) {
		this.id = id;
		return this;
	}

	public SaleItemData quantity(Long quantity) {
		this.quantity = quantity;
		return this;
	}

	public SaleItemData price(Long price) {
		this.price = price;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof SaleItemData)) {
			return false;
		}
		SaleItemData saleItem = (SaleItemData) o;
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
