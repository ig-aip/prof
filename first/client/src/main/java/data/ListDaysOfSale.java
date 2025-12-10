package data;

import java.time.OffsetDateTime;
import java.util.List;

public class ListDaysOfSale {
    List<DayOfSale> list;
    Long daysAgo;

    public ListDaysOfSale(Long daysAgo){
        this.daysAgo = daysAgo;
    }

    public void setArray(List<Sales> salesList){

        for(int i = 0; i < daysAgo; ++i){
            OffsetDateTime timeAgo = OffsetDateTime.now().minusDays(daysAgo - i);
            DayOfSale day = new DayOfSale(timeAgo);
            day.SetSalesPerDay(salesList);
            list.add(day);
        }
    }

    DayOfSale getIndx(int indx){
        return list.get(indx);
    }

    List<DayOfSale> getDayOfSaleList(){
        return list;
    }

}
