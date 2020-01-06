package br.com.atox.sale.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Sale implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -7626965514907311171L;
	private Long saleId;
	private String salesmanName;
	private List<SaleItem> items;

	public Sale() {
	}

	public Sale(Long saleId, String salesmanName, List<SaleItem> items) {
		this.saleId = saleId;
		this.salesmanName = salesmanName;
		this.items = items;
	}

	public static Function<Sale, Long> calculateTotal(){
		return (sale) -> {
			if(sale.getItems() == null || sale.getItems().isEmpty()) return 0L;
			return sale.getItems().stream()
								  .mapToLong( s -> {
									  if(s.getQuantity() == null || s.getQuantity().longValue() == 0L || s.getPrice() == null || s.getPrice().longValue() == 0L)
											  return 0L;
									 return s.getQuantity().longValue() * s.getPrice().longValue();
								  }).sum();
		};
	}

	public Long getSaleId() {
		return this.saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	public String getSalesmanName() {
		return this.salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

	public List<SaleItem> getItems() {
		return this.items;
	}

	public void setItems(List<SaleItem> items) {
		this.items = items;
	}

	public Sale saleId(Long saleId) {
		this.saleId = saleId;
		return this;
	}

	public Sale salesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
		return this;
	}

	public Sale items(List<SaleItem> items) {
		this.items = items;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Sale)) {
			return false;
		}
		Sale sale = (Sale) o;
		return Objects.equals(saleId, sale.saleId) && Objects.equals(salesmanName, sale.salesmanName) && Objects.equals(items, sale.items);
	}

	@Override
	public int hashCode() {
		return Objects.hash(saleId, salesmanName, items);
	}

	@Override
	public String toString() {
		return "{" +
			" saleId='" + getSaleId() + "'" +
			", salesmanName='" + getSalesmanName() + "'" +
			", items='" + getItems() + "'" +
			"}";
	}


}
