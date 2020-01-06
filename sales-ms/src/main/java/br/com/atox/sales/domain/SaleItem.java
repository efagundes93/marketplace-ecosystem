package br.com.atox.sales.domain;

import java.io.Serializable;
import java.util.Objects;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * SaleItem
 */
@RegisterForReflection
public class SaleItem implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = -6167975593745758549L;
    private Long itemSequenceNumber;
    private Long quantity;
    private Long price;
    private String productId;

    public SaleItem() {
    }

    public SaleItem(Long itemSequenceNumber, Long quantity, Long price, String productId) {
        this.itemSequenceNumber = itemSequenceNumber;
        this.quantity = quantity;
        this.price = price;
        this.productId = productId;
    }

    public Long getItemSequenceNumber() {
        return this.itemSequenceNumber;
    }

    public void setItemSequenceNumber(Long itemSequenceNumber) {
        this.itemSequenceNumber = itemSequenceNumber;
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

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public SaleItem itemSequenceNumber(Long itemSequenceNumber) {
        this.itemSequenceNumber = itemSequenceNumber;
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

    public SaleItem productId(String productId) {
        this.productId = productId;
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
        return Objects.equals(itemSequenceNumber, saleItem.itemSequenceNumber) && Objects.equals(quantity, saleItem.quantity) && Objects.equals(price, saleItem.price) && Objects.equals(productId, saleItem.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemSequenceNumber, quantity, price, productId);
    }

    @Override
    public String toString() {
        return "{" +
            " itemSequenceNumber='" + getItemSequenceNumber() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", price='" + getPrice() + "'" +
            ", productId='" + getProductId() + "'" +
            "}";
    }

}