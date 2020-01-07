package br.com.atox.marketplace.sales.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import br.com.atox.marketplace.customer.domain.Customer;
import br.com.atox.marketplace.seller.domain.Seller;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * Sale
 */
@RegisterForReflection
public class Sale implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 3459793679010684215L;
    private Long id;
    private List<SaleItem> items;
    private Seller seller;
    private Customer customer;
    private String saleOrigin;


    public Sale() {
    }



    public Sale(Long id, List<SaleItem> items, Seller seller, Customer customer, String saleOrigin) {
        this.id = id;
        this.items = items;
        this.seller = seller;
        this.customer = customer;
        this.saleOrigin = saleOrigin;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SaleItem> getItems() {
        return this.items;
    }

    public void setItems(List<SaleItem> items) {
        this.items = items;
    }

    public Seller getSeller() {
        return this.seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getSaleOrigin() {
        return this.saleOrigin;
    }

    public void setSaleOrigin(String saleOrigin) {
        this.saleOrigin = saleOrigin;
    }

    public Sale id(Long id) {
        this.id = id;
        return this;
    }

    public Sale items(List<SaleItem> items) {
        this.items = items;
        return this;
    }

    public Sale seller(Seller seller) {
        this.seller = seller;
        return this;
    }

    public Sale customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Sale saleOrigin(String saleOrigin) {
        this.saleOrigin = saleOrigin;
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
        return Objects.equals(id, sale.id) && Objects.equals(items, sale.items) && Objects.equals(seller, sale.seller) && Objects.equals(customer, sale.customer) && Objects.equals(saleOrigin, sale.saleOrigin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, items, seller, customer, saleOrigin);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", items='" + getItems() + "'" +
            ", seller='" + getSeller() + "'" +
            ", customer='" + getCustomer() + "'" +
            ", saleOrigin='" + getSaleOrigin() + "'" +
            "}";
    }
    
}