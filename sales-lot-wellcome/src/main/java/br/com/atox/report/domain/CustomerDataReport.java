package br.com.atox.report.domain;

import java.util.List;
import java.util.Objects;

/**
 * CustomerDataReport
 */
public class CustomerDataReport {

    private Long totalRecordsRead;
    private List<BusinessAreaDataReport> businessAreaReport;

    public CustomerDataReport() {
    }

    public Long getTotalRecordsRead() {
        return this.totalRecordsRead;
    }

    public void setTotalRecordsRead(Long totalReaded) {
        this.totalRecordsRead = totalReaded;
    }

    public List<BusinessAreaDataReport> getBusinessAreaReport() {
        return this.businessAreaReport;
    }

    public void setBusinessAreaReport(List<BusinessAreaDataReport> businessAreaReport) {
        this.businessAreaReport = businessAreaReport;
    }

    public CustomerDataReport totalRecordsRead(Long totalReaded) {
        this.totalRecordsRead = totalReaded;
        return this;
    }

    public CustomerDataReport businessAreaReport(List<BusinessAreaDataReport> businessAreaReport) {
        this.businessAreaReport = businessAreaReport;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CustomerDataReport)) {
            return false;
        }
        CustomerDataReport customerDataReport = (CustomerDataReport) o;
        return Objects.equals(totalRecordsRead, customerDataReport.totalRecordsRead) && Objects.equals(businessAreaReport, customerDataReport.businessAreaReport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalRecordsRead, businessAreaReport);
    }

    @Override
    public String toString() {
        return "{" +
            " totalReaded='" + getTotalRecordsRead() + "'" +
            ", businessAreaReport='" + getBusinessAreaReport() + "'" +
            "}";
    }

}