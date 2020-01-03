package br.com.atox.report.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * FileReceivedReport
 */
public class FileReceivedReport implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5321419533943230284L;
    private SalesmanDataReport salesman;
    private CustomerDataReport  customer;
    private SalesDataReport     sale;


    public FileReceivedReport() {
    }
    
    public SalesmanDataReport getSalesman() {
        return this.salesman;
    }

    public void setSalesman(SalesmanDataReport salesman) {
        this.salesman = salesman;
    }

    public CustomerDataReport getCustomer() {
        return this.customer;
    }

    public void setCustomer(CustomerDataReport customer) {
        this.customer = customer;
    }

    public SalesDataReport getSale() {
        return this.sale;
    }

    public void setSale(SalesDataReport sale) {
        this.sale = sale;
    }

    public FileReceivedReport salesman(SalesmanDataReport salesman) {
        this.salesman = salesman;
        return this;
    }

    public FileReceivedReport customer(CustomerDataReport customer) {
        this.customer = customer;
        return this;
    }

    public FileReceivedReport sale(SalesDataReport sale) {
        this.sale = sale;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FileReceivedReport)) {
            return false;
        }
        FileReceivedReport fileReceivedReport = (FileReceivedReport) o;
        return Objects.equals(salesman, fileReceivedReport.salesman) && Objects.equals(customer, fileReceivedReport.customer) && Objects.equals(sale, fileReceivedReport.sale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salesman, customer, sale);
    }

    @Override
    public String toString() {
        return "{" +
            " salesman='" + getSalesman() + "'" +
            ", customer='" + getCustomer() + "'" +
            ", sale='" + getSale() + "'" +
            "}";
    } 
}