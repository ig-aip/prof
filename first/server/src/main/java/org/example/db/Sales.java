package org.example.db;

import jakarta.persistence.*;
import org.example.Enums.PayMethod;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.OffsetDateTime;

@Entity
@Table(name = "Sales")
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @Column(name = "vendingApparatId", nullable = false)
    private int vendingApparatId;

    @Column(name = "productId", nullable = false)
    private int productId;

    @Column(name = "productsSaleCount", nullable = false)
    private int productsSaleCount;

    @Column(name = "price", nullable = false, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "payMethod")
    private PayMethod payMethod;

    @Column(name = "soldAt")
    private OffsetDateTime soldAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVendingApparatId() {
        return vendingApparatId;
    }

    public void setVendingApparatId(int vendingApparatId) {
        this.vendingApparatId = vendingApparatId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductsSaleCount() {
        return productsSaleCount;
    }

    public void setProductsSaleCount(int productsSaleCount) {
        this.productsSaleCount = productsSaleCount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    public OffsetDateTime getSoldAt() {
        return soldAt;
    }

    public void setSoldAt(OffsetDateTime soldAt) {
        this.soldAt = soldAt;
    }
}
