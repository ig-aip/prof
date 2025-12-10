package data;






import enams.PayMethod;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.OffsetDateTime;


public class Sales {

    Long id;



    private int vendingApparatId;

    private int productId;

    private int productsSaleCount;

    private BigDecimal price;

    private PayMethod payMethod;

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
