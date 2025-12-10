package data;

import java.time.OffsetDateTime;
import java.util.List;

public class DayOfSale {
    List<Sales> list;
    OffsetDateTime time;

    public DayOfSale(OffsetDateTime time) {
        this.time = time;
    }

    public void SetSalesPerDay(List<Sales> allSales){
        for(Sales s : allSales){
            if(s.getSoldAt().getDayOfMonth() == time.getDayOfMonth()){
                list.add(s);
            }
        }
    }
}
