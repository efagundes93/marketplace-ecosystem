package br.com.atox.report.domain;

import java.util.Objects;

/**
 * SalesmanPerformanceReport
 */
public class SalesmanPerformanceReport {

    private String  criteria;
    private String  classification;
    private String  salesmanName;
    private Long    totalOfSales;
    private Long    totalAmount;


    public SalesmanPerformanceReport() {
    }

    public SalesmanPerformanceReport(String criteria, String classification, String salesmanName, Long totalOfSales, Long totalAmount) {
        this.criteria = criteria;
        this.classification = classification;
        this.salesmanName = salesmanName;
        this.totalOfSales = totalOfSales;
        this.totalAmount = totalAmount;
    }

    public String getCriteria() {
        return this.criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getClassification() {
        return this.classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getSalesmanName() {
        return this.salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public Long getTotalOfSales() {
        return this.totalOfSales;
    }

    public void setTotalOfSales(Long totalOfSales) {
        this.totalOfSales = totalOfSales;
    }

    public Long getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public SalesmanPerformanceReport criteria(String criteria) {
        this.criteria = criteria;
        return this;
    }

    public SalesmanPerformanceReport classification(String classification) {
        this.classification = classification;
        return this;
    }

    public SalesmanPerformanceReport salesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
        return this;
    }

    public SalesmanPerformanceReport totalOfSales(Long totalOfSales) {
        this.totalOfSales = totalOfSales;
        return this;
    }

    public SalesmanPerformanceReport totalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SalesmanPerformanceReport)) {
            return false;
        }
        SalesmanPerformanceReport salesmanPerformanceReport = (SalesmanPerformanceReport) o;
        return Objects.equals(criteria, salesmanPerformanceReport.criteria) && Objects.equals(classification, salesmanPerformanceReport.classification) && Objects.equals(salesmanName, salesmanPerformanceReport.salesmanName) && Objects.equals(totalOfSales, salesmanPerformanceReport.totalOfSales) && Objects.equals(totalAmount, salesmanPerformanceReport.totalAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(criteria, classification, salesmanName, totalOfSales, totalAmount);
    }

    @Override
    public String toString() {
        return "{" +
            " criteria='" + getCriteria() + "'" +
            ", classification='" + getClassification() + "'" +
            ", salesmanName='" + getSalesmanName() + "'" +
            ", totalOfSales='" + getTotalOfSales() + "'" +
            ", totalAmount='" + getTotalAmount() + "'" +
            "}";
    }


}