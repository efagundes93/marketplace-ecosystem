package br.com.atox.report.domain;

import java.util.List;
import java.util.Objects;

import br.com.atox.sale.domain.SaleSynthesis;

/**
 * SalesDataReport
 */
public class SalesDataReport {

    private Long    totalRecordsRead;
    private List<SalesmanPerformanceReport> salemanPerformance;
    private Long totalAmount;
    private Long totalOfSales;
    private SaleSynthesis mostExpensiveSale;


    public SalesDataReport() {
    }

    public SalesDataReport(Long totalRecordsRead, List<SalesmanPerformanceReport> salemanPerformance, Long totalAmount, Long totalOfSales, SaleSynthesis mostExpensiveSale) {
        this.totalRecordsRead = totalRecordsRead;
        this.salemanPerformance = salemanPerformance;
        this.totalAmount = totalAmount;
        this.totalOfSales = totalOfSales;
        this.mostExpensiveSale = mostExpensiveSale;
    }

    public Long getTotalRecordsRead() {
        return this.totalRecordsRead;
    }

    public void setTotalRecordsRead(Long totalRecordsRead) {
        this.totalRecordsRead = totalRecordsRead;
    }

    public List<SalesmanPerformanceReport> getSalemanPerformance() {
        return this.salemanPerformance;
    }

    public void setSalemanPerformance(List<SalesmanPerformanceReport> salemanPerformance) {
        this.salemanPerformance = salemanPerformance;
    }

    public Long getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getTotalOfSales() {
        return this.totalOfSales;
    }

    public void setTotalOfSales(Long totalOfSales) {
        this.totalOfSales = totalOfSales;
    }

    public SaleSynthesis getMostExpensiveSale() {
        return this.mostExpensiveSale;
    }

    public void setMostExpensiveSale(SaleSynthesis mostExpensiveSale) {
        this.mostExpensiveSale = mostExpensiveSale;
    }

    public SalesDataReport totalRecordsRead(Long totalRecordsRead) {
        this.totalRecordsRead = totalRecordsRead;
        return this;
    }

    public SalesDataReport salemanPerformance(List<SalesmanPerformanceReport> salemanPerformance) {
        this.salemanPerformance = salemanPerformance;
        return this;
    }

    public SalesDataReport totalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public SalesDataReport totalOfSales(Long totalOfSales) {
        this.totalOfSales = totalOfSales;
        return this;
    }

    public SalesDataReport mostExpensiveSale(SaleSynthesis mostExpensiveSale) {
        this.mostExpensiveSale = mostExpensiveSale;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SalesDataReport)) {
            return false;
        }
        SalesDataReport salesDataReport = (SalesDataReport) o;
        return Objects.equals(totalRecordsRead, salesDataReport.totalRecordsRead) && Objects.equals(salemanPerformance, salesDataReport.salemanPerformance) && Objects.equals(totalAmount, salesDataReport.totalAmount) && Objects.equals(totalOfSales, salesDataReport.totalOfSales) && Objects.equals(mostExpensiveSale, salesDataReport.mostExpensiveSale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalRecordsRead, salemanPerformance, totalAmount, totalOfSales, mostExpensiveSale);
    }

    @Override
    public String toString() {
        return "{" +
            " totalRecordsRead='" + getTotalRecordsRead() + "'" +
            ", salemanPerformance='" + getSalemanPerformance() + "'" +
            ", totalAmount='" + getTotalAmount() + "'" +
            ", totalOfSales='" + getTotalOfSales() + "'" +
            ", mostExpensiveSale='" + getMostExpensiveSale() + "'" +
            "}";
    }

}