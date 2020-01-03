package br.com.atox.sale.domain;

import java.io.Serializable;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.bson.codecs.pojo.annotations.BsonId;

import io.quarkus.mongodb.panache.MongoEntity;

/**
 * <p> Entidade que sintetiza os dados de venda. </p>
 * @author Emiliano Fagundes
 * @since 2020-01
 * SaleSynthesis
 */
@MongoEntity
public class SaleSynthesis implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -315947057499096183L;
    @BsonId
    private Long saleId;
    private Long totalPrice;
    private String salesmanName;
    

    public SaleSynthesis() {
    }

    public SaleSynthesis(Long saleId, Long totalPrice, String salesmanName) {
        this.saleId = saleId;
        this.totalPrice = totalPrice;
        this.salesmanName = salesmanName;
    }

    public Long getSaleId() {
        return this.saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Long getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSalesmanName() {
        return this.salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public SaleSynthesis saleId(Long saleId) {
        this.saleId = saleId;
        return this;
    }

    public SaleSynthesis totalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public SaleSynthesis salesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SaleSynthesis)) {
            return false;
        }
        SaleSynthesis saleSynthesis = (SaleSynthesis) o;
        return Objects.equals(saleId, saleSynthesis.saleId) && Objects.equals(totalPrice, saleSynthesis.totalPrice) && Objects.equals(salesmanName, saleSynthesis.salesmanName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(saleId, totalPrice, salesmanName);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
    
}