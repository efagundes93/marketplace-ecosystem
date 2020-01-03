package br.com.atox.report.domain;

import java.util.Objects;

/**
 * SalesmanDataReport
 */
public class SalesmanDataReport {

    private Long    totalRecordsRead;
    private String  salesmanHighestSalary;
    private Long    highestSalary;
    private String  salesmanLowestSalary;
    private Long    lowestSalary;

    public SalesmanDataReport() {
    }
    
    public Long getTotalRecordsRead() {
        return this.totalRecordsRead;
    }

    public void setTotalRecordsRead(Long totalReaded) {
        this.totalRecordsRead = totalReaded;
    }

    public String getSalesmanHighestSalary() {
        return this.salesmanHighestSalary;
    }

    public void setSalesmanHighestSalary(String salesmanHighestSalary) {
        this.salesmanHighestSalary = salesmanHighestSalary;
    }

    public Long getHighestSalary() {
        return this.highestSalary;
    }

    public void setHighestSalary(Long highestSalary) {
        this.highestSalary = highestSalary;
    }

    public String getSalesmanLowestSalary() {
        return this.salesmanLowestSalary;
    }

    public void setSalesmanLowestSalary(String salesmanLowestSalary) {
        this.salesmanLowestSalary = salesmanLowestSalary;
    }

    public Long getLowestSalary() {
        return this.lowestSalary;
    }

    public void setLowestSalary(Long lowestSalary) {
        this.lowestSalary = lowestSalary;
    }

    public SalesmanDataReport totalReaded(Long totalReaded) {
        this.totalRecordsRead = totalReaded;
        return this;
    }

    public SalesmanDataReport salesmanHighestSalary(String salesmanHighestSalary) {
        this.salesmanHighestSalary = salesmanHighestSalary;
        return this;
    }

    public SalesmanDataReport highestSalary(Long highestSalary) {
        this.highestSalary = highestSalary;
        return this;
    }

    public SalesmanDataReport salesmanLowestSalary(String salesmanLowestSalary) {
        this.salesmanLowestSalary = salesmanLowestSalary;
        return this;
    }

    public SalesmanDataReport lowestSalary(Long lowestSalary) {
        this.lowestSalary = lowestSalary;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SalesmanDataReport)) {
            return false;
        }
        SalesmanDataReport salesmanDataReport = (SalesmanDataReport) o;
        return Objects.equals(totalRecordsRead, salesmanDataReport.totalRecordsRead) && Objects.equals(salesmanHighestSalary, salesmanDataReport.salesmanHighestSalary) && Objects.equals(highestSalary, salesmanDataReport.highestSalary) && Objects.equals(salesmanLowestSalary, salesmanDataReport.salesmanLowestSalary) && Objects.equals(lowestSalary, salesmanDataReport.lowestSalary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalRecordsRead, salesmanHighestSalary, highestSalary, salesmanLowestSalary, lowestSalary);
    }

    @Override
    public String toString() {
        return "{" +
            " totalReaded='" + getTotalRecordsRead() + "'" +
            ", salesmanHighestSalary='" + getSalesmanHighestSalary() + "'" +
            ", highestSalary='" + getHighestSalary() + "'" +
            ", salesmanLowestSalary='" + getSalesmanLowestSalary() + "'" +
            ", lowestSalary='" + getLowestSalary() + "'" +
            "}";
    }
}