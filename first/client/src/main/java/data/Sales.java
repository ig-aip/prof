package data;






import enams.PayMethod;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;


public class Sales {

    Long id;


    private int vendingApparatId;

    private int productId;

    private int productsSaleCount;

    private int price;

    private PayMethod payMethod;

    private LocalDateTime soldAt;


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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    public LocalDateTime getSoldAt() {
        return soldAt;
    }

    public void setSoldAt(LocalDateTime soldAt) {
        this.soldAt = soldAt;
    }
}
