package br.com.atox.report.domain;

import java.util.Objects;

/**
 * BusinessAreaReport
 */
public class BusinessAreaDataReport {

    private String businessArea;
    private Long quantity;

    public BusinessAreaDataReport() {
    }

    public BusinessAreaDataReport(String businessArea, Long quantity) {
        this.businessArea = businessArea;
        this.quantity = quantity;
    }

    public String getBusinessArea() {
        return this.businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public Long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BusinessAreaDataReport businessArea(String businessArea) {
        this.businessArea = businessArea;
        return this;
    }

    public BusinessAreaDataReport quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BusinessAreaDataReport)) {
            return false;
        }
        BusinessAreaDataReport businessAreaReport = (BusinessAreaDataReport) o;
        return Objects.equals(businessArea, businessAreaReport.businessArea) && Objects.equals(quantity, businessAreaReport.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessArea, quantity);
    }

    @Override
    public String toString() {
        return "{" +
            " businessArea='" + getBusinessArea() + "'" +
            ", quantity='" + getQuantity() + "'" +
            "}";
    }

}