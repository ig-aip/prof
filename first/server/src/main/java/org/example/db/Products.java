package org.example.db;

import jakarta.persistence.*;
import org.example.Enums.PayMethod;

import java.math.BigDecimal;

@Entity
@Table(name = "Products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(columnDefinition = "text", name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "count")
    private int count;

    @Column(name = "minimalCount")
    private int minimalCount;

    @Column(name = "avgSalePerDay")
    private BigDecimal avgSalePerDay;

    public boolean needReFill(){
        if(count <= minimalCount){
            return true;
        }else{
            return false;
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMinimalCount() {
        return minimalCount;
    }

    public void setMinimalCount(int minimalCount) {
        this.minimalCount = minimalCount;
    }

    public BigDecimal getAvgSalePerDay() {
        return avgSalePerDay;
    }

    public void setAvgSalePerDay(BigDecimal avgSalePerDay) {
        this.avgSalePerDay = avgSalePerDay;
    }
}
