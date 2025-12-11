package data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class DayOfSale {
    List<Sales> list = new ArrayList<>();
    LocalDateTime time;

    public DayOfSale(LocalDateTime time) {
        this.time = time;
    }

    public void SetSalesPerDay(List<Sales> allSales){
        for(Sales s : allSales){
            if(s.getSoldAt().getDayOfMonth() == time.getDayOfMonth()){
                list.add(s);
            }
        }
    }

    public int getAllMoneyForThisDay(){
        int allMoney = 0;
        for(Sales s : list){
            allMoney += s.getPrice();
        }
        return allMoney;
    }

    public Long getAllSaledCount(){
        Long count = 0L;
        for(Sales s : list){
            count += s.getProductsSaleCount();
        }
        return count;
    }

    public List<Sales> getList() {
        return list;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
